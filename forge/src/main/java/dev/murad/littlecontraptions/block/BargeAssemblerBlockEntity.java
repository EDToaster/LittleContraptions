package dev.murad.littlecontraptions.block;

import com.simibubi.create.content.contraptions.components.structureMovement.AssemblyException;
import com.simibubi.create.content.contraptions.components.structureMovement.OrientedContraptionEntity;
import com.simibubi.create.content.contraptions.components.structureMovement.mounted.CartAssemblerBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.mounted.CartAssemblerTileEntity;
import com.simibubi.create.content.contraptions.components.structureMovement.mounted.MountedContraption;
import dev.murad.littlecontraptions.entity.ContraptionBargeEntity;
import dev.murad.littlecontraptions.setup.LCBlockEntityTypes;
import dev.murad.littlecontraptions.setup.LCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BargeAssemblerBlockEntity extends BlockEntity {
    private static final int assemblyCooldown = 8;

    private int ticksSinceLastUpdate;
    protected AssemblyException lastException;

    public BargeAssemblerBlockEntity(BlockPos pos, BlockState state) {
        super(LCBlockEntityTypes.BARGE_ASSEMBLER.get(), pos, state);
        this.ticksSinceLastUpdate = 0;
    }

    private void serverTick() {
        // do cooldown checking
        if (ticksSinceLastUpdate < assemblyCooldown) {
            ticksSinceLastUpdate++;
            return;
        }

        if (level == null) return;

        List<ContraptionBargeEntity> barges = level.getEntities(EntityTypeTest.forClass(ContraptionBargeEntity.class),
                new AABB(getBlockPos()).deflate(0.3f),
                (e) -> true);

        if (barges.size() > 0)
            tryApplyActions(barges.get(0));
    }


    public void tryApplyActions(ContraptionBargeEntity barge) {
        if (barge == null || level == null || !canUpdate())
            return;

        setUpdated();

        BlockState state = level.getBlockState(worldPosition);
        if (state.getBlock() != LCBlocks.BARGE_ASSEMBLER.get())
            return;

        CartAssemblerBlock.CartAssemblerAction action = getBlockState().getValue(BargeAssemblerBlock.POWERED) ?
                CartAssemblerBlock.CartAssemblerAction.ASSEMBLE :
                CartAssemblerBlock.CartAssemblerAction.DISASSEMBLE;

        if (action.shouldAssemble())
            assemble(level, worldPosition, barge);
        if (action.shouldDisassemble())
            disassemble(level, worldPosition, barge);
    }

    protected void assemble(Level world, BlockPos pos, ContraptionBargeEntity barge) {
        if (!barge.getPassengers()
                .isEmpty())
            return;

        CartAssemblerTileEntity.CartMovementMode mode = CartAssemblerTileEntity.CartMovementMode.ROTATE;

        MountedContraption contraption = new MountedContraption(mode);
        try {
            if (!contraption.assemble(world, pos)) {
                return;
            }

            lastException = null;
        } catch (AssemblyException e) {
            lastException = e;
            e.printStackTrace();
            return;
        }

        Direction initialOrientation = BargeAssemblerBlock.getHorizontalDirection(getBlockState());

        contraption.removeBlocksFromWorld(world, BlockPos.ZERO);
        contraption.startMoving(world);
        contraption.expandBoundsAroundAxis(Direction.Axis.Y);

        OrientedContraptionEntity entity = OrientedContraptionEntity.create(world, contraption, initialOrientation);

        entity.setPos(barge.getRiderPosition());
        world.addFreshEntity(entity);
        entity.startRiding(barge);
    }

    protected void disassemble(Level world, BlockPos pos, ContraptionBargeEntity barge) {
        if (barge.getPassengers()
                .isEmpty())
            return;
        Entity entity = barge.getPassengers()
                .get(0);
        if (!(entity instanceof OrientedContraptionEntity))
            return;
        OrientedContraptionEntity contraption = (OrientedContraptionEntity) entity;

        contraption.yaw = BargeAssemblerBlock.getHorizontalDirection(getBlockState())
                .toYRot();
        barge.ejectPassengers();
    }

    public void setUpdated() {
        ticksSinceLastUpdate = 0;
    }

    private boolean canUpdate() {
        return ticksSinceLastUpdate >= assemblyCooldown;
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, BargeAssemblerBlockEntity e) {
        e.serverTick();
    }

}

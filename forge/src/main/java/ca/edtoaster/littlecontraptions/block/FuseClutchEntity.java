package ca.edtoaster.littlecontraptions.block;

import ca.edtoaster.littlecontraptions.setup.LCBlockEntityTypes;
import com.simibubi.create.content.contraptions.relays.encased.GearshiftBlock;
import com.simibubi.create.content.contraptions.relays.encased.SplitShaftTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FuseClutchEntity extends SplitShaftTileEntity {

    public FuseClutchEntity(BlockPos pos, BlockState state) {
        super(LCBlockEntityTypes.FUSE_CLUTCH.get(), pos, state);
    }

    @Override
    public void tick() {
        super.tick();

        // detach if overstressed
        if (this.overStressed) {
            this.level.setBlock(
                    this.getBlockPos(),
                    this.getBlockState()
                            .setValue(BlockStateProperties.POWERED, true),
                    16
            );
            GearshiftBlock block = (GearshiftBlock) this.getBlockState().getBlock();
            block.detachKinetics(level, getBlockPos(), false);
        }
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        return this.hasSource()
                && face != this.getSourceFacing()
                && this.getBlockState().getValue(BlockStateProperties.POWERED)
                ? 0.0F : 1.0F;
    }
}

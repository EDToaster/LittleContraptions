package ca.edtoaster.littlecontraptions.block;

import ca.edtoaster.littlecontraptions.setup.LCBlockEntityTypes;
import com.simibubi.create.content.contraptions.relays.encased.GearshiftBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class FuseClutch extends GearshiftBlock {

    public FuseClutch(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isClientSide) {
            // if powered, set engaged to false
            if (worldIn.hasNeighborSignal(pos)) {
                worldIn.setBlock(pos, state.setValue(POWERED, false), 18);

                // reattach
                this.detachKinetics(worldIn, pos, true);
            }
        }
    }

    public BlockEntityType<? extends FuseClutchEntity> getTileEntityType() {
        return LCBlockEntityTypes.FUSE_CLUTCH.get();
    }
}

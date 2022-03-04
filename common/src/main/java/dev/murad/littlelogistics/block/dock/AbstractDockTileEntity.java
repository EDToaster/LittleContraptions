package dev.murad.littlelogistics.block.dock;

import dev.murad.littlelogistics.block.IVesselLoader;
import dev.murad.littlelogistics.entity.custom.VesselEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public abstract class AbstractDockTileEntity extends BlockEntity {
    public AbstractDockTileEntity(BlockEntityType<?> p_i48289_1_, BlockPos pos, BlockState s) {
        super(p_i48289_1_, pos, s);
    }

    public abstract boolean holdVessel(VesselEntity vessel, Direction direction);

    public Optional<HopperBlockEntity> getHopper(){
        BlockEntity mayBeHopper = this.level.getBlockEntity(this.getTargetBlockPos());
        if (mayBeHopper instanceof HopperBlockEntity h) {
            return Optional.of(h);
        }
        else return Optional.empty();
    }

    public Optional<IVesselLoader> getVesselLoader(){
        BlockEntity mayBeHopper = this.level.getBlockEntity(this.getTargetBlockPos());
        if (mayBeHopper instanceof IVesselLoader l) {
            return Optional.of(l);
        }
        else return Optional.empty();
    }

    protected abstract BlockPos getTargetBlockPos();

}

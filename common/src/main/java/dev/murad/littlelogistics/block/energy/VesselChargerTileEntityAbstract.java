package dev.murad.littlelogistics.block.energy;

import dev.murad.littlelogistics.block.IVesselLoader;
import dev.murad.littlelogistics.entity.custom.VesselEntity;
import dev.murad.littlelogistics.setup.LLBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class VesselChargerTileEntityAbstract extends BlockEntity implements IVesselLoader {
    private int cooldownTime = 0;

    protected abstract void setEnergy(int energy);

    public VesselChargerTileEntityAbstract(BlockPos pos, BlockState state) {
        super(LLBlockEntityTypes.VESSEL_CHARGER.get(), pos, state);
        setEnergy(0);
    }


    private void serverTickInternal() {
        if (this.level != null) {
            --this.cooldownTime;
            if (this.cooldownTime <= 0) {
                this.cooldownTime = tryChargeEntity() ? 0 : 10;
            }
        }
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, VesselChargerTileEntityAbstract e) {
        e.serverTickInternal();
    }


    private boolean tryChargeEntity() {
        return IVesselLoader.getEntityCapability(getBlockPos().relative(getBlockState().getValue(VesselChargerBlock.FACING)),
                CapabilityEnergy.ENERGY, level).map(iEnergyStorage -> {
                    int vesselCap = iEnergyStorage.receiveEnergy(MAX_TRANSFER, true);
                    int toTransfer = internalBattery.extractEnergy(vesselCap, false);
                    return iEnergyStorage.receiveEnergy(toTransfer, false) > 0;
        }).orElse(false);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        internalBattery.readAdditionalSaveData(compound.getCompound("energy_storage"));
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        CompoundTag energyNBT = new CompoundTag();
        internalBattery.addAdditionalSaveData(energyNBT);
        super.saveAdditional(compound);
        compound.put("energy_storage", energyNBT);
    }

    @Override
    public boolean holdVessel(VesselEntity vessel, Mode mode) {
        return vessel.getCapability(CapabilityEnergy.ENERGY).map(energyHandler -> {
            switch (mode) {
                case EXPORT:
                    return (energyHandler.getEnergyStored() < energyHandler.getMaxEnergyStored() - 50) && internalBattery.getEnergyStored() > 50;
                default:
                    return false;
            }
        }).orElse(false);
    }

    public void use(Player player, InteractionHand hand) {
        player.displayClientMessage(new TranslatableComponent("block.littlelogistics.vessel_charger.capacity",
                internalBattery.getEnergyStored(), internalBattery.getMaxEnergyStored()), false);
    }
}

package dev.murad.shipping.energy;

import net.minecraft.nbt.CompoundTag;

public class CommonEnergyStorage {

    public static final String ENERGY_TAG = "energy";

    private final int maxCapacity, maxReceive, maxExtract;
    private EnergyStorage proxyStorage;

    public ReadWriteEnergyStorage(int maxCapacity, int maxReceive, int maxExtract)
    {
        this.maxCapacity = maxCapacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        proxyStorage = null;
    }

    private int clampInclusive(int n, int lo, int hi) {
        return Math.max(lo, Math.min(n, hi));
    }

    // when a TileEntity/Item/Entity is created, call this to set it up
    public void setEnergy(int energy) {
        proxyStorage = new EnergyStorage(maxCapacity, maxReceive, maxExtract, clampInclusive(energy, 0, maxCapacity));
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        int energy = compound.contains(ENERGY_TAG) ? compound.getInt(ENERGY_TAG) : 0;
        proxyStorage = new EnergyStorage(maxCapacity, maxReceive, maxExtract, clampInclusive(energy, 0, maxCapacity));
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt(ENERGY_TAG, proxyStorage.getEnergyStored());
    }

    public int receiveEnergy(int maxReceive, boolean simulate) {
        return proxyStorage.receiveEnergy(maxReceive, simulate);
    }

    public int extractEnergy(int maxExtract, boolean simulate) {
        return proxyStorage.extractEnergy(maxExtract, simulate);
    }

    public int getEnergyStored() {
        return proxyStorage.getEnergyStored();
    }

    public int getMaxEnergyStored() {
        return proxyStorage.getMaxEnergyStored();
    }

    public boolean canExtract() {
        return proxyStorage.canExtract();
    }

    public boolean canReceive() {
        return proxyStorage.canReceive();
    }
}

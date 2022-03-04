package dev.murad.littlelogistics.setup;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.murad.littlelogistics.block.dock.BargeDockTileEntity;
import dev.murad.littlelogistics.block.dock.TugDockTileEntity;
import dev.murad.littlelogistics.block.energy.VesselChargerTileEntityAbstract;
import dev.murad.littlelogistics.block.fluid.FluidHopperTileEntity;
import dev.murad.littlelogistics.block.vessel_detector.VesselDetectorTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class LLBlockEntityTypes {
    public static final RegistrySupplier<BlockEntityType<TugDockTileEntity>> TUG_DOCK = register(
            "tug_dock",
            TugDockTileEntity::new,
            LLBlocks.TUG_DOCK
    );

    public static final RegistrySupplier<BlockEntityType<BargeDockTileEntity>> BARGE_DOCK = register(
            "barge_dock",
            BargeDockTileEntity::new,
            LLBlocks.BARGE_DOCK
    );

    public static final RegistrySupplier<BlockEntityType<VesselDetectorTileEntity>> VESSEL_DETECTOR = register(
            "vessel_detector",
            VesselDetectorTileEntity::new,
            LLBlocks.VESSEL_DETECTOR
    );

    public static final RegistrySupplier<BlockEntityType<FluidHopperTileEntity>> FLUID_HOPPER = register(
            "fluid_hopper",
            FluidHopperTileEntity::new,
            LLBlocks.FLUID_HOPPER
    );

    public static final RegistrySupplier<BlockEntityType<VesselChargerTileEntityAbstract>> VESSEL_CHARGER = register(
            "vessel_charger",
            VesselChargerTileEntityAbstract::new,
            LLBlocks.VESSEL_CHARGER
    );

    private static <T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> register(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            RegistrySupplier<? extends Block> block) {
        return Registration.BLOCK_ENTITY_TYPES.register(name, () ->
                BlockEntityType.Builder.of(factory, block.get()).build(null));
    }

    public static void register () {

    }
}

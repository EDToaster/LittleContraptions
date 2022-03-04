package dev.murad.littlelogistics.setup;


import dev.architectury.registry.registries.RegistrySupplier;
import dev.murad.littlelogistics.block.dock.BargeDockBlock;
import dev.murad.littlelogistics.block.dock.TugDockBlock;
import dev.murad.littlelogistics.block.energy.VesselChargerBlock;
import dev.murad.littlelogistics.block.fluid.FluidHopperBlock;
import dev.murad.littlelogistics.block.guide_rail.CornerGuideRailBlock;
import dev.murad.littlelogistics.block.guide_rail.TugGuideRailBlock;
import dev.murad.littlelogistics.block.vessel_detector.VesselDetectorBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class LLBlocks {
    public static final RegistrySupplier<Block> TUG_DOCK = register(
            "tug_dock",
            () -> new TugDockBlock(Block.Properties.of(Material.METAL)
                    .destroyTime(0.5f)
            ),
            CreativeModeTab.TAB_TRANSPORTATION);

    public static final RegistrySupplier<Block> BARGE_DOCK = register(
            "barge_dock",
            () -> new BargeDockBlock(Block.Properties.of(Material.METAL)
                    .destroyTime(0.5f)
            ),
            CreativeModeTab.TAB_TRANSPORTATION);

    public static final RegistrySupplier<Block> GUIDE_RAIL_CORNER = register(
            "guide_rail_corner",
            () -> new CornerGuideRailBlock(Block.Properties.of(Material.METAL)
                    .destroyTime(0.5f)
            ),
            CreativeModeTab.TAB_TRANSPORTATION);

    public static final RegistrySupplier<Block> VESSEL_DETECTOR = register(
            "vessel_detector",
            () -> new VesselDetectorBlock(Block.Properties.of(Material.METAL)
                    .destroyTime(0.5f)
            ),
            CreativeModeTab.TAB_TRANSPORTATION);

    public static final RegistrySupplier<Block> GUIDE_RAIL_TUG = register(
            "guide_rail_tug",
            () -> new TugGuideRailBlock(Block.Properties.of(Material.METAL)
                    .destroyTime(0.5f)
            ),
            CreativeModeTab.TAB_TRANSPORTATION);

    public static final RegistrySupplier<Block> FLUID_HOPPER = register(
            "fluid_hopper",
            () -> new FluidHopperBlock(Block.Properties.of(Material.METAL)
                    .destroyTime(0.5f)
            ),
            CreativeModeTab.TAB_TRANSPORTATION);

    public static final RegistrySupplier<Block> VESSEL_CHARGER = register(
            "vessel_charger",
            () -> new VesselChargerBlock(Block.Properties.of(Material.METAL)
                    .destroyTime(0.5f)
            ),
            CreativeModeTab.TAB_TRANSPORTATION);

    public static <T extends Block> RegistrySupplier<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name, block);
    }

    public static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> block, CreativeModeTab group){
        RegistrySupplier<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(group)));
        return ret;
    }

    public static void register () {}
}

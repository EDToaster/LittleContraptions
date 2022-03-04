package dev.murad.littlelogistics.setup;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.murad.littlelogistics.item.*;
import dev.murad.littlelogistics.item.creative.CreativeCapacitor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class LLItems {
    public static final RegistrySupplier<Item> CHEST_BARGE = Registration.ITEMS.register("barge",
            () -> new ChestBargeItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> CHUNK_LOADER_BARGE = Registration.ITEMS.register("chunk_loader_barge",
            () -> new ChunkLoaderBargeItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> FISHING_BARGE = Registration.ITEMS.register("fishing_barge",
            () -> new FishingBargeItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> FLUID_BARGE = Registration.ITEMS.register("fluid_barge",
            () -> new FluidTankBargeItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> SEATER_BARGE = Registration.ITEMS.register("seater_barge",
            () -> new SeaterBargeItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> STEAM_TUG = Registration.ITEMS.register("tug",
            () -> new SteamTugItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> ENERGY_TUG = Registration.ITEMS.register("energy_tug",
            () -> new EnergyTugItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> SPRING = Registration.ITEMS.register("spring",
            () -> new SpringItem(new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> TUG_ROUTE = Registration.ITEMS.register("tug_route",
            () -> new TugRouteItem(new Item.Properties().stacksTo(16).tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static final RegistrySupplier<Item> CREATIVE_CAPACITOR = Registration.ITEMS.register("creative_capacitor",
            () -> new CreativeCapacitor(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static void register () {

    }
}

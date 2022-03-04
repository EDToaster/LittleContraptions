package dev.murad.littlelogistics.setup;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.murad.littlelogistics.entity.accessor.EnergyTugDataAccessor;
import dev.murad.littlelogistics.entity.accessor.SteamTugDataAccessor;
import dev.murad.littlelogistics.entity.accessor.TugRouteScreenDataAccessor;
import dev.murad.littlelogistics.entity.container.EnergyTugContainer;
import dev.murad.littlelogistics.entity.container.FishingBargeContainer;
import dev.murad.littlelogistics.entity.container.SteamTugContainer;
import dev.murad.littlelogistics.item.container.TugRouteContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

public class LLMenuTypes {

    private static SimpleContainerData makeIntArray(FriendlyByteBuf buffer) {
        int size = (buffer.readableBytes() + 1) / 4;
        SimpleContainerData arr = new SimpleContainerData(size);
        for (int i = 0; i < size; i++) {
            arr.set(i, buffer.readInt());
        }
        return arr;
    }

    public static final RegistrySupplier<MenuType<SteamTugContainer>> TUG_CONTAINER =
            Registration.CONTAINERS.register("tug_container",
                    () -> MenuRegistry.ofExtended(
                            (windowId, inv, data) ->
                                    new SteamTugContainer(windowId, inv.player.level, new SteamTugDataAccessor(makeIntArray(data)), inv, inv.player)));

    public static final RegistrySupplier<MenuType<EnergyTugContainer>> ENERGY_TUG_CONTAINER =
            Registration.CONTAINERS.register("energy_tug_container",
                    () -> MenuRegistry.ofExtended(
                            (windowId, inv, data) ->
                                    new EnergyTugContainer(windowId, inv.player.level, new EnergyTugDataAccessor(makeIntArray(data)), inv, inv.player)));

    public static final RegistrySupplier<MenuType<FishingBargeContainer>> FISHING_BARGE_CONTAINER =
            Registration.CONTAINERS.register("fishing_barge_container",
                    () -> MenuRegistry.ofExtended(
                            (windowId, inv, data) ->
                                    new FishingBargeContainer(windowId, inv.player.level, data.readInt(), inv, inv.player)));

    public static final RegistrySupplier<MenuType<TugRouteContainer>> TUG_ROUTE_CONTAINER =
            Registration.CONTAINERS.register("tug_route_container",
                    () -> MenuRegistry.ofExtended(
                            (windowId, inv, data) ->
                                    new TugRouteContainer(windowId, inv.player.level, new TugRouteScreenDataAccessor(makeIntArray(data)), inv, inv.player)));




    public static void register () {}
}

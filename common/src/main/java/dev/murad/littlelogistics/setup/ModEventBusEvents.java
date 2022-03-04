package dev.murad.littlelogistics.setup;

import dev.murad.shipping.ShippingMod;
import dev.murad.littlelogistics.entity.custom.VesselEntity;
import dev.murad.littlelogistics.entity.custom.tug.EnergyTugEntity;
import dev.murad.littlelogistics.entity.custom.tug.SteamTugEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ShippingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(LLEntityTypes.STEAM_TUG.get(), SteamTugEntity.setCustomAttributes().build());
        event.put(LLEntityTypes.ENERGY_TUG.get(), EnergyTugEntity.setCustomAttributes().build());
        event.put(LLEntityTypes.FISHING_BARGE.get(), VesselEntity.setCustomAttributes().build());
        event.put(LLEntityTypes.CHUNK_LOADER_BARGE.get(), VesselEntity.setCustomAttributes().build());
        event.put(LLEntityTypes.FLUID_TANK_BARGE.get(), VesselEntity.setCustomAttributes().build());
        event.put(LLEntityTypes.CHEST_BARGE.get(), VesselEntity.setCustomAttributes().build());
        event.put(LLEntityTypes.SEATER_BARGE.get(), VesselEntity.setCustomAttributes().build());
    }

}
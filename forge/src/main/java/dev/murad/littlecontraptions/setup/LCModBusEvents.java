package dev.murad.littlecontraptions.setup;

import dev.murad.littlecontraptions.LCMod;
import dev.murad.shipping.entity.custom.VesselEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LCMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LCModBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(LCEntityTypes.CONTRAPTION_BARGE.get(), VesselEntity.setCustomAttributes().build());
    }
}

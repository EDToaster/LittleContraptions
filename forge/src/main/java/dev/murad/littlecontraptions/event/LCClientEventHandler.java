package dev.murad.littlecontraptions.event;

import dev.murad.littlecontraptions.LCMod;
import dev.murad.littlecontraptions.entity.models.ContraptionBargeModel;
import dev.murad.littlecontraptions.setup.LCBlocks;
import dev.murad.littlecontraptions.setup.LCEntityTypes;
import dev.murad.shipping.entity.render.barge.StaticVesselRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = LCMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LCClientEventHandler {

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(LCBlocks.BARGE_ASSEMBLER.get(), RenderType.cutoutMipped());
        });
    }

    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(LCEntityTypes.CONTRAPTION_BARGE.get(),
                (ctx) -> new StaticVesselRenderer<>(ctx, ContraptionBargeModel::new, ContraptionBargeModel.LAYER_LOCATION,
                        new ResourceLocation(LCMod.MOD_ID, "textures/entity/contraption_barge.png")));
    }

    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ContraptionBargeModel.LAYER_LOCATION, ContraptionBargeModel::createBodyLayer);
    }
}

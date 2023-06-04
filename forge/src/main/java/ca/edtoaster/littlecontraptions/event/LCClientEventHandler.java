package ca.edtoaster.littlecontraptions.event;

import ca.edtoaster.littlecontraptions.entity.models.ContraptionBargeModel;
import ca.edtoaster.littlecontraptions.setup.LCBlocks;
import ca.edtoaster.littlecontraptions.setup.LCEntityTypes;
import ca.edtoaster.littlecontraptions.LCMod;
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

    @SuppressWarnings("removal")
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemBlockRenderTypes.setRenderLayer(LCBlocks.BARGE_ASSEMBLER.get(), RenderType.cutoutMipped()));
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

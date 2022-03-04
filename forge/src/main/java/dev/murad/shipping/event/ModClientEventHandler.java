package dev.murad.shipping.event;

import dev.murad.littlelogistics.setup.LLBlocks;
import dev.murad.shipping.ShippingMod;
import dev.murad.littlelogistics.block.fluid.render.FluidHopperTileEntityRenderer;
import dev.murad.littlelogistics.entity.models.*;
import dev.murad.littlelogistics.entity.render.ChestBargeRenderer;
import dev.murad.littlelogistics.entity.render.ChunkLoaderBargeRenderer;
import dev.murad.littlelogistics.entity.render.DummyEntityRenderer;
import dev.murad.littlelogistics.entity.render.EnergyTugRenderer;
import dev.murad.littlelogistics.entity.render.FishingBargeRenderer;
import dev.murad.littlelogistics.entity.render.FluidTankBargeRenderer;
import dev.murad.littlelogistics.entity.render.SeaterBargeRenderer;
import dev.murad.littlelogistics.entity.render.SteamTugRenderer;
import dev.murad.littlelogistics.setup.LLEntityTypes;
import dev.murad.littlelogistics.setup.LLBlockEntityTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


/**
 * Mod-specific event bus
 */
@Mod.EventBusSubscriber(modid = ShippingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEventHandler {
    public static final ResourceLocation EMPTY_TUG_ROUTE = new ResourceLocation(ShippingMod.MOD_ID, "item/empty_tug_route");
    public static final ResourceLocation EMPTY_ENERGY = new ResourceLocation(ShippingMod.MOD_ID, "item/empty_energy");
    public static final ResourceLocation EMPTY_ATLAS_LOC = InventoryMenu.BLOCK_ATLAS;

    @SubscribeEvent
    public static void onTextureStitchEventPre(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location() != EMPTY_ATLAS_LOC) return;
        event.addSprite(EMPTY_TUG_ROUTE);
        event.addSprite(EMPTY_ENERGY);
    }

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(LLBlocks.FLUID_HOPPER.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(LLBlocks.VESSEL_CHARGER.get(), RenderType.cutoutMipped());
        });
    }

    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(LLEntityTypes.CHEST_BARGE.get(), ChestBargeRenderer::new);
        event.registerEntityRenderer(LLEntityTypes.CHUNK_LOADER_BARGE.get(), ChunkLoaderBargeRenderer::new);
        event.registerEntityRenderer(LLEntityTypes.ENERGY_TUG.get(), EnergyTugRenderer::new);
        event.registerEntityRenderer(LLEntityTypes.FISHING_BARGE.get(), FishingBargeRenderer::new);
        event.registerEntityRenderer(LLEntityTypes.FLUID_TANK_BARGE.get(), FluidTankBargeRenderer::new);
        event.registerEntityRenderer(LLEntityTypes.SEATER_BARGE.get(), SeaterBargeRenderer::new);
        event.registerEntityRenderer(LLEntityTypes.STEAM_TUG.get(), SteamTugRenderer::new);
        event.registerEntityRenderer(LLEntityTypes.SPRING.get(), DummyEntityRenderer::new);

        event.registerBlockEntityRenderer(LLBlockEntityTypes.FLUID_HOPPER.get(), FluidHopperTileEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ChainExtendedModel.LAYER_LOCATION, ChainExtendedModel::createBodyLayer);
        event.registerLayerDefinition(ChainModel.LAYER_LOCATION, ChainModel::createBodyLayer);
        event.registerLayerDefinition(ChestBargeModel.LAYER_LOCATION, ChestBargeModel::createBodyLayer);
        event.registerLayerDefinition(ChunkLoaderBargeModel.LAYER_LOCATION, ChunkLoaderBargeModel::createBodyLayer);
        event.registerLayerDefinition(EnergyTugModel.LAYER_LOCATION, EnergyTugModel::createBodyLayer);
        event.registerLayerDefinition(FishingBargeDeployedModel.LAYER_LOCATION, FishingBargeDeployedModel::createBodyLayer);
        event.registerLayerDefinition(FishingBargeModel.LAYER_LOCATION, FishingBargeModel::createBodyLayer);
        event.registerLayerDefinition(FishingBargeTransitionModel.LAYER_LOCATION, FishingBargeTransitionModel::createBodyLayer);
        event.registerLayerDefinition(FluidTankBargeModel.LAYER_LOCATION, FluidTankBargeModel::createBodyLayer);
        event.registerLayerDefinition(SeaterBargeModel.LAYER_LOCATION, SeaterBargeModel::createBodyLayer);
        event.registerLayerDefinition(SteamTugModel.LAYER_LOCATION, SteamTugModel::createBodyLayer);

    }


}

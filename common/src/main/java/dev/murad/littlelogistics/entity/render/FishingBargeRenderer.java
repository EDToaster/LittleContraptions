package dev.murad.littlelogistics.entity.render;

import dev.murad.shipping.ShippingMod;
import dev.murad.littlelogistics.entity.custom.barge.FishingBargeEntity;
import dev.murad.littlelogistics.entity.models.FishingBargeDeployedModel;
import dev.murad.littlelogistics.entity.models.FishingBargeModel;
import dev.murad.littlelogistics.entity.models.FishingBargeTransitionModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FishingBargeRenderer extends VesselRenderer<FishingBargeEntity> {

    private static final ResourceLocation BARGE_TEXTURE =
            new ResourceLocation(ShippingMod.MOD_ID, "textures/entity/fishing_barge.png");

    private final EntityModel stashed;
    private final EntityModel transition;
    private final EntityModel deployed;

    public FishingBargeRenderer(EntityRendererProvider.Context context) {
        super(context);
        stashed = new FishingBargeModel(context.bakeLayer(FishingBargeModel.LAYER_LOCATION));
        transition = new FishingBargeTransitionModel(context.bakeLayer(FishingBargeTransitionModel.LAYER_LOCATION));
        deployed = new FishingBargeDeployedModel(context.bakeLayer(FishingBargeDeployedModel.LAYER_LOCATION));
    }

    @Override
    EntityModel getModel(FishingBargeEntity entity) {
        switch (entity.getStatus()) {
            case STASHED:
                return stashed;
            case DEPLOYED:
                return deployed;
            case TRANSITION:
                return transition;
            default:
                throw new IllegalStateException("Unexpected value: " + entity.getStatus());
        }
    }

    @Override
    public ResourceLocation getTextureLocation(FishingBargeEntity p_110775_1_) {
        return BARGE_TEXTURE;
    }
}

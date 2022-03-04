package dev.murad.littlelogistics.entity.render;

import dev.murad.shipping.ShippingMod;
import dev.murad.littlelogistics.entity.custom.barge.ChestBargeEntity;
import dev.murad.littlelogistics.entity.models.ChestBargeModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ChestBargeRenderer extends VesselRenderer<ChestBargeEntity> {
    private static final ResourceLocation BARGE_TEXTURE =
            new ResourceLocation(ShippingMod.MOD_ID, "textures/entity/barge.png");

    private final EntityModel model;

    public ChestBargeRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new ChestBargeModel(context.bakeLayer(ChestBargeModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(ChestBargeEntity entity) {
        return BARGE_TEXTURE;
    }

    @Override
    public EntityModel getModel(ChestBargeEntity entity) {
        return model;
    }
}

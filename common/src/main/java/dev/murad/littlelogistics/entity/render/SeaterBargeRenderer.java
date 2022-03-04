package dev.murad.littlelogistics.entity.render;

import dev.murad.shipping.ShippingMod;
import dev.murad.littlelogistics.entity.custom.barge.SeaterBargeEntity;
import dev.murad.littlelogistics.entity.models.SeaterBargeModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SeaterBargeRenderer extends VesselRenderer<SeaterBargeEntity> {
    private static final ResourceLocation BARGE_TEXTURE =
            new ResourceLocation(ShippingMod.MOD_ID, "textures/entity/seater_barge.png");

    private final EntityModel model;


    public SeaterBargeRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new SeaterBargeModel(context.bakeLayer(SeaterBargeModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(SeaterBargeEntity entity) {
        return BARGE_TEXTURE;
    }

    @Override
    public EntityModel getModel(SeaterBargeEntity entity) {
        return model;
    }
}

package ca.edtoaster.littlecontraptions.ponder.element;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.ponder.PonderScene;
import com.simibubi.create.foundation.ponder.PonderWorld;
import com.simibubi.create.foundation.ponder.element.AnimatedSceneElement;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import dev.murad.shipping.entity.custom.VesselEntity;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.ParameterizedType;

public class VesselElement<T extends VesselEntity> extends AnimatedSceneElement {
    private Vec3 location;
    private LerpedFloat rotation;
    private T entity;
    private EntityConstructor<T> constructor;
    private float initialRotation;
    @Getter
    private final Class<VesselElement<T>> reifiedClass;

    public VesselElement(Vec3 location, float rotation, EntityConstructor<T> constructor) {
        //noinspection unchecked
        this.reifiedClass = (Class<VesselElement<T>>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.initialRotation = rotation;
        this.location = location.add(0.0D, 0.0625D, 0.0D);
        this.constructor = constructor;
        this.rotation = LerpedFloat.angular().startWithValue((double)rotation);
    }

    public void reset(PonderScene scene) {
        super.reset(scene);
        this.entity.setPosRaw(0.0D, 0.0D, 0.0D);
        this.entity.xo = 0.0D;
        this.entity.yo = 0.0D;
        this.entity.zo = 0.0D;
        this.entity.xOld = 0.0D;
        this.entity.yOld = 0.0D;
        this.entity.zOld = 0.0D;
        this.rotation.startWithValue((double)this.initialRotation);
    }

    public void tick(PonderScene scene) {
        super.tick(scene);
        if (this.entity == null) {
            this.entity = this.constructor.create(scene.getWorld(), 0.0D, 0.0D, 0.0D);
        }

        ++this.entity.tickCount;
        this.entity.setOnGround(true);
        this.entity.xo = this.entity.getX();
        this.entity.yo = this.entity.getY();
        this.entity.zo = this.entity.getZ();
        this.entity.xOld = this.entity.getX();
        this.entity.yOld = this.entity.getY();
        this.entity.zOld = this.entity.getZ();
    }

    public void setPositionOffset(Vec3 position, boolean immediate) {
        if (this.entity != null) {
            this.entity.setPos(position.x, position.y, position.z);
            if (immediate) {
                this.entity.xo = position.x;
                this.entity.yo = position.y;
                this.entity.zo = position.z;
            }
        }
    }

    public void setRotation(float angle, boolean immediate) {
        if (this.entity != null) {
            this.rotation.setValue((double)angle);
            if (immediate) {
                this.rotation.startWithValue((double)angle);
            }
        }
    }

    public Vec3 getPositionOffset() {
        return this.entity != null ? this.entity.position() : Vec3.ZERO;
    }

    public Vec3 getRotation() {
        return new Vec3(0.0D, (double)this.rotation.getValue(), 0.0D);
    }

    protected void renderLast(PonderWorld world, MultiBufferSource buffer, PoseStack ms, float fade, float pt) {
        EntityRenderDispatcher entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
        if (this.entity == null) {
            this.entity = this.constructor.create(world, 0.0D, 0.0D, 0.0D);
        }

        ms.pushPose();
        ms.translate(this.location.x, this.location.y, this.location.z);
        ms.translate(Mth.lerp((double)pt, this.entity.xo, this.entity.getX()), Mth.lerp((double)pt, this.entity.yo, this.entity.getY()), Mth.lerp((double)pt, this.entity.zo, this.entity.getZ()));
        TransformStack.cast(ms).rotateY((double)this.rotation.getValue(pt));
        entityrenderermanager.render(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, pt, ms, buffer, this.lightCoordsFromFade(fade));
        ms.popPose();
    }

    public interface EntityConstructor<T extends VesselEntity> {
        T create(Level var1, double var2, double var4, double var6);
    }
}


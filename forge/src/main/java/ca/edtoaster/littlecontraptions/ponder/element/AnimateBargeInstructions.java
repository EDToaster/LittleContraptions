package ca.edtoaster.littlecontraptions.ponder.element;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.instruction.AnimateElementInstruction;
import dev.murad.shipping.entity.custom.VesselEntity;
import net.minecraft.world.phys.Vec3;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class AnimateBargeInstructions<T extends VesselEntity> extends AnimateElementInstruction<VesselElement<T>> {
    public static <T extends VesselEntity> AnimateBargeInstructions<T> rotate(ElementLink<VesselElement<T>> link, float rotation, int ticks) {
        return new AnimateBargeInstructions<>(link, new Vec3(0.0D, (double)rotation, 0.0D), ticks, (wse, v) -> {
            wse.setRotation((float)v.y, ticks == 0);
        }, VesselElement::getRotation);
    }

    public static <T extends VesselEntity> AnimateBargeInstructions<T> move(ElementLink<VesselElement<T>> link, Vec3 offset, int ticks) {
        return new AnimateBargeInstructions<>(link, offset, ticks, (wse, v) -> {
            wse.setPositionOffset(v, ticks == 0);
        }, VesselElement::getPositionOffset);
    }

    protected AnimateBargeInstructions(ElementLink<VesselElement<T>> link, Vec3 totalDelta, int ticks, BiConsumer<VesselElement<T>, Vec3> setter, Function<VesselElement<T>, Vec3> getter) {
        super(link, totalDelta, ticks, setter, getter);
    }
}
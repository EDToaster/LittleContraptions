package ca.edtoaster.littlecontraptions.ponder.element;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.instruction.AnimateElementInstruction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class AnimateVehicleInstructions<T extends Entity> extends AnimateElementInstruction<VehicleElement<T>> {
    public static <T extends Entity> AnimateVehicleInstructions<T> rotate(ElementLink<VehicleElement<T>> link, float rotation, int ticks) {
        return new AnimateVehicleInstructions<>(link, new Vec3(0.0D, (double)rotation, 0.0D), ticks, (wse, v) -> {
            wse.setRotation((float)v.y, ticks == 0);
        }, VehicleElement::getRotation);
    }

    public static <T extends Entity> AnimateVehicleInstructions<T> move(ElementLink<VehicleElement<T>> link, Vec3 offset, int ticks) {
        return new AnimateVehicleInstructions<>(link, offset, ticks, (wse, v) -> {
            wse.setPositionOffset(v, ticks == 0);
        }, VehicleElement::getPositionOffset);
    }

    protected AnimateVehicleInstructions(ElementLink<VehicleElement<T>> link, Vec3 totalDelta, int ticks, BiConsumer<VehicleElement<T>, Vec3> setter, Function<VehicleElement<T>, Vec3> getter) {
        super(link, totalDelta, ticks, setter, getter);
    }
}
package ca.edtoaster.littlecontraptions.ponder.element;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.instruction.FadeIntoSceneInstruction;
import com.simibubi.create.foundation.ponder.instruction.FadeOutOfSceneInstruction;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class VehicleInstructions {

    public static class FadeInVehicleInstruction extends FadeIntoSceneInstruction<VehicleElement> {
        public FadeInVehicleInstruction(int fadeInTicks, Direction fadeInFrom, VehicleElement element) {
            super(fadeInTicks, fadeInFrom, element);
        }

        protected Class<VehicleElement> getElementClass() {
            return VehicleElement.class;
        }
    }


    public static class FadeOutVehicleInstruction extends FadeOutOfSceneInstruction<VehicleElement> {
        public FadeOutVehicleInstruction(int fadeInTicks, Direction fadeInFrom, ElementLink element) {
            super(fadeInTicks, fadeInFrom, element);
        }

        protected Class<VehicleElement> getElementClass() {
            return VehicleElement.class;
        }
    }


    private SceneBuilder builder;

    public VehicleInstructions(SceneBuilder builder) {
        this.builder = builder;
    }

    public <T extends Entity> ElementLink<VehicleElement<T>> createVehicle(Vec3 location, float angle, VehicleElement.EntityConstructor<T> type) {
        VehicleElement<T> cart = new VehicleElement<>(location.subtract(0, 0.5, 0), angle, type);
        @SuppressWarnings("unchecked")
        ElementLink<VehicleElement<T>> link = new ElementLink<>((Class<VehicleElement<T>>) cart.getClass());
        builder.addInstruction(new FadeInVehicleInstruction(10, Direction.DOWN, cart));
        builder.addInstruction((scene) -> {
            scene.linkElement(cart, link);
        });
        return link;
    }

    public <T extends Entity> void rotateVehicle(ElementLink<VehicleElement<T>> link, float yRotation, int duration) {
        builder.addInstruction(AnimateVehicleInstructions.rotate(link, yRotation, duration));
    }

    public <T extends Entity> void moveVehicle(ElementLink<VehicleElement<T>> link, Vec3 offset, int duration) {
        builder.addInstruction(AnimateVehicleInstructions.move(link, offset, duration));
    }

    public <T extends Entity> void removeVehicle(ElementLink<VehicleElement<T>> elem) {
        builder.addInstruction(new FadeOutVehicleInstruction(10, Direction.DOWN, elem));
    }
}

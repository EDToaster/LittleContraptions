package ca.edtoaster.littlecontraptions.ponder.element;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import dev.murad.shipping.entity.custom.VesselEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class VehicleInstructions {
    private SceneBuilder builder;

    public VehicleInstructions(SceneBuilder builder) {
        this.builder = builder;
    }

    public <T extends Entity> ElementLink<VehicleElement<T>> createVessel(Vec3 location, float angle, VehicleElement.EntityConstructor<T> type) {
        VehicleElement<T> cart = new VehicleElement<>(location.subtract(0, 0.5, 0), angle, type);
        @SuppressWarnings("unchecked")
        ElementLink<VehicleElement<T>> link = new ElementLink<>((Class<VehicleElement<T>>) cart.getClass());
        builder.addInstruction(new CreateContraptionBargeInstruction(10, Direction.DOWN, cart));
        builder.addInstruction((scene) -> {
            scene.linkElement(cart, link);
        });
        return link;
    }

    public <T extends Entity> void rotateVessel(ElementLink<VehicleElement<T>> link, float yRotation, int duration) {
        builder.addInstruction(AnimateBargeInstructions.rotate(link, yRotation, duration));
    }

    public <T extends Entity> void moveVessel(ElementLink<VehicleElement<T>> link, Vec3 offset, int duration) {
        builder.addInstruction(AnimateBargeInstructions.move(link, offset, duration));
    }
}

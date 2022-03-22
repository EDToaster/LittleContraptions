package ca.edtoaster.littlecontraptions.ponder.element;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.instruction.PonderInstruction;
import dev.murad.shipping.entity.custom.VesselEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class BargeInstructions {
    private SceneBuilder builder;

    public BargeInstructions(SceneBuilder builder) {
        this.builder = builder;
    }

    public <T extends VesselEntity> ElementLink<VesselElement<T>> createVessel(Vec3 location, float angle, VesselElement.EntityConstructor<T> type) {
        VesselElement<T> cart = new VesselElement<>(location.subtract(0, 0.5, 0), angle, type);
        ElementLink<VesselElement<T>> link = new ElementLink<>(cart.getReifiedClass());
        builder.addInstruction(new CreateContraptionBargeInstruction(10, Direction.DOWN, cart));
        builder.addInstruction((scene) -> {
            scene.linkElement(cart, link);
        });
        return link;
    }

    public <T extends VesselEntity> void rotateVessel(ElementLink<VesselElement<T>> link, float yRotation, int duration) {
        builder.addInstruction(AnimateBargeInstructions.rotate(link, yRotation, duration));
    }

    public <T extends VesselEntity> void moveVessel(ElementLink<VesselElement<T>> link, Vec3 offset, int duration) {
        builder.addInstruction(AnimateBargeInstructions.move(link, offset, duration));
    }
}

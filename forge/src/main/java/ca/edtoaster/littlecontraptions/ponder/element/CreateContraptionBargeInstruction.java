package ca.edtoaster.littlecontraptions.ponder.element;

import com.simibubi.create.foundation.ponder.instruction.FadeIntoSceneInstruction;
import net.minecraft.core.Direction;

public class CreateContraptionBargeInstruction extends FadeIntoSceneInstruction<VehicleElement> {
    public CreateContraptionBargeInstruction(int fadeInTicks, Direction fadeInFrom, VehicleElement element) {
        super(fadeInTicks, fadeInFrom, element);
    }

    protected Class<VehicleElement> getElementClass() {
        return VehicleElement.class;
    }
}

package ca.edtoaster.littlecontraptions.ponder.element;

import com.simibubi.create.foundation.ponder.instruction.FadeIntoSceneInstruction;
import net.minecraft.core.Direction;

public class CreateContraptionBargeInstruction extends FadeIntoSceneInstruction<VesselElement> {
    public CreateContraptionBargeInstruction(int fadeInTicks, Direction fadeInFrom, VesselElement element) {
        super(fadeInTicks, fadeInFrom, element);
    }

    protected Class<VesselElement> getElementClass() {
        return VesselElement.class;
    }
}

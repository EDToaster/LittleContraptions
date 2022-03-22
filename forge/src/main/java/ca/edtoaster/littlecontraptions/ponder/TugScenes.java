package ca.edtoaster.littlecontraptions.ponder;

import ca.edtoaster.littlecontraptions.ponder.element.BargeInstructions;
import ca.edtoaster.littlecontraptions.ponder.element.VesselElement;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import dev.murad.shipping.entity.custom.tug.SteamTugEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.List;

public class TugScenes {
    public static void basicTugScene(SceneBuilder scene, SceneBuildingUtil util) {
        BargeInstructions bargeInst = new BargeInstructions(scene);

        // Setup Scene
        scene.title("basic_tug", "Moving Structures using Barge Assemblers");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75F);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 0, 0, 6)
                .add(util.select.fromTo(2, 0, 0, 6, 0, 4))
                .add(util.select.fromTo(0, 0, 6, 6, 0, 6)), Direction.UP);
        scene.idle(5);

        List<BlockPos> waterPos = List.of(
                of(1, 0, 0),
                of(1, 0, 1),
                of(1, 0, 2),
                of(1, 0, 3),
                of(1, 0, 4),
                of(1, 0, 5),
                of(2, 0, 5),
                of(3, 0, 5),
                of(4, 0, 5),
                of(5, 0, 5),
                of(6, 0, 5));

        for (BlockPos pos : waterPos) {
            scene.world.showSection(util.select.position(pos), Direction.DOWN);
            scene.idle(2);
        }

        // Spawn tug
        ElementLink<VesselElement<SteamTugEntity>> steamTug =
                bargeInst.createVessel(util.vector.topOf(2, 0, 4), 90.0F, SteamTugEntity::new);
        scene.idle(10);

    }

    private static BlockPos of(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }
}

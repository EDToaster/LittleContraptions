package ca.edtoaster.littlecontraptions.ponder;

import ca.edtoaster.littlecontraptions.ponder.element.VehicleInstructions;
import ca.edtoaster.littlecontraptions.ponder.element.VehicleElement;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.ponder.element.WorldSectionElement;
import com.simibubi.create.foundation.utility.Pointing;
import dev.murad.shipping.block.dock.DockingBlockStates;
import dev.murad.shipping.block.rail.AbstractDockingRail;
import dev.murad.shipping.block.rail.SwitchRail;
import dev.murad.shipping.entity.custom.barge.ChestBargeEntity;
import dev.murad.shipping.entity.custom.barge.FishingBargeEntity;
import dev.murad.shipping.entity.custom.train.locomotive.EnergyLocomotiveEntity;
import dev.murad.shipping.entity.custom.train.wagon.ChestCarEntity;
import dev.murad.shipping.entity.custom.tug.EnergyTugEntity;
import dev.murad.shipping.setup.ModBlocks;
import dev.murad.shipping.setup.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;

import java.util.List;

public class LocomotiveScenes {
    private static ItemStack stackOf(Item item) {
        return new ItemStack(item, 1);
    }
    private static BlockPos of(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }


    public static void dockingScene(SceneBuilder scene, SceneBuildingUtil util) {
        VehicleInstructions bargeInst = new VehicleInstructions(scene);

        // Setup Scene
        scene.title("loco_dock", "Docking the locomotive");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75f);
        scene.world.showSection(util.select.fromTo(0, 0, 3, 6, 0, 6), Direction.UP);
        scene.idle(5);

        // add rails

        List<BlockPos> railPos = List.of(
                of(0, 1, 3),
                of(1, 1, 3),
                of(2, 1, 3),
                of(3, 1, 3),
                of(4, 1, 3),
                of(5, 1, 3),
                of(6, 1, 3));

        for (BlockPos pos : railPos) {
            scene.world.showSection(util.select.position(pos), Direction.DOWN);
            scene.idle(2);
        }

        scene.overlay.showText(80)
                .pointAt(util.vector.topOf(of(2, 0, 3)))
                .placeNearTarget()
                .text("Docks can be used to stop the locomotives automatically while loading/unloading");

        scene.idle(100);

        scene.overlay.showText(80)
                .pointAt(util.vector.topOf(of(3, 0, 3)))
                .placeNearTarget()
                .text("Every docking station needs to have one locomotive dock rails and a direct line of car dock rails");

        scene.idle(120);

        scene.overlay.showText(100)
                .pointAt(util.vector.topOf(of(4, 0, 3)))
                .placeNearTarget()
                .text("Train car docks can be switched from blue (wait for unloading) to orange (wait for loading) using conductor's wrench");

        scene.idle(130);

        scene.addKeyframe();

        scene.overlay.showControls((new InputWindowElement(util.vector.topOf(of(4, 0, 3)), Pointing.DOWN))
                .rightClick()
                .withItem(stackOf(ModItems.CONDUCTORS_WRENCH.get())), 30);

        Block bargeDock = ModBlocks.CAR_DOCK_RAIL.get();
        BlockState orangeDock = bargeDock.defaultBlockState()
                .setValue(DockingBlockStates.INVERTED, false)
                .setValue(BaseRailBlock.WATERLOGGED, false)
                .setValue(AbstractDockingRail.RAIL_SHAPE, RailShape.EAST_WEST);

        scene.world.replaceBlocks(util.select.position(of(4, 1, 3)), orangeDock, false);


        // add hoppers
        scene.idle(30);
        scene.world.showSection(util.select.fromTo(0, 1, 4, 6, 1, 6), Direction.UP);

        scene.world.replaceBlocks(util.select.position(of(3, 0, 3)), ModBlocks.RAPID_HOPPER.get().defaultBlockState(), false);


        scene.idle(30);

        scene.overlay.showText(50)
                .pointAt(util.vector.topOf(of(4, 1, 4)))
                .placeNearTarget()
                .text("To load the trains, place a hopper beside the dock");

        scene.idle(70);

        scene.overlay.showText(50)
                .pointAt(util.vector.blockSurface(of(3, 0, 3), Direction.DOWN))
                .placeNearTarget()
                .text("To unload the trains, place a hopper below the rail block.");

        scene.idle(100);

        // add the rest of the surface

        scene.addKeyframe();

        scene.world.showSection(util.select.fromTo(0, 0, 0, 6, 0, 2), Direction.UP);

        // Spawn train

        ElementLink<VehicleElement<Entity>> tug =
                bargeInst.createVessel(util.vector.of(4.5,1.5,3.5), 270.0F, EnergyLocomotiveEntity::new);
        ElementLink<VehicleElement<Entity>> chest1 =
                bargeInst.createVessel(util.vector.of(5.5,1.5,3.5), 270.0F, ChestCarEntity::new);
        ElementLink<VehicleElement<Entity>> chest2 =
                bargeInst.createVessel(util.vector.of(6.5,1.5,3.5), 270.0F, ChestCarEntity::new);

        bargeInst.moveVessel(tug, util.vector.of(-2, 0, 0), 50);
        bargeInst.moveVessel(chest1, util.vector.of(-2, 0, 0), 50);
        bargeInst.moveVessel(chest2, util.vector.of(-2, 0, 0), 50);

        scene.idle(80);

        scene.overlay.showText(70)
                .pointAt(util.vector.topOf(of(2, 0, 3)))
                .placeNearTarget()
                .text("The locomotive will automatically wait until the whole train is loaded/unloaded.");

        scene.idle(100);

        bargeInst.moveVessel(tug, util.vector.of(-2, 0, 0), 50);
        bargeInst.moveVessel(chest1, util.vector.of(-2, 0, 0), 50);
        bargeInst.moveVessel(chest2, util.vector.of(-2, 0, 0), 50);

        scene.idle(5);


    }

    public static void routeScene(SceneBuilder scene, SceneBuildingUtil util) {
        VehicleInstructions bargeInst = new VehicleInstructions(scene);

        // Setup Scene
        scene.title("loco_route", "Locomotive routing");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75f);
        scene.world.showSection(util.select.fromTo(0, 0, 0, 6, 0, 6), Direction.UP);
        scene.idle(5);

        // add rails

        List<BlockPos> railPos = List.of(
                of(0, 1, 5),
                of(1, 1, 5),
                of(2, 1, 5),
                of(3, 1, 5),
                of(4, 1, 5),
                of(5, 1, 5),
                of(6, 1, 5),
                of(3, 1, 2),
                of(3, 1, 3),
                of(3, 1, 4),
                of(0, 1, 1),
                of(1, 1, 1),
                of(2, 1, 1),
                of(3, 1, 1),
                of(4, 1, 1),
                of(5, 1, 1),
                of(6, 1, 1));

        for (BlockPos pos : railPos) {
            scene.world.showSection(util.select.position(pos), Direction.DOWN);
            scene.idle(2);
        }

        scene.world.modifyBlock(of(3,1,5), LocomotiveScenes::flipAutoRail, false);
        scene.idle(100);
        scene.world.modifyBlock(of(3,1,1), LocomotiveScenes::flipAutoRail, false);


        // Spawn train

        ElementLink<VehicleElement<Entity>> tug =
                bargeInst.createVessel(util.vector.of(4.5,1.5,3.5), 270.0F, EnergyLocomotiveEntity::new);

        bargeInst.moveVessel(tug, util.vector.of(-2, 0, 0), 50);


        scene.idle(80);

        bargeInst.moveVessel(tug, util.vector.of(-2, 0, 0), 50);


        scene.idle(5);
    }

    private static BlockState flipAutoRail(BlockState autorail){
        return autorail.setValue(SwitchRail.POWERED, true);
    }
}

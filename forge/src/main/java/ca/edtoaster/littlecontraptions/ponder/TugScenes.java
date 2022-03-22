package ca.edtoaster.littlecontraptions.ponder;

import ca.edtoaster.littlecontraptions.ponder.element.BargeInstructions;
import ca.edtoaster.littlecontraptions.ponder.element.VesselElement;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import dev.murad.shipping.block.guide_rail.CornerGuideRailBlock;
import dev.murad.shipping.entity.custom.tug.SteamTugEntity;
import dev.murad.shipping.item.TugRouteItem;
import dev.murad.shipping.setup.ModBlocks;
import dev.murad.shipping.setup.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TugScenes {
    private static ItemStack stackOf(Item item) {
        return new ItemStack(item, 1);
    }

    public static void basicTugScene(SceneBuilder scene, SceneBuildingUtil util) {
        BargeInstructions bargeInst = new BargeInstructions(scene);

        // Setup Scene
        scene.title("basic_tug", "Just the basics about tugs");
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
        BlockPos steamTugInitialPosition = new BlockPos(4, 0, 5);
        Vec3 steamTugInteractPosition = util.vector.topOf(steamTugInitialPosition).subtract(0.5, 0, 0);

        scene.overlay.showText(100)
                .pointAt(util.vector.topOf(steamTugInitialPosition))
                .placeNearTarget()
                .text("Tugboats can be placed anywhere on water");
        scene.idle(50);
        scene.overlay.showControls((new InputWindowElement(util.vector.topOf(steamTugInitialPosition), Pointing.DOWN))
                .rightClick()
                .withItem(stackOf(ModItems.STEAM_TUG.get())), 50);
        scene.idle(70);

        ElementLink<VesselElement<SteamTugEntity>> steamTug =
                bargeInst.createVessel(util.vector.topOf(steamTugInitialPosition), 270.0F, SteamTugEntity::new);

        scene.idle(20);
        scene.addKeyframe();

        // Tug route scene
        BlockPos waypoint = new BlockPos(1, 0, 0);
        scene.overlay.showText(60)
                .pointAt(util.vector.topOf(waypoint))
                .placeNearTarget()
                .text("Tugboats will naturally pathfind to Tug Route waypoints");

        scene.idle(70);

        ItemStack emptyTugRoute = stackOf(ModItems.TUG_ROUTE.get());
        ItemStack fullTugRoute = stackOf(ModItems.TUG_ROUTE.get());
        TugRouteItem.pushRoute(fullTugRoute, 0, 0);

        // Simulate right click :)
        scene.overlay.showControls((new InputWindowElement(util.vector.topOf(waypoint), Pointing.DOWN))
                .rightClick()
                .withItem(emptyTugRoute), 30);
        scene.idle(37);
        scene.overlay.showControls((new InputWindowElement(util.vector.topOf(waypoint), Pointing.DOWN))
                .rightClick()
                .withItem(fullTugRoute), 50);
        scene.idle(50);

        // Render tugroute item on the water
        ElementLink<EntityElement> itemEntity = scene.world.createItemEntity(
                util.vector.topOf(waypoint),
                util.vector.of(0.0D, 0.0D, 0.0D),
                fullTugRoute);
        scene.world.modifyEntity(itemEntity, e -> e.setNoGravity(true));
        scene.idle(20);

        scene.addKeyframe();

        // Put tugroute in tugboat
        scene.overlay.showText(70)
                .pointAt(steamTugInteractPosition)
                .placeNearTarget()
                .text("Putting the Tug Route item in the Tug will start its journey!");
        scene.idle(35);
        scene.overlay.showControls((new InputWindowElement(steamTugInteractPosition.add(0, 1, 0), Pointing.DOWN))
                .withItem(fullTugRoute), 35);
        scene.idle(45);

        // Start moving tugboat
        bargeInst.moveVessel(steamTug, new Vec3(-2, 0, 0), 25);
        scene.idle(15);
        bargeInst.rotateVessel(steamTug, -35, 10);
        scene.idle(25);

        scene.addKeyframe();

        // Stuck!
        Vec3 turnPosition = new Vec3(2, 1, 5);
        scene.overlay.showText(80)
                .pointAt(turnPosition)
                .placeNearTarget()
                .text("Sometimes tugboats will get stuck on corners. This is fixable by using a corner guide rail");
        scene.idle(100);

        BlockPos guideRailPos = new BlockPos(2, 0, 4);
        Block guideRailBlock = ModBlocks.GUIDE_RAIL_CORNER.get();
        BlockState guideRailRight = guideRailBlock.mirror(guideRailBlock.defaultBlockState(), Mirror.LEFT_RIGHT)
                .setValue(CornerGuideRailBlock.INVERTED, true);
        BlockState guideRailWrong = guideRailBlock.mirror(guideRailBlock.defaultBlockState(), Mirror.LEFT_RIGHT)
                .setValue(CornerGuideRailBlock.INVERTED, false);

        scene.world.replaceBlocks(util.select.position(guideRailPos), guideRailWrong, true);

        scene.idle(20);

        scene.overlay.showText(70)
                .pointAt(turnPosition)
                .placeNearTarget()
                .text("If the corner guide rail is facing the wrong way, shift-right click it to flip it");
        scene.idle(36);
        scene.overlay.showControls((new InputWindowElement(turnPosition.add(.5, 1, -.5), Pointing.DOWN))
                .rightClick()
                .whileSneaking(), 35);
        scene.idle(35);
        scene.world.replaceBlocks(util.select.position(guideRailPos), guideRailRight, false);

        scene.idle(20);

        scene.addKeyframe();

        // Continue!
        bargeInst.moveVessel(steamTug, new Vec3(-1, 0, -1), 10);
        bargeInst.rotateVessel(steamTug, -55, 10);
        scene.idle(10);

        bargeInst.moveVessel(steamTug, new Vec3(0, 0, -3), 36);
        scene.idle(36);
        scene.world.modifyEntity(itemEntity, Entity::discard);

    }

    private static BlockPos of(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }
}

package ca.edtoaster.littlecontraptions.setup;

import ca.edtoaster.littlecontraptions.LCMod;
import ca.edtoaster.littlecontraptions.block.BargeAssemblerBlock;
import ca.edtoaster.littlecontraptions.ponder.AssemblerScenes;
import ca.edtoaster.littlecontraptions.ponder.LocomotiveScenes;
import ca.edtoaster.littlecontraptions.ponder.TugScenes;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import dev.murad.shipping.setup.ModBlocks;
import dev.murad.shipping.setup.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LCPonder {

    public static PonderTag LC_TUGS;
    public static PonderTag LC_LOCOS;

    private static CreateRegistrate createRegistrate = CreateRegistrate.lazy(LCMod.MOD_ID).get();

    public static final BlockEntry<BargeAssemblerBlock> BARGE_ASSEMBLER_ENTRY = new BlockEntry<>(createRegistrate, LCBlocks.BARGE_ASSEMBLER);
    public static final BlockEntry<Block> CORNER_GUIDE_RAIL_BLOCK_BLOCK_ENTRY = new BlockEntry<>(createRegistrate, ModBlocks.GUIDE_RAIL_CORNER);
    public static final BlockEntry<Block> BARGE_DOCK_ENTRY = new BlockEntry<>(createRegistrate, ModBlocks.BARGE_DOCK);
    public static final BlockEntry<Block> TUG_DOCK_ENTRY = new BlockEntry<>(createRegistrate, ModBlocks.TUG_DOCK);
    public static final BlockEntry<Block> LOCO_DOCK_ENTRY = new BlockEntry<>(createRegistrate, ModBlocks.LOCOMOTIVE_DOCK_RAIL);
    public static final BlockEntry<Block> CAR_DOCK_ENTRY = new BlockEntry<>(createRegistrate, ModBlocks.CAR_DOCK_RAIL);
    public static final BlockEntry<Block> AUTO_SWITCH = new BlockEntry<>(createRegistrate, ModBlocks.AUTOMATIC_SWITCH_RAIL);
    public static final BlockEntry<Block> AUTO_TEE = new BlockEntry<>(createRegistrate, ModBlocks.AUTOMATIC_TEE_JUNCTION_RAIL);

    public static final ItemEntry<Item> STEAM_TUG_ITEM_ENTRY = new ItemEntry<>(createRegistrate, ModItems.STEAM_TUG);
    public static final ItemEntry<Item> ENERGY_TUG_ITEM_ENTRY = new ItemEntry<>(createRegistrate, ModItems.ENERGY_TUG);
    public static final ItemEntry<Item> STEAM_LOCOMOTIVE_ENTRY = new ItemEntry<>(createRegistrate, ModItems.STEAM_LOCOMOTIVE);
    public static final ItemEntry<Item> ENERGY_LOCOMOTIVE_ITEM_ENTRY = new ItemEntry<>(createRegistrate, ModItems.ENERGY_LOCOMOTIVE);
    public static final ItemEntry<Item> TUG_ROUTE_ENTRY = new ItemEntry<>(createRegistrate, ModItems.TUG_ROUTE);
    public static final ItemEntry<Item> LOCO_ROUTE_ENTRY = new ItemEntry<>(createRegistrate, ModItems.LOCO_ROUTE);
    public static final ItemEntry<Item> CONTRAPTION_BARGE_ENTRY = new ItemEntry<>(createRegistrate, LCItems.CONTRAPTION_BARGE_ITEM);

    public static void register() {
        LC_TUGS = createPonderTag("tugs").item(ModItems.STEAM_TUG::get, true, false)
                .defaultLang("Little Logistics Tugs",
                        "Water trains with pathfinding!").addToIndex();

        LC_LOCOS = createPonderTag("trains").item(ModItems.STEAM_LOCOMOTIVE::get, true, false)
                .defaultLang("Little Logistics Trains",
                        "Small but smart locomotives!").addToIndex();

        PonderRegistrationHelper HELPER = new PonderRegistrationHelper(LCMod.MOD_ID);

        HELPER.forComponents(BARGE_ASSEMBLER_ENTRY, CONTRAPTION_BARGE_ENTRY)
                .addStoryBoard("basic_assembler", AssemblerScenes::basicAssemblerScene);

        HELPER.forComponents(STEAM_TUG_ITEM_ENTRY, CORNER_GUIDE_RAIL_BLOCK_BLOCK_ENTRY, ENERGY_TUG_ITEM_ENTRY, TUG_ROUTE_ENTRY)
                .addStoryBoard("basic_tug", TugScenes::basicTugScene);

        HELPER.forComponents(BARGE_DOCK_ENTRY, TUG_DOCK_ENTRY, STEAM_TUG_ITEM_ENTRY, ENERGY_TUG_ITEM_ENTRY)
                .addStoryBoard("tug_dock", TugScenes::dockingScene);

        HELPER.forComponents(LOCO_DOCK_ENTRY, CAR_DOCK_ENTRY, STEAM_LOCOMOTIVE_ENTRY, ENERGY_LOCOMOTIVE_ITEM_ENTRY)
                .addStoryBoard("loco_dock", LocomotiveScenes::dockingScene);

        HELPER.forComponents(STEAM_LOCOMOTIVE_ENTRY, ENERGY_LOCOMOTIVE_ITEM_ENTRY, LOCO_ROUTE_ENTRY, AUTO_SWITCH, AUTO_TEE)
                .addStoryBoard("loco_route", LocomotiveScenes::routeScene);

        PonderRegistry.TAGS.forTag(PonderTag.MOVEMENT_ANCHOR)
                .add(BARGE_ASSEMBLER_ENTRY);

        PonderRegistry.TAGS.forTag(LC_LOCOS)
                .add(LOCO_DOCK_ENTRY)
                .add(LOCO_ROUTE_ENTRY)
                .add(STEAM_LOCOMOTIVE_ENTRY)
                .add(ENERGY_LOCOMOTIVE_ITEM_ENTRY)
                .add(AUTO_TEE)
                .add(AUTO_SWITCH)
                .add(CAR_DOCK_ENTRY);

        PonderRegistry.TAGS.forTag(LC_TUGS)
                .add(STEAM_TUG_ITEM_ENTRY)
                .add(CORNER_GUIDE_RAIL_BLOCK_BLOCK_ENTRY)
                .add(ENERGY_TUG_ITEM_ENTRY)
                .add(BARGE_ASSEMBLER_ENTRY)
                .add(CONTRAPTION_BARGE_ENTRY)
                .add(BARGE_DOCK_ENTRY)
                .add(TUG_DOCK_ENTRY)
                .add(TUG_ROUTE_ENTRY);
    }

    private static PonderTag createPonderTag(String id) {
        return new PonderTag(new ResourceLocation(LCMod.MOD_ID, id));
    }
}

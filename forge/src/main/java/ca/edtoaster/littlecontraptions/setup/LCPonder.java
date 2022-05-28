package ca.edtoaster.littlecontraptions.setup;

import ca.edtoaster.littlecontraptions.LCMod;
import ca.edtoaster.littlecontraptions.block.BargeAssemblerBlock;
import ca.edtoaster.littlecontraptions.ponder.AssemblerScenes;
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

    private static CreateRegistrate createRegistrate = CreateRegistrate.lazy(LCMod.MOD_ID).get();

    public static final BlockEntry<BargeAssemblerBlock> BARGE_ASSEMBLER_ENTRY = new BlockEntry<>(createRegistrate, LCBlocks.BARGE_ASSEMBLER);
    public static final BlockEntry<Block> CORNER_GUIDE_RAIL_BLOCK_BLOCK_ENTRY = new BlockEntry<>(createRegistrate, ModBlocks.GUIDE_RAIL_CORNER);
    public static final BlockEntry<Block> BARGE_DOCK_ENTRY = new BlockEntry<>(createRegistrate, ModBlocks.BARGE_DOCK);
    public static final BlockEntry<Block> TUG_DOCK_ENTRY = new BlockEntry<>(createRegistrate, ModBlocks.TUG_DOCK);

    public static final ItemEntry<Item> STEAM_TUG_ITEM_ENTRY = new ItemEntry<>(createRegistrate, ModItems.STEAM_TUG);
    public static final ItemEntry<Item> ENERGY_TUG_ITEM_ENTRY = new ItemEntry<>(createRegistrate, ModItems.ENERGY_TUG);
    public static final ItemEntry<Item> TUG_ROUTE_ENTRY = new ItemEntry<>(createRegistrate, ModItems.TUG_ROUTE);
    public static final ItemEntry<Item> CONTRAPTION_BARGE_ENTRY = new ItemEntry<>(createRegistrate, LCItems.CONTRAPTION_BARGE_ITEM);

    public static void register() {
        LC_TUGS = createPonderTag("tugs").item(ModItems.STEAM_TUG::get, true, false)
                .defaultLang("Little Logistics Tugs",
                        "Water trains with pathfinding!").addToIndex();

        PonderRegistrationHelper HELPER = new PonderRegistrationHelper(LCMod.MOD_ID);

        HELPER.forComponents(BARGE_ASSEMBLER_ENTRY, CONTRAPTION_BARGE_ENTRY)
                .addStoryBoard("basic_assembler", AssemblerScenes::basicAssemblerScene);

        HELPER.forComponents(STEAM_TUG_ITEM_ENTRY, CORNER_GUIDE_RAIL_BLOCK_BLOCK_ENTRY, ENERGY_TUG_ITEM_ENTRY, TUG_ROUTE_ENTRY)
                .addStoryBoard("basic_tug", TugScenes::basicTugScene);

        HELPER.forComponents(BARGE_DOCK_ENTRY, TUG_DOCK_ENTRY)
                .addStoryBoard("tug_dock", TugScenes::dockingScene);

        PonderRegistry.TAGS.forTag(PonderTag.MOVEMENT_ANCHOR)
                .add(BARGE_ASSEMBLER_ENTRY);

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

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
import dev.murad.shipping.setup.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class LCPonder {

    public static PonderTag LC_TUGS;

    private static CreateRegistrate createRegistrate = CreateRegistrate.lazy(LCMod.MOD_ID).get();
    public static final BlockEntry<BargeAssemblerBlock> BARGE_ASSEMBLER_ENTRY = new BlockEntry<>(createRegistrate, LCBlocks.BARGE_ASSEMBLER);
    public static final ItemEntry<Item> STEAM_TUG_ITEM_ENTRY = new ItemEntry<>(createRegistrate, ModItems.STEAM_TUG);

    public static void register() {
        LC_TUGS = createPonderTag("movement_anchor").item(ModItems.STEAM_TUG::get, true, false)
                .defaultLang("Tug Boats",
                        "It's like trains, but with boats!").addToIndex();

        PonderRegistrationHelper HELPER = new PonderRegistrationHelper(LCMod.MOD_ID);

        HELPER.forComponents(BARGE_ASSEMBLER_ENTRY)
                .addStoryBoard("basic_assembler", AssemblerScenes::basicAssemblerScene);

        HELPER.forComponents(STEAM_TUG_ITEM_ENTRY)
                .addStoryBoard("basic_tug", TugScenes::basicTugScene);

        PonderRegistry.TAGS.forTag(PonderTag.MOVEMENT_ANCHOR)
                .add(BARGE_ASSEMBLER_ENTRY);

        PonderRegistry.TAGS.forTag(LC_TUGS)
                .add(STEAM_TUG_ITEM_ENTRY);
    }

    private static PonderTag createPonderTag(String id) {
        return new PonderTag(new ResourceLocation(LCMod.MOD_ID, id));
    }
}

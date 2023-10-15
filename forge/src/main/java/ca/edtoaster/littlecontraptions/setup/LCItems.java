package ca.edtoaster.littlecontraptions.setup;

import ca.edtoaster.littlecontraptions.entity.ContraptionBargeEntity;
import dev.murad.shipping.item.VesselItem;
import dev.murad.shipping.setup.ModBlocks;
import dev.murad.shipping.setup.ModItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class LCItems {

    public static final RegistryObject<Item> CONTRAPTION_BARGE_ITEM = Registration.ITEMS.register("contraption_barge",
            () -> new VesselItem(new Item.Properties(), ContraptionBargeEntity::new));

    public static final RegistryObject<Item> BARGE_ASSEMBLER = Registration.ITEMS.register("barge_assembler",
            () -> new BlockItem(LCBlocks.BARGE_ASSEMBLER.get(), new Item.Properties()));

    public static void register () {
    }

    /**
     * Subscribe to event when building each creative mode tab. Items are added to tabs here.
     * @param event The creative tab currently being built
     */
    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(CONTRAPTION_BARGE_ITEM);
            event.accept(BARGE_ASSEMBLER);
        }
    }
}

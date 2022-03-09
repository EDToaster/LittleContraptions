package dev.murad.littlecontraptions.setup;

import dev.murad.littlecontraptions.item.ContraptionBargeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class LCItems {

    public static final RegistryObject<Item> CONTRAPTION_BARGE_ITEM = Registration.ITEMS.register("contraption_barge",
            () -> new ContraptionBargeItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public static void register () {
    }
}

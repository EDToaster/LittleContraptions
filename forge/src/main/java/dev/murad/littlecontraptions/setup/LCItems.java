package dev.murad.littlecontraptions.setup;

import dev.murad.littlecontraptions.item.ContraptionBargeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class LCItems {

    public static final Registration registration = new Registration();
    static {
        registration.setCurrentTab(CreativeModeTab.TAB_TRANSPORTATION);
    }

    public static final RegistryObject<Item> CONTRAPTION_BARGE_ITEM =
            registration.item("contraption_barge", ContraptionBargeItem::new)
            .register();

    public static void register () {
    }
}

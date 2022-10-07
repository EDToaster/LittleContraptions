package ca.edtoaster.littlecontraptions.setup;

import ca.edtoaster.littlecontraptions.item.ContraptionBargeItem;
import dev.murad.shipping.entity.custom.vessel.barge.SeaterBargeEntity;
import dev.murad.shipping.item.VesselItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class LCItems {

    public static final Registration registration = new Registration();
    static {
        registration.setCurrentTab(CreativeModeTab.TAB_TRANSPORTATION);
    }

    public static final RegistryObject<Item> CONTRAPTION_BARGE_ITEM = dev.murad.shipping.setup.Registration.ITEMS.register("contraption_barge",
            () -> new VesselItem(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION), ContraptionBargeItem::new));

    public static void register () {
    }
}

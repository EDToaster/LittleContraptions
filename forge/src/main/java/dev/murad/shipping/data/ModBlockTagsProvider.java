package dev.murad.shipping.data;

import dev.murad.shipping.ShippingMod;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, ShippingMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {


    }


}

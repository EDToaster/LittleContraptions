package dev.murad.littlecontraptions.data;

import dev.murad.littlecontraptions.LCMod;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, LCMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {


    }


}

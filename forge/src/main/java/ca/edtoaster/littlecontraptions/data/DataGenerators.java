package ca.edtoaster.littlecontraptions.data;

import ca.edtoaster.littlecontraptions.LCMod;
import ca.edtoaster.littlecontraptions.data.client.ModBlockStateProvider;
import ca.edtoaster.littlecontraptions.data.client.ModItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LCMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators () {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent gatherDataEvent){
        DataGenerator gen = gatherDataEvent.getGenerator();
        ExistingFileHelper existingFileHelper = gatherDataEvent.getExistingFileHelper();

        gen.addProvider(true, new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(true, new ModItemModelProvider(gen, existingFileHelper));

        ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(true, modBlockTagsProvider);
        gen.addProvider(true, new ModItemTagsProvider(gen, modBlockTagsProvider, existingFileHelper));
        gen.addProvider(true, new ModLootTableProvider(gen));
        gen.addProvider(true, new ModRecipeProvider(gen));
    }

}

package ca.edtoaster.littlecontraptions.data;

import ca.edtoaster.littlecontraptions.LCMod;
import ca.edtoaster.littlecontraptions.data.client.ModBlockStateProvider;
import ca.edtoaster.littlecontraptions.data.client.ModItemModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LCMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators () {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent gatherDataEvent){
        var gen = gatherDataEvent.getGenerator();
        var existingFileHelper = gatherDataEvent.getExistingFileHelper();
        var pack = gen.getPackOutput();
        var lookupProvider = gatherDataEvent.getLookupProvider();

        gen.addProvider(true, new ModBlockStateProvider(pack, existingFileHelper));
        gen.addProvider(true, new ModItemModelProvider(pack, existingFileHelper));

        ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(pack, lookupProvider, existingFileHelper);
        gen.addProvider(true, modBlockTagsProvider);
        gen.addProvider(true, new ModItemTagsProvider(pack, lookupProvider, modBlockTagsProvider.contentsGetter(), existingFileHelper));
        gen.addProvider(true, new ModLootTableProvider(pack));
        gen.addProvider(true, new ModRecipeProvider(pack));
    }

}

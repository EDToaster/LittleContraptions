package ca.edtoaster.littlecontraptions.setup;

import ca.edtoaster.littlecontraptions.LCMod;
import com.tterrag.registrate.util.OneTimeEventReceiver;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;


public class Registration {

    public static final DeferredRegister<Block> BLOCKS = createRegistry(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<EntityType<?>> ENTITIES = createRegistry(ForgeRegistries.ENTITY_TYPES);
    public static final DeferredRegister<Item> ITEMS = createRegistry(ForgeRegistries.ITEMS);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = createRegistry(ForgeRegistries.BLOCK_ENTITY_TYPES);

    private static<T> DeferredRegister<T> createRegistry(IForgeRegistry<T> registry) {
        return DeferredRegister.create(registry, LCMod.MOD_ID);
    }

    public static void register () {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
        ENTITIES.register(eventBus);

        LCItems.register();
        LCBlocks.register();
        LCBlockEntityTypes.register();
        LCEntityTypes.register();

        OneTimeEventReceiver.addListener(eventBus, FMLClientSetupEvent.class, (event) -> event.enqueueWork(LCPonder::register));
    }
}

package dev.murad.littlecontraptions.setup;

import dev.murad.littlecontraptions.LCMod;
import dev.murad.littlecontraptions.setup.LCBlockEntityTypes;
import dev.murad.littlecontraptions.setup.LCBlocks;
import dev.murad.littlecontraptions.setup.LCEntityTypes;
import dev.murad.littlecontraptions.setup.LCItems;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

import java.util.function.Function;

public class Registration {
    @Setter
    private CreativeModeTab currentTab;

    public Registration() {
        currentTab = CreativeModeTab.TAB_MISC;
    }

    /*
        ITEMS
     */

    public <T extends Item> ItemBuilder<T> item(String id, ItemBuilder.ItemConstructor<T> constructor) {
        return new ItemBuilder<>(id, constructor);
    }

    @RequiredArgsConstructor
    public class ItemBuilder<T extends Item> {
        @Setter
        private Item.Properties properties = new Item.Properties().tab(currentTab);

        private final String id;
        private final ItemConstructor<T> constructor;

        public ItemBuilder<T> transformProperties(Function<Item.Properties, Item.Properties> propFunc) {
            properties = propFunc.apply(properties);
            return this;
        }

        public RegistryObject<Item> register() {
            return ITEMS.register(id, () -> constructor.construct(properties));
        }

        @FunctionalInterface
        public interface ItemConstructor<T extends Item> {
            T construct(Item.Properties properties);
        }
    }

    /*
        BLOCKS
     */
    public <T extends Block> BlockBuilder<T> block(String id, BlockBuilder.BlockConstructor<T> constructor) {
        return new BlockBuilder<>(id, constructor);
    }

    @RequiredArgsConstructor
    public class BlockBuilder<T extends Block> {
        @Setter
        private BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(Material.METAL);

        private final String id;
        private final BlockConstructor<T> constructor;
        boolean createSimpleItem = false;

        public BlockBuilder<T> baseMaterial(Material mat) {
            properties = BlockBehaviour.Properties.of(mat);
            return this;
        }

        public BlockBuilder<T> destroyTime(float time) {
            properties.destroyTime(time);
            return this;
        }

        public BlockBuilder<T> noCollision() {
            properties.noCollission();
            return this;
        }

        public BlockBuilder<T> simpleItem() {
            createSimpleItem = true;
            return this;
        }

        public RegistryObject<T> register() {
            RegistryObject<T> ret = BLOCKS.register(id, () -> constructor.construct(properties));
            if (createSimpleItem) {
                ITEMS.register(id, () -> new BlockItem(ret.get(), new Item.Properties().tab(currentTab)));
            }
            return ret;
        }

        @FunctionalInterface
        public interface BlockConstructor<T extends Block> {
            T construct(BlockBehaviour.Properties properties);
        }
    }


    public static final DeferredRegister<Block> BLOCKS = create(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<EntityType<?>> ENTITIES = create(ForgeRegistries.ENTITIES);
    public static final DeferredRegister<Item> ITEMS = create(ForgeRegistries.ITEMS);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = create(ForgeRegistries.BLOCK_ENTITIES);

    private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> create(IForgeRegistry<T> registry) {
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
    }
}

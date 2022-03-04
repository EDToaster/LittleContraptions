package dev.murad.littlelogistics.setup;

import dev.architectury.registry.registries.DeferredRegister;
import dev.murad.littlelogistics.LLMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = create(Registry.ITEM_REGISTRY);
    public static final DeferredRegister<Block> BLOCKS = create(Registry.BLOCK_REGISTRY);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = create(Registry.BLOCK_ENTITY_TYPE_REGISTRY);
    public static final DeferredRegister<EntityType<?>> ENTITIES = create(Registry.ENTITY_TYPE_REGISTRY);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = create(Registry.SOUND_EVENT_REGISTRY);

    public static final DeferredRegister<MenuType<?>> CONTAINERS = create(Registry.MENU_REGISTRY);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = create(Registry.RECIPE_SERIALIZER_REGISTRY);

    public static void register() {
        ITEMS.register();
        BLOCKS.register();
        ENTITIES.register();
        CONTAINERS.register();
        RECIPE_SERIALIZERS.register();
    }

    private static <T> DeferredRegister<T> create(ResourceKey<Registry<T>> registry) {
        return DeferredRegister.create(LLMod.MOD_ID, registry);
    }
}

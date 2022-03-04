package dev.murad.littlelogistics.setup;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.murad.littlelogistics.recipe.TugRouteRecipe;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;

public class LLRecipeSerializers {

    public static final RegistrySupplier<SimpleRecipeSerializer<TugRouteRecipe>> TUG_ROUTE_COPY = Registration.RECIPE_SERIALIZERS.register(
            "tug_route_copy", () -> new SimpleRecipeSerializer<>(TugRouteRecipe::new));

    public static void register () {

    }
}

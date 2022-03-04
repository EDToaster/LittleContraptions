package dev.murad.littlelogistics.setup;

import dev.murad.littlelogistics.item.SpringItem;
import dev.murad.littlelogistics.item.TugRouteItem;
import net.minecraft.client.renderer.item.ItemProperties;

import static dev.murad.littlelogistics.util.Location.modLoc;


public class ModItemModelProperties {

    public static void register() {
        ItemProperties.register(LLItems.SPRING.get(),
                modLoc("springstate"), (stack, world, entity, i) -> {
                    return SpringItem.getState(stack).equals(SpringItem.State.READY) ? 0 : 1;
                });

        ItemProperties.register(LLItems.TUG_ROUTE.get(),
                modLoc("routestate"), (stack, world, entity, i) -> {
                    return !TugRouteItem.getRoute(stack).isEmpty() ? 0 : 1;
                });
    }

}

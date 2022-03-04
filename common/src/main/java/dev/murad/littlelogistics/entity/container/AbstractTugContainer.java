package dev.murad.littlelogistics.entity.container;

import dev.murad.littlelogistics.entity.accessor.DataAccessor;
import dev.murad.littlelogistics.entity.custom.tug.AbstractTugEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public abstract class AbstractTugContainer<T extends DataAccessor> extends AbstractItemHandlerContainer {
    protected T data;
    protected AbstractTugEntity tugEntity;

    public AbstractTugContainer(@Nullable MenuType<?> containerType, int windowId, Level world, T data,
                                Inventory playerInventory, Player player) {
        super(containerType, windowId, playerInventory, player);
        this.tugEntity = (AbstractTugEntity) world.getEntity(data.getEntityUUID());
        this.data = data;
        layoutPlayerInventorySlots(8, 84);
        this.addDataSlots(data);
    }
}

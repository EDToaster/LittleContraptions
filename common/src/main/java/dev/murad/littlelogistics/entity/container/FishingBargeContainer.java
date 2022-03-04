package dev.murad.littlelogistics.entity.container;

import dev.murad.littlelogistics.entity.custom.barge.FishingBargeEntity;
import dev.murad.littlelogistics.setup.LLMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class FishingBargeContainer extends AbstractItemHandlerContainer {
    private final FishingBargeEntity fishingBargeEntity;

    public FishingBargeContainer(int windowId, Level world, int entityId,
                                    Inventory playerInventory, Player player) {
        super(LLMenuTypes.FISHING_BARGE_CONTAINER.get(), windowId, playerInventory, player);
        this.fishingBargeEntity = (FishingBargeEntity) world.getEntity(entityId);
        layoutPlayerInventorySlots(8, 49);

        if(fishingBargeEntity != null) {
            fishingBargeEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                for(int k = 0; k < 9; ++k) {
                    this.addSlot(new SlotItemHandler(h, k, 8 + k * 18, 18));
                }
            });
        }
    }

    @Override
    protected int getSlotNum() {
        return 9;
    }

    @Override
    public boolean stillValid(Player p_75145_1_) {
        return fishingBargeEntity.stillValid(p_75145_1_);
    }
}

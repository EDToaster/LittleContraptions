package ca.edtoaster.littlecontraptions.item;

import ca.edtoaster.littlecontraptions.entity.ContraptionBargeEntity;

import ca.edtoaster.littlecontraptions.setup.LCItems;
import com.mojang.datafixers.util.Function4;

import dev.murad.shipping.entity.custom.vessel.barge.AbstractBargeEntity;
import dev.murad.shipping.entity.custom.vessel.barge.SeaterBargeEntity;
import dev.murad.shipping.item.VesselItem;
import dev.murad.shipping.setup.ModEntityTypes;
import dev.murad.shipping.setup.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;


import java.util.Optional;


// TODO: Refactor Item in LL to reduce class bloat
public class ContraptionBargeItem  extends AbstractBargeEntity {

    public ContraptionBargeItem(EntityType<? extends AbstractBargeEntity> type, Level world) {
        super(type, world);
    }

    @Override
    public Item getDropItem() {
        return LCItems.CONTRAPTION_BARGE_ITEM.get();
    }

    @Override
    protected void doInteract(Player player) {

    }

}

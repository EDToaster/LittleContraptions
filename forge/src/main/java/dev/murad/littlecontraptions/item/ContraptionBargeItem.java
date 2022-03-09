package dev.murad.littlecontraptions.item;

import dev.murad.littlecontraptions.entity.ContraptionBargeEntity;
import dev.murad.shipping.item.AbstractEntityAddItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

// TODO: Refactor Item in LL to reduce class bloat
public class ContraptionBargeItem  extends AbstractEntityAddItem {
    public ContraptionBargeItem(Properties p_i48526_2_) {
        super(p_i48526_2_);
    }

    @Override
    protected Entity getEntity(Level level, BlockHitResult raytraceresult) {
        return new ContraptionBargeEntity(level, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
    }
}

package dev.murad.littlelogistics.item;

import dev.murad.littlelogistics.entity.custom.tug.SteamTugEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

public class SteamTugItem extends AbstractEntityAddItem {
    public SteamTugItem(Properties p_i48526_2_) {
        super(p_i48526_2_);
    }

    protected Entity getEntity(Level world, BlockHitResult raytraceresult) {
        return new SteamTugEntity(world, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
    }

}

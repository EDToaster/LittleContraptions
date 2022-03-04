package dev.murad.littlelogistics.item;

import dev.murad.littlelogistics.entity.custom.tug.EnergyTugEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

public class EnergyTugItem extends AbstractEntityAddItem {
    public EnergyTugItem(Properties props) {
        super(props);
    }

    protected Entity getEntity(Level world, BlockHitResult raytraceresult) {
        return new EnergyTugEntity(world, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
    }
}

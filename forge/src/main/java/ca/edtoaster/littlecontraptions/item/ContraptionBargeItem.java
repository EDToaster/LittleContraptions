package ca.edtoaster.littlecontraptions.item;

import ca.edtoaster.littlecontraptions.entity.ContraptionBargeEntity;

import com.mojang.datafixers.util.Function4;

import dev.murad.shipping.item.VesselItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;


import java.util.Optional;


// TODO: Refactor Item in LL to reduce class bloat
public class ContraptionBargeItem  extends VesselItem {

    private final Function4<Level, Double, Double, Double, Entity> addEntity;
    private Optional<String> tooltipLocation = Optional.empty();


    public ContraptionBargeItem(Properties p_i48526_2_,Function4<Level, Double, Double, Double, Entity> addEntity, String tooltip) {
        super(p_i48526_2_);
        this.addEntity = addEntity;
        this.tooltipLocation = Optional.of(tooltip);
    }

    public ContraptionBargeItem(Properties p_i48526_2_, Function4<Level, Double, Double, Double, Entity> addEntity) {
        super( p_i48526_2_);
        this.addEntity = addEntity;
    }

    @Override
    protected Entity getEntity(Level level, BlockHitResult raytraceresult) {
        return new ContraptionBargeEntity(level, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
    }
}

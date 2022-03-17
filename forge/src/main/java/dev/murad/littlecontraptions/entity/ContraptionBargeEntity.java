package dev.murad.littlecontraptions.entity;

import com.simibubi.create.content.contraptions.components.structureMovement.train.capability.CapabilityMinecartController;
import com.simibubi.create.content.contraptions.components.structureMovement.train.capability.MinecartController;
import dev.murad.littlecontraptions.setup.LCEntityTypes;
import dev.murad.littlecontraptions.setup.LCItems;
import dev.murad.shipping.entity.custom.barge.AbstractBargeEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class ContraptionBargeEntity extends AbstractBargeEntity {

    private final BargeController controller = new BargeController(this);
    private final LazyOptional<MinecartController> controllerOpt = LazyOptional.of(() -> controller);

    public ContraptionBargeEntity(EntityType<? extends ContraptionBargeEntity> type, Level world) {
        super(type, world);
    }

    public ContraptionBargeEntity(Level worldIn, double x, double y, double z) {
        super(LCEntityTypes.CONTRAPTION_BARGE.get(), worldIn, x, y, z);
    }

    @Override
    protected boolean canAddPassenger(Entity p_184219_1_) {
        return this.getPassengers().size() < 1;
    }

    @Override
    public Item getDropItem() {
        return LCItems.CONTRAPTION_BARGE_ITEM.get();
    }

    @Override
    protected void doInteract(Player player) {
        // no op
    }

    public void positionRider(Entity entity) {
        if (this.hasPassenger(entity)) {
            entity.setPos(this.getX(), this.getY() + 0.53125, this.getZ());
        }
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == CapabilityMinecartController.MINECART_CONTROLLER_CAPABILITY) {
            return controllerOpt.cast();
        }
        return super.getCapability(cap);
    }
}

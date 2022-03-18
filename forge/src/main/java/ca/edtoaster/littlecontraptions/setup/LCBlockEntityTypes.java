package ca.edtoaster.littlecontraptions.setup;

import ca.edtoaster.littlecontraptions.block.BargeAssemblerBlockEntity;
import dev.murad.shipping.setup.Registration;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class LCBlockEntityTypes {
    // create
    public static final RegistryObject<BlockEntityType<BargeAssemblerBlockEntity>> BARGE_ASSEMBLER = register(
            "barge_assembler",
            BargeAssemblerBlockEntity::new,
            LCBlocks.BARGE_ASSEMBLER
    );

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            RegistryObject<? extends Block> block) {
        return Registration.TILE_ENTITIES.register(name, () ->
                BlockEntityType.Builder.of(factory, block.get()).build(null));
    }

    public static void register () {

    }
}

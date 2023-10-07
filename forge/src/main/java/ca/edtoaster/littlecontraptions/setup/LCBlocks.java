package ca.edtoaster.littlecontraptions.setup;

import ca.edtoaster.littlecontraptions.block.BargeAssemblerBlock;
import ca.edtoaster.littlecontraptions.block.FuseClutch;
import com.simibubi.create.content.CreateItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class LCBlocks {
    public static Registration registration = new Registration();
    static {
        registration.setCurrentTab(CreativeModeTab.TAB_TRANSPORTATION);
    }

    public static final RegistryObject<BargeAssemblerBlock> BARGE_ASSEMBLER = registration.block("barge_assembler", BargeAssemblerBlock::new)
            .baseMaterial(Material.METAL)
            .noCollision()
            .destroyTime(0.5f)
            .simpleItem()
            .register();

    public static final RegistryObject<FuseClutch> FUSE_CLUTCH = registration.block("fuse_clutch", FuseClutch::new)
            .baseMaterial(Material.METAL)
            .noCollision()
            .destroyTime(0.5f)
            .simpleItem()
            .register();

    public static void register () {}
}

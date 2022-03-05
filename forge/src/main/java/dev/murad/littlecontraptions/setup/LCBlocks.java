package dev.murad.littlecontraptions.setup;

import dev.murad.littlecontraptions.block.BargeAssemblerBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class LCBlocks {
    public static final RegistryObject<Block> BARGE_ASSEMBLER = register(
        "barge_assembler",
        () -> new BargeAssemblerBlock(BlockBehaviour.Properties.of(Material.METAL).noCollission()
                .destroyTime(0.5f)
        ),
        CreativeModeTab.TAB_TRANSPORTATION);

    public static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name, block);
    }

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, CreativeModeTab group){
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(group)));
        return ret;
    }

    public static void register () {}
}

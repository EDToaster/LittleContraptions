package dev.murad.littlecontraptions.data.client;

import com.simibubi.create.Create;
import dev.murad.littlecontraptions.LCMod;
import dev.murad.littlecontraptions.setup.LCBlocks;
import dev.murad.shipping.block.energy.VesselChargerBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, LCMod.MOD_ID, exFileHelper);
    }

    public static ResourceLocation getBlTx(String name){
        return new ResourceLocation(LCMod.MOD_ID, String.format("block/%s", name));
    }

    private int xRotFromDir(Direction direction){
        switch (direction) {
            case DOWN:
                return 270;
            case UP:
                return 90;
            default:
                return 0;
        }
    }


    @Override
    protected void registerStatesAndModels() {
        getVariantBuilder(LCBlocks.BARGE_ASSEMBLER.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(models().withExistingParent(
                            "barge_assembler",
                            new ResourceLocation(Create.ID, "block/cart_assembler/block")))
                        .rotationY((int) state.getValue(VesselChargerBlock.FACING).getOpposite().toYRot())
                        .build()
        );
    }
}

package ca.edtoaster.littlecontraptions.data.client;

import ca.edtoaster.littlecontraptions.setup.LCBlocks;
import com.simibubi.create.Create;
import ca.edtoaster.littlecontraptions.LCMod;
import ca.edtoaster.littlecontraptions.block.BargeAssemblerBlock;
import dev.murad.shipping.block.energy.VesselChargerBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput out, ExistingFileHelper exFileHelper) {
        super(out, LCMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        getVariantBuilder(LCBlocks.BARGE_ASSEMBLER.get()).forAllStates(state -> {
            String suffix = state.getValue(BargeAssemblerBlock.POWERED) ? "_on" : "_off";
            return ConfiguredModel.builder()
                    .modelFile(models().withExistingParent(
                            "barge_assembler" + suffix,
                            new ResourceLocation(Create.ID, "block/cart_assembler/block"))
                            .texture("4", modLoc("block/barge_assembler_top"))
                            .texture("6", modLoc("block/barge_assembler_side" + suffix))
                            .texture("rail", modLoc("block/blank"))
                            .texture("particle", modLoc("block/barge_assembler_side"))
                            .texture("translation_chassis_side", modLoc("block/barge_assembler_side")))
                    .rotationY((int) state.getValue(VesselChargerBlock.FACING).getOpposite().toYRot())
                    .build();
        });
    }
}

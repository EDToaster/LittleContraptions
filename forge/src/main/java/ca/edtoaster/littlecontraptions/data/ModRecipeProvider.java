package ca.edtoaster.littlecontraptions.data;

import ca.edtoaster.littlecontraptions.setup.LCBlocks;
import ca.edtoaster.littlecontraptions.setup.LCItems;
import com.simibubi.create.AllItems;
import dev.murad.shipping.setup.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(LCBlocks.BARGE_ASSEMBLER.get())
                .define('L', ItemTags.LOGS)
                .define('A', AllItems.ANDESITE_ALLOY.get())
                .define('R', Items.REDSTONE)
                .define('C', ModItems.SPRING.get())
                .pattern(" L ")
                .pattern("ARA")
                .pattern("LCL")
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(LCItems.CONTRAPTION_BARGE_ITEM.get())
                .define('B', AllItems.ANDESITE_ALLOY.get())
                .define('A', AllItems.BRASS_INGOT.get())
                .define('$', Items.IRON_INGOT)
                .pattern("ABA")
                .pattern("$$$")
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY.get()))
                .save(consumer);
    }
}

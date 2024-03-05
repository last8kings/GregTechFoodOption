package gregtechfoodoption.recipe.chain;

import de.ellpeck.actuallyadditions.mod.blocks.InitBlocks;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.integration.jei.JEIGTFOPlugin;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Paper;
import static gregtech.api.unification.material.Materials.Water;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class CoffeeChain {
    public static void init() {
        if (GTFOConfig.gtfoaaConfig.disableCoffeeMaker)
            ModHandler.removeRecipeByOutput(new ItemStack(InitBlocks.blockCoffeeMachine));

        if (Loader.isModLoaded(GTFOValues.MODID_AA)) {
            JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(InitItems.itemCoffee));
            JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(InitItems.itemCoffeeSeed));
            JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(InitItems.itemCoffeeBean));
            JEIGTFOPlugin.itemStacksToHide.add(new ItemStack(InitBlocks.blockCoffeeMachine));
        }

        ItemStack emptyCoffeeMug = GTFOMetaItem.EMPTY_MUG.getStackForm();
        ItemStack coffeeFilter = GTFOMetaItem.PAPER_CONE.getStackForm();

        FORMING_PRESS_RECIPES.recipeBuilder()
                .input(Items.CLAY_BALL, 5)
                .notConsumable(PAPER_CONE.getStackForm())
                .outputs(UNFIRED_MUG.getStackForm(2))
                .EUt(30)
                .duration(200)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .inputs(UNFIRED_MUG.getStackForm(16))
                .outputs(EMPTY_MUG.getStackForm(16))
                .blastFurnaceTemp(1033)
                .EUt(120)
                .duration(1200)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.Coffee.getFluid(100))
                .inputs(emptyCoffeeMug)
                .outputs(GTFOMetaItem.COFFEE_MUG.getStackForm())
                .EUt(40)
                .duration(20)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.EnergizedCoffee.getFluid(100))
                .inputs(emptyCoffeeMug)
                .outputs(GTFOMetaItem.ENERGIZING_COFFEE_MUG.getStackForm())
                .EUt(40)
                .duration(20)
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.Coffee.getFluid(10))
                .input(Items.SUGAR)
                .fluidOutputs(GTFOMaterialHandler.EnergizedCoffee.getFluid(10))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.COFFEE_GROUNDS.getItemStack())
                .inputs(coffeeFilter) // This is a paper cone, or a coffee filter, if you like.
                .fluidInputs(Materials.Steam.getFluid(1000))
                .output(dust, Paper, 1)
                .fluidOutputs(GTFOMaterialHandler.Coffee.getFluid(15))
                .EUt(120)
                .duration(30)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.PAPER)
                .circuitMeta(1)
                .outputs(coffeeFilter)
                .EUt(30)
                .duration(14)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.LARGE_ROASTED_COFFEE.getItemStack())
                .outputs(GTFOMaterialHandler.COFFEE_GROUNDS.getItemStack(2))
                .EUt(20)
                .duration(40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.SMALL_ROASTED_COFFEE.getItemStack())
                .outputs(GTFOMaterialHandler.COFFEE_GROUNDS.getItemStack())
                .EUt(20)
                .duration(20)
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.LARGE_GRADED_COFFEE.getItemStack())
                .circuitMeta(1)
                .outputs(GTFOMaterialHandler.LARGE_ROASTED_COFFEE.getItemStack())
                .fluidOutputs(Water.getFluid(200))
                .EUt(120)
                .duration(80)
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.SMALL_GRADED_COFFEE.getItemStack())
                .circuitMeta(1)
                .outputs(GTFOMaterialHandler.SMALL_ROASTED_COFFEE.getItemStack())
                .fluidOutputs(Water.getFluid(100))
                .EUt(120)
                .duration(40)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.LARGE_HULLED_COFFEE.getItemStack())
                .notConsumable(MetaItems.SENSOR_LV)
                .chancedOutput(GTFOMaterialHandler.LARGE_GRADED_COFFEE.getItemStack(), 5000, 10)
                .EUt(60)
                .duration(20)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.SMALL_HULLED_COFFEE.getItemStack())
                .notConsumable(MetaItems.SENSOR_LV)
                .chancedOutput(GTFOMaterialHandler.SMALL_GRADED_COFFEE.getItemStack(), 5000, 10)
                .EUt(60)
                .duration(10)
                .buildAndRegister();
        LATHE_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.SMALL_DRIED_COFFEE.getItemStack())
                .outputs(GTFOMaterialHandler.SMALL_HULLED_COFFEE.getItemStack())
                .EUt(30)
                .duration(10)
                .buildAndRegister();
        LATHE_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.LARGE_DRIED_COFFEE.getItemStack())
                .outputs(GTFOMaterialHandler.LARGE_HULLED_COFFEE.getItemStack())
                .EUt(30)
                .duration(10)
                .buildAndRegister();


        GTFOUtils.chemicalDehydratorProxy().recipeBuilder()
                .inputs(GTFOMaterialHandler.LARGE_WET_COFFEE.getItemStack(32))
                .outputs(GTFOMaterialHandler.LARGE_DRIED_COFFEE.getItemStack(32))
                .fluidOutputs(Water.getFluid(16000))
                .EUt(30)
                .duration(3600)
                .buildAndRegister();

        GTFOUtils.chemicalDehydratorProxy().recipeBuilder()
                .inputs(GTFOMaterialHandler.SMALL_WET_COFFEE.getItemStack(64))
                .outputs(GTFOMaterialHandler.SMALL_DRIED_COFFEE.getItemStack(64))
                .fluidOutputs(Water.getFluid(16000))
                .EUt(30)
                .duration(1800)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.LARGE_BASIC_COFFEE.getItemStack(32))
                .outputs(GTFOMaterialHandler.LARGE_WET_COFFEE.getItemStack(32))
                .fluidInputs(Water.getFluid(16000))
                .EUt(60)
                .duration(3600)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.SMALL_BASIC_COFFEE.getItemStack(64))
                .outputs(GTFOMaterialHandler.SMALL_WET_COFFEE.getItemStack(64))
                .fluidInputs(Water.getFluid(16000))
                .EUt(60)
                .duration(3600)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(COFFEE_SEED.getStackForm(30))
                .outputs(GTFOMaterialHandler.LARGE_BASIC_COFFEE.getItemStack(9))
                .chancedOutput(GTFOMaterialHandler.LARGE_BASIC_COFFEE.getItemStack(), 5000, 200)
                .outputs(GTFOMaterialHandler.SMALL_BASIC_COFFEE.getItemStack(19))
                .chancedOutput(GTFOMaterialHandler.SMALL_BASIC_COFFEE.getItemStack(), 5000, 200)
                .EUt(20)
                .duration(600)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.UNSORTED_BASIC_COFFEE.getItemStack(30))
                .outputs(GTFOMaterialHandler.LARGE_BASIC_COFFEE.getItemStack(9))
                .chancedOutput(GTFOMaterialHandler.LARGE_BASIC_COFFEE.getItemStack(), 5000, 200)
                .outputs(GTFOMaterialHandler.SMALL_BASIC_COFFEE.getItemStack(19))
                .chancedOutput(GTFOMaterialHandler.SMALL_BASIC_COFFEE.getItemStack(), 5000, 200)
                .EUt(20)
                .duration(600)
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder()
                .inputs(GTFOMetaItem.COFFEE_CHERRY.getStackForm())
                .outputs(COFFEE_SEED.getStackForm(9))
                .chancedOutput(COFFEE_SEED.getStackForm(), 5000, 500)
                .EUt(60)
                .duration(20)
                .buildAndRegister();

        //RecipeUtils.addGreenHouseRecipes(new ItemStack(InitItems.itemCoffeeSeed), InitItems.itemCoffeeBean);
    }
}

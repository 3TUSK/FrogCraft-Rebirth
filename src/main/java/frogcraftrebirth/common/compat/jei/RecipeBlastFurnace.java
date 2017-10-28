package frogcraftrebirth.common.compat.jei;

import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeBlastFurnace implements IRecipeWrapper {

	private final IAdvBlastFurnaceRecipe recipe;

	public RecipeBlastFurnace(IAdvBlastFurnaceRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, Stream.of(recipe.getInput(), recipe.getInputSecondary()).map(i -> i.getActualInputs(ItemStack.class)).collect(Collectors.toList()));
		ingredients.setInputs(FluidStack.class, Arrays.asList(recipe.getInputFluid(), new FluidStack(recipe.getShieldGas(), 1000)));
		ingredients.setOutputs(ItemStack.class, Arrays.asList(recipe.getOutput(), recipe.getByproduct()));
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}
}

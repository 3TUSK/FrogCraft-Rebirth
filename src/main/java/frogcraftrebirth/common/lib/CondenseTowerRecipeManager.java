package frogcraftrebirth.common.lib;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IRecipeManager;
import net.minecraftforge.fluids.FluidStack;

public class CondenseTowerRecipeManager implements IRecipeManager<ICondenseTowerRecipe> {

	@Override
	public void add(ICondenseTowerRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(ICondenseTowerRecipe recipe) {
		recipes.remove(recipe);
	}
	
	@Override
	public Collection<ICondenseTowerRecipe> getRecipes() {
		return recipes;
	}

	@Override
	public ICondenseTowerRecipe getRecipe(IFrogRecipeInput... inputs) {
		IFrogRecipeInput input = inputs[0];
		if (input == null)
			return null;
		List<FluidStack> inputFluid = input.getActualInputs(FluidStack.class);
		if (inputFluid.size() > 0) {
			FluidStack first = inputFluid.get(0);
			for (ICondenseTowerRecipe recipe : recipes) {
				if (recipe.getInput().isFluidEqual(first)) {
					return recipe;
				}
			}
		}

		return null;
	}

	@Override
	public ICondenseTowerRecipe getRecipe(Iterable<IFrogRecipeInput> inputs) {
		IFrogRecipeInput input = inputs.iterator().next();
		if (input == null)
			return null;
		List<FluidStack> inputFluid = input.getActualInputs(FluidStack.class);
		if (inputFluid.size() > 0) {
			FluidStack first = inputFluid.get(0);
			for (ICondenseTowerRecipe recipe : recipes) {
				if (recipe.getInput().isFluidEqual(first)) {
					return recipe;
				}
			}
		}

		return null;
	}

	private final Set<ICondenseTowerRecipe> recipes = new HashSet<>();

}

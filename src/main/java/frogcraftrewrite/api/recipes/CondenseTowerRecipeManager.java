package frogcraftrewrite.api.recipes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraftforge.fluids.FluidStack;

public class CondenseTowerRecipeManager implements IRecipeManager<CondenseTowerRecipe> {
	
	public CondenseTowerRecipeManager() {
		recipes = new ArrayList<CondenseTowerRecipe>();
		System.out.println("If you still fuking immediately crash then fucking go out");
	}

	@Override
	public boolean equal(CondenseTowerRecipe recipe1, CondenseTowerRecipe recipe2) {
		return (recipe1.getInput().equals(recipe2.getInput()));
	}

	@Override
	public boolean add(CondenseTowerRecipe recipe) {
		return recipes.add(recipe);
	}

	@Override
	public boolean remove(CondenseTowerRecipe recipe) {
		return recipes.remove(recipe);
	}
	
	@Override
	public Collection<CondenseTowerRecipe> getRecipes() {
		return recipes;
	}
	
	//This method will only read the first parameter.
	@SuppressWarnings("hiding")
	@Override
	public <FluidStack> CondenseTowerRecipe getRecipe(@SuppressWarnings("unchecked")FluidStack... input) {
		if (input[0] == null) 
			return null;
		for (CondenseTowerRecipe recipe : recipes) {
			if (recipe.getInput().isFluidStackIdentical((net.minecraftforge.fluids.FluidStack) input[0]))
				return recipe;
		}
		return null;
	}
	
	private static List<CondenseTowerRecipe> recipes;

	@Override
	public String toString() {
		return "CondenseTowerRecipeManager@"+recipes.toString();
	}
}

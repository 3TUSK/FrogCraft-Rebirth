package frogcraftrewrite.api.recipes;

import java.util.ArrayList;
import java.util.Collection;

public class AdvChemReactorRecipeManager implements IRecipeManager<AdvChemReactorRecipe> {

	@Override
	public boolean equal(AdvChemReactorRecipe recipe1, AdvChemReactorRecipe recipe2) {
		if (recipe1.getInput().size() != recipe2.getInput().size()) 
			return false;
		if (recipe1.getInput() != recipe2.getInput())
			return false;
		return true;
	}

	@Override
	public boolean add(AdvChemReactorRecipe recipe) {
		return recipes.add(recipe);
	}

	@Override
	public boolean remove(AdvChemReactorRecipe recipe) {
		return recipes.remove(recipe);
	}
	
	@Override
	public Collection<AdvChemReactorRecipe> getRecipes() {
		return recipes;
	}
	
	/*public boolean match(AdvChemReactorRecipe aRecipe) {
		for (AdvChemReactorRecipe recipe : recipes) {
			for (int a = 0; a < recipe.getInput().length; a++) {
				if (recipe.getInput()[a] instanceof ItemStack) {
					for (Object obj : aRecipe.getInput()) {
						if (obj instanceof ItemStack) {
							if ((ItemStack)obj == recipe.getInput()[a])
						}
					}
				}
				
				if (recipe.getInput()[a] instanceof FluidStack) {
					
				}
				
				if (recipe.getInput()[a] instanceof String) {
					
				}
			}
		}
		return true;
	}*/

	private static ArrayList<AdvChemReactorRecipe> recipes = new ArrayList<AdvChemReactorRecipe>();

}

package frogcraftrewrite.api.recipes;

import java.util.Collection;

/**Meaningless.*/
public interface IRecipeManager<RECIPE> {
	
	public boolean equal(RECIPE recipe1, RECIPE recipe2);
	
	public boolean add(RECIPE recipe);
	
	public boolean remove(RECIPE recipe);
	
	public Collection<RECIPE> getRecipes();
	
	public RECIPE getRecipe(Object... input);
}

package frogcraftrebirth.api.recipes;

import java.util.Collection;

/** Meaningless. */
public interface IRecipeManager<R> {

	void add(R recipe);

	void remove(R recipe);

	Collection<R> getRecipes();

	R getRecipe(IFrogRecipeInput... input);
}

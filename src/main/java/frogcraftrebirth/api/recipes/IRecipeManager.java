package frogcraftrebirth.api.recipes;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @since 1.0.0
 */
public interface IRecipeManager<R> {

	void add(R recipe);

	void remove(R recipe);

	Collection<R> getRecipes();

	/**
	 * @param input Array of wrapped inputs
	 *
	 * @implSpec
	 * Default implementation delegates to {@link #getRecipe(Iterable)}.
	 *
	 * @return The recipe if matched any; null if otherwise.
	 */
	default R getRecipe(IFrogRecipeInput... input) {
		return getRecipe(Arrays.asList(input));
	}

	R getRecipe(Iterable<IFrogRecipeInput> input);

}

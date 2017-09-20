package frogcraftrebirth.api.recipes;

import net.minecraft.item.ItemStack;

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
	 * @deprecated This cannot be properly implemented
	 * @param input Array of wrapped inputs
	 * @return The recipe if matched any; null if otherwise.
	 */
	@Deprecated
	default R getRecipe(IFrogRecipeInput... input) {
		return null;
	}

	/**
	 *
	 * @param input Input ItemStack array
	 * @return the recipe, or null if none matched
	 *
	 * @since 1.1.0
	 */
	R getRecipe(ItemStack... input);
}

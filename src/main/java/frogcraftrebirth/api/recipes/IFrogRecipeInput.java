package frogcraftrebirth.api.recipes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * A <code>IFrogRecipeInput</code> represents a wrapped input ingredient,
 * with its actual type being unknown by default.
 */
public interface IFrogRecipeInput {

	/**
	 * @param actualInput The actual input ingredient.
	 * @return true if the given actual input is matched; false for otherwise.
	 */
	boolean matches(Object actualInput);

	/**
	 * Retrieve a list of possible inputs.
	 * <p>
	 *     Note that, elements in returned list must satisfy that match(input) == true.
	 * </p>
	 * @param type The type reference of actual inputs
	 * @param <T> The type of inputs (e.g ItemStack, FluidStack, etc.)
	 * @return A list contains given type of inputs.
	 */
	@Nonnull
	<T> List<T> getActualInputs(Class<T> type);

	/**
	 * Retrieve the size of this input. The definition of size varies on the actual
	 * input type, i.e. return value of this method can only be used when type of the
	 * wrapped input is known. Example: if one implementation wraps an ItemStack,
	 * then getSize() can only be used on other ItemStack, not FluidStack or anything else.
	 *
	 * If consuming arbitrary ingredients is need, use {@link #accepts(Class, Object)}.
	 * @return
	 */
	int getSize();

	/**
	 * Accepts arbitrary ingredient input and consumes it according to the type provided.
	 * @param type The type reference of actual input
	 * @param actualInput The actual input ingredient object
	 * @param <I> The type of actual input
	 * @return The remainder, maybe <code>null</code>.
	 */
	@Nullable
	<I> I accepts(Class<I> type, I actualInput);
}

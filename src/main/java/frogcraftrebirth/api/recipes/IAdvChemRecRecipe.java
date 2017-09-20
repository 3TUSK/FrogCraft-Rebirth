/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:26:11 PM, Mar 12, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.recipes;

import java.util.Arrays;
import java.util.Collection;

import net.minecraft.item.ItemStack;

public interface IAdvChemRecRecipe {

	Collection<IFrogRecipeInput> getInputs();

	Collection<ItemStack> getOutputs();

	ItemStack getCatalyst();

	int getTime();

	int getEnergyRate();

	default int getRequiredCellAmount() {
		return 0;
	}

	default int getProducedCellAmount() {
		return 0;
	}

	default boolean equals(IAdvChemRecRecipe rec) {
		return rec.getInputs().equals(getInputs());
	}

	/**
	 * @deprecated This cannot be properly implemented and it is thus discouraged to use this.
	 *             Currently the default implementation always returns false.
	 *             Use {@link #matchInputs(ItemStack...)} instead.
	 * @param inputs Array of {@link IFrogRecipeInput} instances
	 * @return true if the given recipe inputs matches this recipe
	 */
	@Deprecated
	default boolean matchInputs(IFrogRecipeInput... inputs) {
		return false;
	}

	/**
	 * @param inputs The input ItemStack array
	 * @return true if given inputs matches this recipe
	 *
	 * @since 1.1.0
	 */
	default boolean matchInputs(ItemStack... inputs) {
		for (IFrogRecipeInput i : getInputs()) {
			boolean found = false;
			for (ItemStack iActual : inputs) {
				if (i.matches(iActual)) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}

		return true;
	}

}

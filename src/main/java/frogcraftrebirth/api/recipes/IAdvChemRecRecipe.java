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

	default boolean matchInputs(IFrogRecipeInput... inputs) {
		return getInputs().containsAll(Arrays.asList(inputs));
	}

	/**
	 * @param inputs The input ItemStack array
	 * @return true if given inputs matches this recipe
	 *
	 * @since 1.1.0
	 */
	default boolean matchInputs(ItemStack... inputs) {
		for (ItemStack input : inputs) {
			if (getInputs().stream().noneMatch(i -> i.matches(input))) {
				return false;
			}
		}
		return true;
	}

}

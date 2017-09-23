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
		return matchInputs(Arrays.asList(inputs));
	}

	default boolean matchInputs(Iterable<IFrogRecipeInput> inputs) {
		for (IFrogRecipeInput input : getInputs()) {
			boolean found = false;
			for (IFrogRecipeInput check : inputs) {
				if (input.matches(check)) {
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

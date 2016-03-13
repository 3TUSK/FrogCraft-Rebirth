/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:26:11 PM, Mar 12, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.recipes;

import java.util.Map;

public interface IAdvChemRecRecipe {
	
	Map<String, Integer> getInputs();
	
	Map<String, Integer> getOutputs();
	
	double getRateModifier(String catalyst);
	
	int getTime();
	
	int getEnergyRate();
	
	default boolean equals(IAdvChemRecRecipe rec) {
		if (rec.getInputs().equals(getInputs()))
			return true;
			else return false;
	}

}

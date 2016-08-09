/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:50:28 PM, Aug 9, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.craftingharmonics;

import org.winterblade.minecraft.harmony.api.BasicOperation;
import org.winterblade.minecraft.harmony.api.Operation;
import org.winterblade.minecraft.harmony.api.OperationException;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;

@Operation(name = "FrogCraftRebirth.addPyrolyzerRecipe", dependsOn = "frogcraftrebirth")
public class OperationPyrolysisRecipeAdder extends BasicOperation {
	
	transient IPyrolyzerRecipe recipe;
	
	@Override
	public void init() throws OperationException {
		
	}
	
	@Override
	public void apply() {
		if (recipe != null)
			FrogAPI.managerPyrolyzer.add(recipe);
	}
	
	@Override
	public void undo() {
		if (recipe != null)
			FrogAPI.managerPyrolyzer.remove(recipe);
	}

}

/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:14:56 PM, Apr 10, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import frogcraftrebirth.api.FrogAPI;

public class FrogNEIHandler implements IConfigureNEI {

	@Override
	public String getName() {
		return "FrogCraft: Rebirth NEI Compat"; //Localization is coming soon
	}

	@Override
	public String getVersion() {
		return FrogAPI.API_VER;
	}

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new RecipeHandlerPyrolyzer());
		API.registerUsageHandler(new RecipeHandlerPyrolyzer());
	}

}

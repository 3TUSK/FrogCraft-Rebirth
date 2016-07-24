/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 5:56:52 PM, Jul 23, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.ICompatModuleFrog;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class CompatJEI implements IModPlugin, ICompatModuleFrog {

	@Override
	public void init() {
		FrogAPI.FROG_LOG.info("Detected JustEnoughItems, compability will be enabled automatically.");
	}

	@Override
	public void register(IModRegistry registry) {
		
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		
	}

}

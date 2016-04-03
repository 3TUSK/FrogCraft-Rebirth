/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 3:34:14 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.lib.config.ConfigMain;

public final class FrogConfig {
	
	public static void initConfig(FMLPreInitializationEvent event) {
		File configDir = new File(event.getModConfigurationDirectory(), FrogAPI.MODID);
		
		if (!configDir.exists())
			configDir.mkdirs();
		ConfigMain.initMainConfig(new File(configDir, "FrogMain.cfg"));
		
		File reactionDir = new File(configDir, "reactions");
		if (!reactionDir.exists())
			reactionDir.mkdirs(); // todo
	}

}

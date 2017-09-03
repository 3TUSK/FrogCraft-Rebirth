/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 7:38:36 PM, Jul 25, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib.config;

import java.util.Collections;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGui extends GuiConfig {

	public ConfigGui(GuiScreen parentScreen) {
		super(parentScreen, Collections.singletonList(ConfigElement.from(FrogConfig.class)), FrogAPI.MODID, false, false, "FrogCraft-Rebirth", "Config Interface");
	}

}

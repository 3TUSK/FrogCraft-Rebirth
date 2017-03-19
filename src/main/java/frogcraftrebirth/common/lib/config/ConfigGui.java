/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 7:38:36 PM, Jul 25, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib.config;

import java.util.ArrayList;
import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig {
	
	private static final List<IConfigElement> ELEMENTS_LIST;
	
	static {
		ELEMENTS_LIST = new ArrayList<>();
		ELEMENTS_LIST.add(new ConfigElement(ConfigMain.config.getCategory("General")));
		ELEMENTS_LIST.add(new ConfigElement(ConfigMain.config.getCategory("Machine")));
		ELEMENTS_LIST.add(new ConfigElement(ConfigMain.config.getCategory("Misc")));
		ELEMENTS_LIST.add(new ConfigElement(ConfigMain.config.getCategory("Compatibility")));
	}

	public ConfigGui(GuiScreen parentScreen) {
		super(parentScreen, ELEMENTS_LIST, FrogAPI.MODID, false, false, "FrogCraft-Rebirth", "Config Interface");
	}

}

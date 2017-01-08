/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 9:03:00 PM, Apr 11, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.asm;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
/**
 * Note: this CoreMod will not function in production (i.e. in actual game play)
 */
@IFMLLoadingPlugin.MCVersion("1.10.2")
@IFMLLoadingPlugin.TransformerExclusions("frogcraftrebirth.common.asm")
public class FrogASMPlugin implements IFMLLoadingPlugin {
	
	public static final Logger LOGGER = LogManager.getLogger("FrogASMPlugin");

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {
			"frogcraftrebirth.common.asm.FrogASMTransformer"
		};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}

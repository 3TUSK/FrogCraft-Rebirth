/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 9:03:00 PM, Apr 11, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.asm;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.10.2")
@IFMLLoadingPlugin.TransformerExclusions("frogcraftrebirth.common.asm")
public class FrogASMPlugin implements IFMLLoadingPlugin {
	
	public static boolean ic2ClassicDetected = false;

	@Override
	public String[] getASMTransformerClass() {
		System.out.println("Well, FrogASMPlugin detcted.");
		return new String[] {
			"frogcraftrebirth.common.asm.FrogASMTransformer"
			//Will be enabled as soon as there is necessity
		};
	}

	@Override
	public String getModContainerClass() {
		return "frogcraftrebirth.common.asm.FrogCoremodContainer";
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

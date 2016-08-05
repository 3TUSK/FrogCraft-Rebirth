/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 8:44:28 AM, Aug 5, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.techreborn;

import frogcraftrebirth.api.ICompatModuleFrog;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CompatTechReborn implements ICompatModuleFrog {

	public static void preInit() {
		GameRegistry.<Block>register(new BlockPneumaticCompressor());
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			
		}
	}
	
	@Override
	public void init() {
		
	}

}

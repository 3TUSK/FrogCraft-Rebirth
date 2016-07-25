/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TSUK at 2:37:47 PM, Jul 21, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogItems;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FrogTextures {
	
	public static void initFrogItemsTexture() {
		RegHelper.registerModel(FrogItems.coolantAmmonia60K, "AmmoniaCoolant60K");
		RegHelper.registerModel(FrogItems.coolantAmmonia180K, "AmmoniaCoolant180K");
		RegHelper.registerModel(FrogItems.coolantAmmonia360K, "AmmoniaCoolant360K");
		RegHelper.registerModel(FrogItems.decayBatteryPlutoium, "DecayBatteryPlutoium");
		RegHelper.registerModel(FrogItems.decayBatteryThorium, "DecayBatteryThorium");
		RegHelper.registerModel(FrogItems.decayBatteryUranium, "DecayBatteryUranium");
		RegHelper.registerModel(FrogItems.ionCannon);
		RegHelper.registerModel(FrogItems.ionCannonFrame);
		RegHelper.registerModel(FrogItems.jinkela, "GoldClod");
		
		RegHelper.registerModel(FrogItems.tiberium, 0, "TiberiumRed");
		RegHelper.registerModel(FrogItems.tiberium, 1, "TiberiumBlue");
		RegHelper.registerModel(FrogItems.tiberium, 2, "TiberiumGreen");
	}
	
	public static void initFrogBlocksTexture() {
		RegHelper.registerModel(FrogBlocks.frogOres, 0, "Carnallite");
		RegHelper.registerModel(FrogBlocks.frogOres, 1, "Dewalquite");
		RegHelper.registerModel(FrogBlocks.frogOres, 2, "Fluorapatite");
		
		//RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidNitricAcid, "nitricAcid");
	}

}

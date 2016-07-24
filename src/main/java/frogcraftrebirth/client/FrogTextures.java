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
		RegHelper.regTextureFor(FrogItems.coolantAmmonia60K, "ammoniaCoolant60K");
		RegHelper.regTextureFor(FrogItems.coolantAmmonia180K, "ammoniaCoolant180K");
		RegHelper.regTextureFor(FrogItems.coolantAmmonia360K, "ammoniaCoolant360K");
		RegHelper.regTextureFor(FrogItems.decayBatteryPlutoium, "decayBatteryPlutoium");
		RegHelper.regTextureFor(FrogItems.decayBatteryThorium, "decayBatteryThorium");
		RegHelper.regTextureFor(FrogItems.decayBatteryUranium, "decayBatteryUranium");
		RegHelper.regTextureFor(FrogItems.ionCannon);
		RegHelper.regTextureFor(FrogItems.ionCannonFrame);
		RegHelper.regTextureFor(FrogItems.jinkela, "goldClod");
		
		int index;
		for (index = 0; index < 16; ++index) {
			RegHelper.regTextureFor(FrogItems.itemDust, index);
		}
		for (index = 0; index < 5; ++index) {
			RegHelper.regTextureFor(FrogItems.itemReactionModule, index);
		}
		for (index = 0; index < 4; ++index) {
			RegHelper.regTextureFor(FrogItems.tiberium, index);
		}
	}
	
	public static void initFrogBlocksTexture() {
		RegHelper.regTextureFor(FrogBlocks.frogOres, 0, "carnallite");
		RegHelper.regTextureFor(FrogBlocks.frogOres, 1, "dewalquite");
		RegHelper.regTextureFor(FrogBlocks.frogOres, 2, "fluorapatite");
		
		//RegHelper.regFluidBlockTexture((BlockFluidBase)FrogBlocks.fluidNitricAcid, "nitricAcid");
	}

}

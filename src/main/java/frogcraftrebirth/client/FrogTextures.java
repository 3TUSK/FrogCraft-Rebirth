/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TSUK at 2:37:47 PM, Jul 21, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FrogTextures {
	
	@SideOnly(Side.CLIENT)
	public static void initFrogItemsTexture() {
		regItemTexture(FrogItems.coolantAmmonia60K);
		regItemTexture(FrogItems.coolantAmmonia180K);
		regItemTexture(FrogItems.coolantAmmonia360K);
		regItemTexture(FrogItems.decayBatteryPlutoium);
		regItemTexture(FrogItems.decayBatteryThorium);
		regItemTexture(FrogItems.decayBatteryUranium);
		regItemTexture(FrogItems.ionCannon);
		regItemTexture(FrogItems.ionCannonFrame);
		regItemTexture(FrogItems.jinkela);
		
		int index;
		for (index = 0; index < 16; ++index) {
			regItemTexture(FrogItems.itemDust, index);
		}
		for (index = 0; index < 5; ++index) {
			regItemTexture(FrogItems.itemReactionModule);
		}
		for (index = 0; index < 4; ++index) {
			regItemTexture(FrogItems.tiberium);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initFrogBlocksTexture() {
		
	}
	
	@SideOnly(Side.CLIENT)
	static void regItemTexture(Item item) {
		regItemTexture(item, 0);
	}
	
	@SideOnly(Side.CLIENT)
	static void regItemTexture(Item item, int metadata) {
		String uniqueID = FrogAPI.MODID + ":" + item.getUnlocalizedName();
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(uniqueID, "inventory"));
	}

}

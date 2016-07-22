/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TSUK at 2:37:47 PM, Jul 21, 2016, CST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import frogcraftrebirth.common.FrogItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FrogTextures {
	
	@SideOnly(Side.CLIENT)
	public static void initFrogItemsTexture() {
		regItemTexture(FrogItems.coolantAmmonia60K, "ammoniaCoolant60K");
		regItemTexture(FrogItems.coolantAmmonia180K, "ammoniaCoolant180K");
		regItemTexture(FrogItems.coolantAmmonia360K, "ammoniaCoolant360K");
		regItemTexture(FrogItems.decayBatteryPlutoium, "decayBatteryPlutoium");
		regItemTexture(FrogItems.decayBatteryThorium, "decayBatteryThorium");
		regItemTexture(FrogItems.decayBatteryUranium, "decayBatteryUranium");
		regItemTexture(FrogItems.ionCannon);
		regItemTexture(FrogItems.ionCannonFrame);
		regItemTexture(FrogItems.jinkela, "goldClod");
		
		int index;
		for (index = 0; index < 16; ++index) {
			regItemTexture(FrogItems.itemDust, index);
		}
		for (index = 0; index < 5; ++index) {
			regItemTexture(FrogItems.itemReactionModule, index);
		}
		for (index = 0; index < 4; ++index) {
			regItemTexture(FrogItems.tiberium, index);
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
		regItemTexture(item, metadata, Item.REGISTRY.getNameForObject(item).toString());
	}
	
	@SideOnly(Side.CLIENT)
	static void regItemTexture(Item item, String newResLoc) {
		regItemTexture(item, 0, "frogcraftrebirth:"+newResLoc);
	}
	
	@SideOnly(Side.CLIENT)
	static void regItemTexture(Item item, int metadata, String newResLoc) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(newResLoc, "inventory"));
	}

}

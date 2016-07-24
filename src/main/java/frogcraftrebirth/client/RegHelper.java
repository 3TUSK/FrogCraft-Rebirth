/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 3:44:59 PM, Jul 24, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class RegHelper {
	
	static void regTextureFor(Item item) {
		regTextureFor(item, 0);
	}
	
	static void regTextureFor(Block block) {
		regTextureFor(block, 0);
	}
	
	static void regTextureFor(Item item, int metadata) {
		regTextureFor(item, metadata, Item.REGISTRY.getNameForObject(item).toString());
	}
	
	static void regTextureFor(Block block, int metadata) {
		regTextureFor(block, metadata, Block.REGISTRY.getNameForObject(block).toString());
	}
	
	static void regTextureFor(Item item, String newResLoc) {
		regTextureFor(item, 0, "frogcraftrebirth:" + newResLoc);
	}
	
	static void regTextureFor(Block block, String newResLoc) {
		regTextureFor(block, 0, "frogcraftrebirth:" + newResLoc);
	}
	
	static void regTextureFor(Block block, int metadata, String newResLoc) {
		regTextureFor(Item.getItemFromBlock(block), metadata, "frogcraftrebirth:" + newResLoc);
	}
	
	/**
	 * @param newResLoc The custom model location, modid inclusive.
	 */
	static void regTextureFor(Item item, int metadata, String newResLoc) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(newResLoc, "inventory"));
	}
	
	static void regFluidBlockTexture(BlockFluidBase fluidBlock, final String name) {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluidBlock), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("frogcraftrebirth:" + name, "fluid");
			}	
		});
		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("frogcraftrebirth:" + name, "fluid");
			}		
		});
	}

}

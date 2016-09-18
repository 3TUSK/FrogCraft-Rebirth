/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 3:44:59 PM, Jul 24, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class RegHelper {
	
	static void registerModel(Item item) {
		registerModel(item, 0);
	}
	
	static void registerModel(Block block) {
		registerModel(block, 0);
	}
	
	static void registerModel(Item item, String newResLoc) {
		registerModel(item, 0, newResLoc);
	}
	
	static void registerModel(Block block, String newResLoc) {
		registerModel(block, 0, newResLoc);
	}
	
	static void registerModel(Item item, int metadata) {
		registerModel0(item, metadata, Item.REGISTRY.getNameForObject(item).toString());
	}
	
	static void registerModel(Block block, int metadata) {
		registerModel0(Item.getItemFromBlock(block), metadata, Block.REGISTRY.getNameForObject(block).toString());
	}
	
	static void registerModel(Item item, int metadata, String newResLoc) {
		registerModel0(item, metadata, "frogcraftrebirth:" + newResLoc);
	}
	
	static void registerModel(Block block, int metadata, String newResLoc) {
		registerModel0(Item.getItemFromBlock(block), metadata, "frogcraftrebirth:" + newResLoc);
	}
	
	private static void registerModel0(Item item, int metadata, String resourceLocation) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceLocation, "inventory"));
	}
	
	static void regFluidBlockTexture(BlockFluidBase fluidBlock, final String name) {
		ModelResourceLocation aResource = new ModelResourceLocation("frogcraftrebirth:fluid", name);
		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return aResource;
			}		
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluidBlock), stack -> aResource);
		ModelBakery.registerItemVariants(Item.getItemFromBlock(fluidBlock), aResource);
	}

}

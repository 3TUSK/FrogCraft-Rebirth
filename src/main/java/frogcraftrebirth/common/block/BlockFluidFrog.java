/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 11:33:22 PM, Jul 25, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.block;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockFluidFrog extends BlockFluidClassic {
	
	public BlockFluidFrog(Fluid fluid, String registryName) {
		this(fluid, registryName, Material.WATER);
	}

	public BlockFluidFrog(Fluid fluid, String registryName, Material material) {
		super(fluid, material);
		//this.setCreativeTab(FrogAPI.frogTab);
		this.setRegistryName(registryName);
		GameRegistry.<Block>register(this);
		fluid.setBlock(this);
		ItemFrogBlock.registerItemBlockFor(this, new ItemBlock(this).setCreativeTab(FrogAPI.frogTab));
	}

}

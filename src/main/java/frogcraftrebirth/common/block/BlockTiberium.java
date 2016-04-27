/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 4:08:53 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.block;

import java.util.ArrayList;

import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockTiberium extends BlockFrog {

	public BlockTiberium() {
		super(Material.glass, 2);
		this.setBlockName("tiberium");
		this.setHardness(10.0F);
		this.setResistance(41.0F);
		this.setSubNameArray("red", "blue", "green");
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		entity.setFire(60);
	}
	
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> dropList = new ArrayList<ItemStack>();

		//quantityDropped(metadata, fortune, world.rand);
		
		return dropList;
	}

	public void exlposion(World world, int x, int y, int z) {
		
	}

}

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
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTiberium extends BlockFrog {

	public BlockTiberium() {
		super(TIBERIUM, "tiberium");
		this.setUnlocalizedName("tiberium");
		this.setHardness(10.0F);
		this.setResistance(42.0F);
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.setFire(60);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> dropList = new ArrayList<ItemStack>();

		//quantityDropped(metadata, fortune, world.rand);	
		return dropList;
	}

	public void explode(World world, BlockPos pos, float strength, boolean smoke) {
		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), strength, smoke);
	}
	
	public static enum Color {
		RED, BLUE, GREEN;
	}

}

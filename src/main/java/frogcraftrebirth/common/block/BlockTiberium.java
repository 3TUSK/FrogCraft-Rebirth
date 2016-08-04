/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 4:08:53 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.block;

import java.util.ArrayList;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.lib.block.BlockFrog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTiberium extends BlockFrog {
	
	public static final PropertyEnum<BlockTiberium.Color> TYPE = PropertyEnum.<BlockTiberium.Color>create("color", BlockTiberium.Color.class);

	public static IBlockState getTiberiumWithType(int typeIndex) {
		if (typeIndex > 2)
			typeIndex = 2; //Use Green Tiberium as fallback
		return ((BlockTiberium)FrogBlocks.tiberium).getDefaultState().withProperty(TYPE, BlockTiberium.Color.values()[typeIndex]);
	}
	
	public BlockTiberium() {
		super(TIBERIUM, "tiberium_crystal", 0, 1, 2);
		this.setUnlocalizedName("tiberium_crystal");
		this.setHardness(10.0F);
		this.setResistance(42.0F);
	}
	
	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { TYPE };
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.setFire(60);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> dropList = new ArrayList<ItemStack>();
		int quantityDropped = 1 + RANDOM.nextInt(fortune * 2);
		dropList.add(new ItemStack(FrogItems.tiberium, quantityDropped, this.damageDropped(state)));
		return dropList;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, Color.values()[meta]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public boolean isBlockNormalCube(IBlockState blockState) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}

	public void explode(World world, BlockPos pos, float strength, boolean smoke) {
		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), strength, smoke);
	}
	
	public static enum Color implements IStringSerializable {
		RED, BLUE, GREEN;

		@Override
		public String getName() {
			return this.name().toLowerCase();
		}
	}

}

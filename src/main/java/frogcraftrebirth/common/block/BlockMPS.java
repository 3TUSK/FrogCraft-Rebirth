/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.block;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.api.mps.IMobilePowerStation;
import frogcraftrebirth.common.item.ItemMPS;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMPS extends BlockMechanism {

	public BlockMPS() {
		super(TileMobilePowerStation.class);
		setHardness(1.0F);
		setResistance(1.0F);	
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileMobilePowerStation();
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (worldIn.getTileEntity(pos) instanceof TileMobilePowerStation) {		
			if (stack.getTagCompound() != null) {
				TileEntity tile = worldIn.getTileEntity(pos);
				if (tile instanceof IMobilePowerStation) {
					((IMobilePowerStation)tile).loadDataFrom(stack.getTagCompound());
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;
		playerIn.openGui(FrogCraftRebirth.getInstance(), 4, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity tile, ItemStack stack) {
		if (tile instanceof TileMobilePowerStation) {
			ItemStack mps = new ItemStack(this, 1);
			if (!mps.hasTagCompound())
				mps.setTagCompound(new NBTTagCompound());
			((TileMobilePowerStation)tile).saveDataTo(mps.getTagCompound());
			spawnAsEntity(worldIn, pos, mps);
		} else {
			super.harvestBlock(worldIn, player, pos, state, tile, stack);
		}
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		ItemStack stack = new ItemStack(this, 1, 0);
		NBTTagCompound tagMPS;
		if (stack.getTagCompound() != null) {
			tagMPS = stack.getTagCompound();
		} else {
			tagMPS = new NBTTagCompound();
			stack.setTagCompound(tagMPS);
		}
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileMobilePowerStation) {
			((TileMobilePowerStation)tile).saveDataTo(tagMPS);
			return stack;
		} else {
			tagMPS.setInteger("charge", 0);
			tagMPS.setInteger("maxCharge", 60000);
			tagMPS.setInteger("tier", 1);
			return stack;
		}
	}
	
	@Override
	public EnumFacing getFacing(World world, BlockPos pos) {
		return EnumFacing.UP;
	}
	
	@Override
	public boolean setFacing(World world, BlockPos pos, EnumFacing newDirection, EntityPlayer player) {
		return false;
	}
	
	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te, EntityPlayer player, int fortune) {
		ItemStack stack = new ItemStack(this, 1, 0);
		stack.setTagCompound(new NBTTagCompound());
		if (te instanceof TileMobilePowerStation)
			((TileMobilePowerStation)te).saveDataTo(stack.getTagCompound());
		else
			ItemMPS.normalize(stack);
		return Collections.singletonList(stack);
	}

}

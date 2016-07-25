package frogcraftrebirth.common.block;

import javax.annotation.Nullable;

import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMPS extends BlockFrogWrenchable implements ITileEntityProvider {
	
	public static final PropertyInteger LEVEL = PropertyInteger.create("charge_level", 0, 5);
	
	public BlockMPS() {
		super(MACHINE, "mobile_power_station", false, 0, 5);
		setUnlocalizedName("mobilePowerStation");
		setHardness(1.0F);
		setResistance(1.0F);	
	}
	
	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { LEVEL };
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileMobilePowerStation();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (worldIn.isRemote)
			return;
		if (worldIn.getTileEntity(pos) instanceof TileMobilePowerStation){		
			if (stack.getTagCompound() != null) {
				TileMobilePowerStation tile = (TileMobilePowerStation) worldIn.getTileEntity(pos);
				tile.loadDataFrom(stack.getTagCompound());
			}
		}
	}
	
	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity tile, @Nullable ItemStack stack) {
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
		return state.getValue(LEVEL);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(LEVEL, meta);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		ItemStack stack = new ItemStack(this, 1, 0);
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (world.getTileEntity(pos) instanceof TileMobilePowerStation) {
			((TileMobilePowerStation)world.getTileEntity(pos)).saveDataTo(stack.getTagCompound());
			return stack;
		} else {
			stack.getTagCompound().setInteger("charge", 0);
			stack.getTagCompound().setInteger("maxCharge", 60000);
			stack.getTagCompound().setInteger("tier", 1);
			return stack;
		}
	}

}

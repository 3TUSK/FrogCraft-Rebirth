package frogcraftrebirth.common.block;

import java.util.List;

import javax.annotation.Nullable;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.api.event.AccessControlEvent;
import frogcraftrebirth.common.item.ItemMPS;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockMPS extends BlockFrogWrenchable implements ITileEntityProvider {
	
	public static final PropertyInteger LEVEL = PropertyInteger.create("charge_level", 0, 5);
	
	public BlockMPS() {
		super(MACHINE, "mobile_power_station", false);
		setUnlocalizedName("mobilePowerStation");
		setDefaultState(this.getBlockState().getBaseState().withProperty(LEVEL, 0));
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
        return true;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileMobilePowerStation();
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		try {
			TileEntity tile = worldIn.getTileEntity(pos);
			float ratio = 0F;
			if (tile instanceof TileMobilePowerStation) {
				ratio = 5 * ((TileMobilePowerStation)tile).getCurrentEnergy() / ((TileMobilePowerStation)tile).getCurrentEnergyCapacity();
			}
			return state.withProperty(LEVEL, ratio >= 5F ? 5 : (int)ratio);
		} catch (Exception e) {
			return state.withProperty(LEVEL, 0);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (worldIn.getTileEntity(pos) instanceof TileMobilePowerStation) {		
			if (stack.getTagCompound() != null) {
				TileMobilePowerStation tile = (TileMobilePowerStation) worldIn.getTileEntity(pos);
				tile.loadDataFrom(stack.getTagCompound());
				if (tile.getOwnerUUID() == null && placer instanceof EntityPlayer)
					MinecraftForge.EVENT_BUS.post(new AccessControlEvent.Activate(tile, (EntityPlayer)placer));
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(FrogCraftRebirth.instance, 4, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
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
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
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
		return java.util.Arrays.<ItemStack>asList(stack);
	}

}

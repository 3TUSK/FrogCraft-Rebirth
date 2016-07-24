package frogcraftrebirth.common.block;

import javax.annotation.Nullable;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import frogcraftrebirth.common.tile.TileAirPump;
import frogcraftrebirth.common.tile.TileLiquifier;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachine extends BlockFrogWrenchable implements ITileEntityProvider {
	
	public static final PropertyEnum<Type> TYPE = PropertyEnum.<Type>create("machine", Type.class);

	public BlockMachine() {
		super(MACHINE, "machine", false, 0, 1, 2, 3);
		setUnlocalizedName("machines");
		setHardness(5.0F);
		setResistance(10.0F);
	}
	
	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { TYPE, FACING_HORIZONTAL };
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileFrog tile = (TileFrog)worldIn.getTileEntity(pos);
		if (tile != null) {
			if (tile instanceof IInventory) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tile);
			}
			//world.func_147453_f(x, y, z, block);
		}
		
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta & 0b11) {
			case 0:
				return new TileAdvChemReactor();
			case 1:
				return new TileAirPump();
			case 2:
				return new TilePyrolyzer();
			case 3:
				return new TileLiquifier();
			default:
				return null;
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) return false;
			playerIn.openGui(FrogCraftRebirth.instance, 5, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return false;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int facing = state.getValue(FACING_HORIZONTAL).getIndex();
		return facing << 2 + state.getValue(TYPE).ordinal() - 2;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int facing = meta >> 2, type = meta & 0b11;
		return this.getDefaultState().withProperty(FACING_HORIZONTAL, EnumFacing.getFront(facing + 2)).withProperty(TYPE, Type.values()[type]);
	}
	
	public static enum Type implements IStringSerializable {
		ADVCHEMREACTOR, AIRPUMP, PYROLYZER, LIQUIFIER;

		@Override
		public String getName() {
			return this.name().toLowerCase();
		}
	}
}

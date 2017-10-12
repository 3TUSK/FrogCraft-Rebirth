package frogcraftrebirth.common.block;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerPart;
import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.tile.IHasWork;
import frogcraftrebirth.common.tile.TileCondenseTower;
import frogcraftrebirth.common.tile.TileCondenseTowerStructure;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCondenseTower extends BlockFrogWrenchable implements ITileEntityProvider {

	public static final PropertyEnum<Part> TYPE = PropertyEnum.create("variant", Part.class);

	public BlockCondenseTower() {
		super(MACHINE, "condense_tower", false, 0, 1, 2);
		setUnlocalizedName("condenseTower");
		setDefaultState(getDefaultState().withProperty(WORKING, false));
		setHardness(15.0F);
		setResistance(20.0f);
	}

	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { TYPE, FACING_HORIZONTAL, WORKING };
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta & 0b11) {
			case 0:
				return new TileCondenseTower();
			case 1:
				return new TileCondenseTowerStructure();
			case 2:
				return new TileFluidOutputHatch();
			default:
				return null;
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock().getMetaFromState(state) & 0b11;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof IHasWork) {
			return state.withProperty(WORKING, ((IHasWork)tile).isWorking());
		} else {
			return state.withProperty(WORKING, false);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof ICondenseTowerCore)
			return;
		TileEntity tileBelow = world.getTileEntity(pos.down());
		if (tile != null && tileBelow != null && tile instanceof ICondenseTowerPart && tileBelow instanceof ICondenseTowerPart) {
			ICondenseTowerCore core = ((ICondenseTowerPart)tileBelow).getMainBlock();
			if (core != null) {
				core.onPartAttached((ICondenseTowerPart) tile);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.getBlockState(pos).getValue(TYPE) == Part.CYLINDER)
			return false; // process as normal block

		if (worldIn.isRemote)
			return true;
		playerIn.openGui(FrogCraftRebirth.getInstance(), 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true; // i.e. server and not cylinder
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof ICondenseTowerCore)
			((ICondenseTowerCore)tile).onDestruction();
		else if (tile instanceof ICondenseTowerPart) {
			((ICondenseTowerPart)tile).getMainBlock().onPartRemoved((ICondenseTowerPart)tile);
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int facing;
		switch (state.getValue(FACING_HORIZONTAL)) {
			case SOUTH: {
				facing = 0;
				break;
			}
			case WEST: {
				facing = 1;
				break;
			}
			case NORTH: {
				facing = 2;
				break;
			}
			case EAST: {
				facing = 3;
				break;
			}
			default: {
				facing = 2;
				break;
			}
		}
		return (facing << 2) + state.getValue(TYPE).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		int facing = meta >> 2, type = meta & 0b11;
		return this.getDefaultState().withProperty(FACING_HORIZONTAL,  EnumFacing.getHorizontal(facing)).withProperty(TYPE, Part.values()[type]);
	}

	public enum Part implements IStringSerializable {
		CORE, CYLINDER, OUTPUT;

		@Override
		public String getName() {
			return this.name().toLowerCase(java.util.Locale.ENGLISH);
		}
	}

}

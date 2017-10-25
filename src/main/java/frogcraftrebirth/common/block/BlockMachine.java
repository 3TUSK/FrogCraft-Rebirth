package frogcraftrebirth.common.block;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.lib.util.ItemUtil;
import frogcraftrebirth.common.tile.IHasWork;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import frogcraftrebirth.common.tile.TileAirPump;
import frogcraftrebirth.common.tile.TileLiquefier;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachine extends BlockFrogWrenchable {
	
	public static final PropertyEnum<Type> TYPE = PropertyEnum.create("variant", Type.class);

	public BlockMachine() {
		super(MACHINE, "machine", false, 0, 1, 2, 3);
		setUnlocalizedName("machines");
		setDefaultState(getDefaultState().withProperty(WORKING, false));
		setHardness(5.0F);
		setResistance(10.0F);
	}
	
	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { TYPE, FACING_HORIZONTAL, WORKING };
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		int meta = state.getBlock().getMetaFromState(state);
		switch (meta & 0b11) { //AirPump has no item storage capability, so meta & 3 = 1 is omitted here
			case 0: {
				ItemUtil.dropInventroyItems(worldIn, pos, ((TileAdvChemReactor)worldIn.getTileEntity(pos)).module);
				ItemUtil.dropInventroyItems(worldIn, pos, ((TileAdvChemReactor)worldIn.getTileEntity(pos)).input);
				ItemUtil.dropInventroyItems(worldIn, pos, ((TileAdvChemReactor)worldIn.getTileEntity(pos)).output);
				ItemUtil.dropInventroyItems(worldIn, pos, ((TileAdvChemReactor)worldIn.getTileEntity(pos)).cellInput);
				ItemUtil.dropInventroyItems(worldIn, pos, ((TileAdvChemReactor)worldIn.getTileEntity(pos)).cellOutput);
				break;
			}
			case 2: {
				ItemUtil.dropInventroyItems(worldIn, pos, ((TilePyrolyzer)worldIn.getTileEntity(pos)).input, ((TilePyrolyzer)worldIn.getTileEntity(pos)).output, ((TilePyrolyzer)worldIn.getTileEntity(pos)).fluidIO);
				break;
			}
			case 3: {
				ItemUtil.dropInventroyItems(worldIn, pos, ((TileLiquefier)worldIn.getTileEntity(pos)).inv);
				break;
			}
			default: 
				break;
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		switch (state.getValue(TYPE)) {
			case ADVCHEMREACTOR:
				return new TileAdvChemReactor();
			case AIRPUMP:
				return new TileAirPump();
			case PYROLYZER:
				return new TilePyrolyzer();
			case LIQUEFIER:
				return new TileLiquefier();
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;
		playerIn.openGui(FrogCraftRebirth.getInstance(), 5, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
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
		return this.getDefaultState().withProperty(FACING_HORIZONTAL, EnumFacing.getHorizontal(facing)).withProperty(TYPE, Type.values()[type]);
	}
	
	public enum Type implements IStringSerializableEnumImpl {
		ADVCHEMREACTOR, AIRPUMP, PYROLYZER, LIQUEFIER
    }
}

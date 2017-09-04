package frogcraftrebirth.common.block;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.tile.TileHSU;
import frogcraftrebirth.common.tile.TileHSUUltra;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHSU extends BlockFrogWrenchable implements ITileEntityProvider {

	public static final PropertyEnum<Level> LEVEL = PropertyEnum.create("variant", Level.class);

	public BlockHSU() {
		super(MACHINE, "hybrid_storage_unit", true, 0, 1);
		setUnlocalizedName("hybridStorageUnit");
	}

	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { LEVEL, FACING_ALL };
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else if (worldIn.getTileEntity(pos) instanceof TileHSU) {
			playerIn.openGui(FrogCraftRebirth.getInstance(), 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta % 2) {
			case 0:
				return new TileHSU();
			case 1:
				return new TileHSUUltra();
			default:
				return null;
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(LEVEL).ordinal();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING_ALL).getIndex() * 2 + state.getValue(LEVEL).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(LEVEL, Level.values()[meta % 2]).withProperty(FACING_ALL, EnumFacing.VALUES[meta % 6]);
	}

	public enum Level implements IStringSerializableEnumImpl {
		NORMAL, ULTRA
    }

}

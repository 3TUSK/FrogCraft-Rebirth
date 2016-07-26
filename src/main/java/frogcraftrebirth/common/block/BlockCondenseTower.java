package frogcraftrebirth.common.block;

import javax.annotation.Nullable;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.tile.TileCondenseTower;
import frogcraftrebirth.common.tile.TileCondenseTowerStructure;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCondenseTower extends BlockFrogWrenchable implements ITileEntityProvider {
	
	public static final PropertyEnum<Part> TYPE = PropertyEnum.<Part>create("part", Part.class);

	public BlockCondenseTower() {
		super(MACHINE, "condense_tower", false, 0, 1, 2);
		setUnlocalizedName("condenseTower");
		setHardness(15.0F);
		setResistance(20.0f);
	}
	
	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] { TYPE, FACING_HORIZONTAL };
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
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
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return false;
		else {
			if (worldIn.getTileEntity(pos) instanceof TileFrog) {
				playerIn.openGui(FrogCraftRebirth.instance, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int facing = state.getValue(FACING_HORIZONTAL).getIndex();
		return facing << 2 + state.getValue(TYPE).ordinal();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int facing = meta >> 2, type = meta & 0b11;
		return this.getDefaultState().withProperty(FACING_HORIZONTAL,  EnumFacing.getHorizontal(facing)).withProperty(TYPE, Part.values()[type]);
	}
	
	public static enum Part implements IStringSerializable {
		CORE, CYLINDER, OUTPUT;

		@Override
		public String getName() {
			return this.name().toLowerCase();
		}
	}

}

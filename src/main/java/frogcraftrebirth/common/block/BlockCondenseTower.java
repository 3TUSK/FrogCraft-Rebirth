package frogcraftrebirth.common.block;

import javax.annotation.Nullable;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.tile.TileCondenseTower;
import frogcraftrebirth.common.tile.TileCondenseTowerStructure;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCondenseTower extends BlockFrogContainer {

	public BlockCondenseTower() {
		super(MACHINE);
		setUnlocalizedName("multiBlockMachine.CondenseTower");
		setHardness(15.0F);
		setResistance(20.0f);
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
		}
		
		return null;
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
	
	public static enum Part {
		CORE, CYLINDER, OUTPUT;
	}

}

package frogcraftrebirth.common.block;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGenerator extends BlockFrogContainer {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockGenerator() {
		super(MACHINE, "generator");
		setUnlocalizedName("generator");
		setHardness(5.0F);
		setResistance(10.0F);
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
		if (meta == 0)
			return new TileCombustionFurnace();
		else
			return null;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return false;
		player.openGui(FrogCraftRebirth.instance, 3, world, x, y, z);
		return false;
	}
	
	public static enum Type implements IStringSerializable {
		COMBUSTION/*, ???*/;

		@Override
		public String getName() {
			return this.name();
		}
	}

}

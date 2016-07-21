package frogcraftrebirth.common.block;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import frogcraftrebirth.common.tile.TileAirPump;
import frogcraftrebirth.common.tile.TileLiquifier;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachine extends BlockFrogContainer {

	public BlockMachine() {
		super(Material.IRON, 3);
		setUnlocalizedName("machines");
		setHardness(5.0F);
		setResistance(10.0F);
		setSubNameArray("AdvChemReactor", "AirPump", "Pyrolyzer", "Liquifier");
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
		switch (meta) {
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
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float hitX, float hitY, float hitZ) {
		if (world.isRemote) return false;
		player.openGui(FrogCraftRebirth.instance, 5, world, x, y, z);
		return false;
	}
}

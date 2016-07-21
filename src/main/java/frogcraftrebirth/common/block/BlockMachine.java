package frogcraftrebirth.common.block;

import java.util.Random;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import frogcraftrebirth.common.tile.TileAirPump;
import frogcraftrebirth.common.tile.TileLiquifier;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachine extends BlockFrogContainer {

	public BlockMachine() {
		super(Material.IRON, 3);
		setBlockName("machines");
		setHardness(5.0F);
		setResistance(10.0F);
		setSubNameArray("AdvChemReactor", "AirPump", "Pyrolyzer", "Liquifier");
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int what) {
		TileFrog tile = (TileFrog)world.getTileEntity(x, y, z);
		if (tile != null) {
			if (tile instanceof IInventory) {
				for (int n = 0;n < ((IInventory)tile).getSizeInventory(); n++) {
					ItemStack stack = ((IInventory)tile).getStackInSlot(n);		
					if (stack != null) {
						EntityItem entityitem = new EntityItem(world, x, y, z, stack.copy());
						if (stack.hasTagCompound())
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)stack.stackTagCompound.copy());
						float f3 = 0.05F;
                        entityitem.motionX = (double)((float)new Random().nextGaussian() * f3);
                        entityitem.motionY = (double)((float)new Random().nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)new Random().nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
					}
				}
			}
			world.func_147453_f(x, y, z, block);
		}
		
		super.breakBlock(world, x, y, z, block, what);
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

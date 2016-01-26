package frogcraftrebirth.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGenerator extends BlockFrogContainer {

	public BlockGenerator() {
		super(Material.iron, 0);
		setBlockName("generator");
		setHardness(5.0F);
		setResistance(10.0F);
		setSubNameArray("CombustionFurnace");
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		iconArray[0][0]=r.registerIcon(TEXTURE_MAIN + "CombustionFurnace_Back");
		iconArray[0][1]=r.registerIcon(TEXTURE_MAIN + "CombustionFurnace_Back");
		iconArray[0][2]=r.registerIcon(TEXTURE_MAIN + "CombustionFurnace_Front");
		iconArray[0][3]=r.registerIcon(TEXTURE_MAIN + "CombustionFurnace_Back");    	
		iconArray[0][4]=r.registerIcon(TEXTURE_MAIN + "CombustionFurnace_Side");
		iconArray[0][5]=r.registerIcon(TEXTURE_MAIN + "CombustionFurnace_Side"); 
	}

}

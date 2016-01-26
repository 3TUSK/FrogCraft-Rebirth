package frogcraftrebirth.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMPS extends BlockFrogContainer {
	
	public BlockMPS() {
		super(Material.piston);
		setHardness(1.0F);
		setResistance(100.0F);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		iconArray[0][0] = reg.registerIcon(TEXTURE_MAIN + "MobilePS_Top");
		iconArray[0][1] = reg.registerIcon(TEXTURE_MAIN + "MobilePS_Bottom");
		for (int a=2;a<6;a++) {
			iconArray[0][a] = reg.registerIcon(TEXTURE_MAIN + "MobilePS_Side");
		}
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileMobilePowerStation();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {
		if (world.isRemote) return;
		if (world.getTileEntity(x, y, z) instanceof TileMobilePowerStation){		
			if (itemstack.stackTagCompound != null) {
				TileMobilePowerStation tile = (TileMobilePowerStation) world.getTileEntity(x, y, z);
				/*tile.charge = itemstack.stackTagCompound.getDouble("storedEnergy");
				tile.maxCharge = itemstack.stackTagCompound.getDouble("maxEnergy");
				
				NBTTagList invList = itemstack.stackTagCompound.getTagList("inventory", 10);
				for (int n = 0; n < invList.tagCount(); n++) {
					NBTTagCompound aItem = invList.getCompoundTagAt(n);
					byte slot = aItem.getByte("Slot");
					if (slot >= 0 && slot < tile.inv.length) {
						tile.inv[slot] = ItemStack.loadItemStackFromNBT(aItem);
					}
				}*/
				tile.readFromNBT(itemstack.stackTagCompound);
			}
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int what) {
		ItemStack mps = new ItemStack(this, 1);
		
		if (world.getTileEntity(x, y, z) instanceof TileMobilePowerStation) {
			TileMobilePowerStation tile = (TileMobilePowerStation)world.getTileEntity(x, y, z);
			if (mps.stackTagCompound == null) mps.stackTagCompound = new NBTTagCompound();
			/*mps.stackTagCompound.setDouble("storedEnergy", tile.charge);
			mps.stackTagCompound.setDouble("maxEnergy", tile.maxCharge);
			NBTTagList invList = new NBTTagList();
			for (int n = 0; n < tile.inv.length; n++) {
				ItemStack stack = tile.inv[n];
				if (stack != null) {
					NBTTagCompound tagStack = new NBTTagCompound();
					tagStack.setByte("Slot", (byte) n);
					stack.writeToNBT(tagStack);
					invList.appendTag(tagStack);
				}
			}
			mps.stackTagCompound.setTag("inventory", invList);*/
			tile.writeToNBT(mps.stackTagCompound);
		}
		
		dropBlockAsItem(world, x, y, z, mps);
		super.breakBlock(world, x, y, z, block, what);
	}

}

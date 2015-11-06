package frogcraftrewrite.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import frogcraftrewrite.common.tile.TileMobilePowerStation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMPS extends BlockFrog {

	IIcon[] icons;
	
	public BlockMPS() {
		super(Material.rock);
		setCreativeTab(FrogCraftRebirth.TAB_FC);
		setHardness(1.0F);
		setResistance(1000.0F);//Our MPS won't exploded!
		setBlockTextureName("frogcraftrewrite:MobilePS");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return icons[side];
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(getTextureName()+"_Top");
		icons[1] = reg.registerIcon(getTextureName()+"_Bottom");
		for (int a=2;a<=6;a++) {
			icons[a] = reg.registerIcon(getTextureName()+"_Side");
		}
	}
	
	/**Our MPS won't be replaced by leaves!*/
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return false;
	}
	
	/**Our MPS won't be burnt!*/
	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileMobilePowerStation(false);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {
		if (world.getTileEntity(x, y, z) instanceof TileMobilePowerStation){		
			if (itemstack.stackTagCompound != null) {
				TileMobilePowerStation tile = (TileMobilePowerStation) world.getTileEntity(x, y, z);
				
				tile.storedEnergy = itemstack.stackTagCompound.getDouble("storedEnergy");
				tile.maxEnergy = itemstack.stackTagCompound.getDouble("maxEnergy");
				tile.isPrivate = itemstack.stackTagCompound.getBoolean("isPrivate");
				
				NBTTagList invList = itemstack.stackTagCompound.getTagList("inventory", 10);
				for (int n = 0; n < invList.tagCount(); n++) {
					NBTTagCompound aItem = invList.getCompoundTagAt(n);
					byte slot = aItem.getByte("Slot");
					if (slot >= 0 && slot < tile.inv.length) {
						tile.inv[slot] = ItemStack.loadItemStackFromNBT(aItem);
					}
				}
			}
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int what) {
		ItemStack mps = new ItemStack(this, 1);
		
		if (world.getTileEntity(x, y, z) instanceof TileMobilePowerStation) {
			TileMobilePowerStation tile = (TileMobilePowerStation) world.getTileEntity(x, y, z);
			if (mps.stackTagCompound == null) mps.stackTagCompound = new NBTTagCompound();
			mps.stackTagCompound.setString("UUID", tile.profile.getId().toString());
			mps.stackTagCompound.setString("name", tile.profile.getName());
			mps.stackTagCompound.setDouble("storedEnergy", tile.storedEnergy);
			mps.stackTagCompound.setDouble("maxEnergy", tile.maxEnergy);
			mps.stackTagCompound.setBoolean("isPrivate", tile.isPrivate);
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
			mps.stackTagCompound.setTag("inventory", invList);
		}
		
		dropBlockAsItem(world, x, y, z, mps);
		super.breakBlock(world, x, y, z, block, what);
	}

}

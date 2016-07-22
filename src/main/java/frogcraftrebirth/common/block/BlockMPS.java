package frogcraftrebirth.common.block;

import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMPS extends BlockFrogContainer {
	
	public BlockMPS() {
		super(MACHINE);
		setHardness(1.0F);
		setResistance(100.0F);
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileMobilePowerStation();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (worldIn.isRemote) return;
		if (worldIn.getTileEntity(pos) instanceof TileMobilePowerStation){		
			if (stack.getTagCompound() != null) {
				TileMobilePowerStation tile = (TileMobilePowerStation) worldIn.getTileEntity(pos);
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
				tile.readFromNBT(stack.getTagCompound());
			}
		}
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		ItemStack mps = new ItemStack(this, 1);
		
		if (worldIn.getTileEntity(pos) instanceof TileMobilePowerStation) {
			TileMobilePowerStation tile = (TileMobilePowerStation)worldIn.getTileEntity(pos);
			if (mps.getTagCompound() == null) 
				mps.setTagCompound(new NBTTagCompound());
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
			tile.writeToNBT(mps.getTagCompound());
		}
		
		spawnAsEntity(worldIn, pos, mps);
		super.breakBlock(worldIn, pos, state);
	}

}

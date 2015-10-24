package frogcraftrewrite.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import ic2.api.tile.IWrenchable;

public abstract class TileFrog extends TileEntity implements IWrenchable{

	protected short facing = 2;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
	}
	
	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
		return true;
	}

	@Override
	public short getFacing() {
		return facing;
	}

	@Override
	public abstract void setFacing(short facing);

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public float getWrenchDropRate() {
		return 1F;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(this.getBlockType(), 1, this.blockMetadata);
	}

}

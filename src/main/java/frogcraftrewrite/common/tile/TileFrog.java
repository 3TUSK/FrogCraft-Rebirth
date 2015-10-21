package frogcraftrewrite.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import ic2.api.tile.IWrenchable;

public abstract class TileFrog extends TileEntity implements IWrenchable{

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
	public abstract short getFacing();

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
	public abstract ItemStack getWrenchDrop(EntityPlayer entityPlayer);

}

package frogcraftrewrite.common.lib.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrewrite.api.IFrogNetworkObject;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class TileFrog extends TileEntity implements IWrenchable, IFrogNetworkObject {

	protected short facing = 5, prevFacing;
	
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
	public void setFacing(short facing) {
		this.facing = facing;
		//update.
		this.prevFacing = facing;
	}

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

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		output.writeShort(this.facing);
	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		this.facing = input.readShort();
	}

}

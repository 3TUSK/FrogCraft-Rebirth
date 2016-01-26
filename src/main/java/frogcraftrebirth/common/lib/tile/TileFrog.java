package frogcraftrebirth.common.lib.tile;

import static frogcraftrebirth.common.network.NetworkHandler.FROG_NETWORK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.api.IFrogNetworkObject;
import frogcraftrebirth.common.network.PacketFrog00TileUpdate;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public abstract class TileFrog extends TileEntity implements IWrenchable, IFrogNetworkObject {

	protected short facing, prevFacing = facing;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
		facing = 5;
		
		prevFacing = facing;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.facing = tag.getShort("facing");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setShort("facing", facing);
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
		if (this instanceof IEnergyTile)
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent((IEnergyTile)this));
		this.facing = facing;
		this.sendTileUpdatePacket(this);
		this.prevFacing = facing;
		if (this instanceof IEnergyTile)
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent((IEnergyTile)this));
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

	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		this.facing = input.readShort();
	}
	
	protected void sendTileUpdatePacket(TileFrog tile) {
		FROG_NETWORK.sendToAllAround(new PacketFrog00TileUpdate(tile), this.worldObj.provider.terrainType.getWorldTypeID(), xCoord, yCoord, zCoord, 5);
	}

}

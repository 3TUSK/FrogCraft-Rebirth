package frogcraftrewrite.common.tile;

import static frogcraftrewrite.common.network.NetworkHandler.FROG_NETWORK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrewrite.common.lib.tile.TileFrogGenerator;
import frogcraftrewrite.common.network.PacketFrog00TileUpdate;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileAcademyWindmillBase extends TileFrogGenerator {
	
	boolean canGenEnergy;

	public TileAcademyWindmillBase() {
		super(0, "TileAcademyCityWindmillBase", 1, 32);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) return;
		
		TileEntity turbine = worldObj.getTileEntity(xCoord, yCoord+7, zCoord);
		if (turbine instanceof TileAcademyWindmillTurbine) {
			this.canGenEnergy = ((TileAcademyWindmillTurbine)turbine).canGenEnergy;
		}
		
		markDirty();
		FROG_NETWORK.sendToAll(new PacketFrog00TileUpdate(this));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.canGenEnergy = tag.getBoolean("canGenEnergy");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setBoolean("canGenEnergy", canGenEnergy);
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		this.canGenEnergy = input.readByte() == 0 ? false : true;
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		output.writeByte(canGenEnergy ? 0x01 : 0x00);
	}
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return direction == ForgeDirection.DOWN;
	}

	@Override
	public double getOfferedEnergy() {
		return canGenEnergy ? super.getOfferedEnergy() : 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return false;
	}

}

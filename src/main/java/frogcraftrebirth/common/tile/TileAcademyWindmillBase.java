package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrebirth.common.lib.tile.TileFrogGenerator;
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
			this.canGenEnergy = ((TileAcademyWindmillTurbine)turbine).isWorking();
		}
		
		markDirty();
		this.sendTileUpdatePacket(this);
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		this.canGenEnergy = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		output.writeBoolean(this.canGenEnergy);
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

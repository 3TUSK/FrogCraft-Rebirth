package frogcraftrebirth.common.lib.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;

public abstract class TileEnergySink extends TileFrog implements ITickable, IEnergySink {
	
	public int charge, maxCharge, sinkTier;
	protected boolean isInENet;
	
	protected TileEnergySink(int sinkTier, int maxEnergy) {
		this.sinkTier = sinkTier;
		this.maxCharge = maxEnergy;
	}
	
	@Override
	public void invalidate() {
		if (!worldObj.isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
		super.invalidate();
	}
	
	@Override
	public void update() {	
		if (!worldObj.isRemote && !isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.charge = tag.getInteger("charge");
		this.maxCharge = tag.getInteger("maxCharge");
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		charge = input.readInt();
		maxCharge = input.readInt();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		output.writeInt(charge);
		output.writeInt(maxCharge);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("charge", this.charge);
		tag.setInteger("maxCharge", maxCharge);
		return super.writeToNBT(tag);
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing direction) {
		return true;
	}

	@Override
	public double getDemandedEnergy() {
		return maxCharge - charge;
	}

	@Override
	public int getSinkTier() {
		return sinkTier;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		this.charge += amount;
		if (charge >= maxCharge)
			charge = maxCharge;
		return 0;
	}

}

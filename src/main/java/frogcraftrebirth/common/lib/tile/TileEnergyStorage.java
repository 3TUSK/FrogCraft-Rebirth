package frogcraftrebirth.common.lib.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tile.IEnergyStorage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public abstract class TileEnergyStorage extends TileFrog implements ITickable, IEnergySink, IEnergySource, IEnergyStorage {

	public EnumFacing emitDir;
	public int storedE, maxE, output, tier;
	protected boolean loaded = false;
	protected final boolean usableForTp;
	
	public TileEnergyStorage(int maxEnergy, int output, int tier, boolean allowTelep) {
		this.storedE = 0;
		this.maxE = maxEnergy;
		this.output = output;
		this.tier = tier;
		this.usableForTp = allowTelep;
	}
	
	@Override
	public void invalidate() {
		if (!worldObj.isRemote && loaded) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.loaded = false;
		}
		super.invalidate();
	}
	
	@Override
	public void update() {
		if (!worldObj.isRemote && !loaded) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.loaded = true;
		}
		this.emitDir = worldObj.getBlockState(getPos()).getValue(BlockFrogWrenchable.FACING_ALL);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.storedE = tag.getInteger("charge");
		this.maxE = tag.getInteger("maxCharge");
		this.output = tag.getInteger("output");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("charge", storedE);
		tag.setInteger("maxCharge", maxE);
		tag.setInteger("output", output);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		output.writeInt(this.storedE);
		output.writeInt(this.maxE);
		output.writeInt(this.output); //not the OutputStream, but the EU output per tick.
		output.writeInt(this.tier);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		this.storedE = input.readInt();
		this.maxE = input.readInt();
		this.output = input.readInt();
		this.tier = input.readInt();
	}
	
	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing direction) {
		return direction != this.emitDir;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing direction) {
		return direction == this.emitDir;
	}

	@Override
	public int getStored() {
		return this.storedE;
	}

	@Override
	public void setStored(int energy) {
		this.storedE = energy;
	}

	@Override
	public int addEnergy(int amount) {
		this.storedE += amount;
		if (storedE > maxE) 
			storedE = maxE;
		return storedE;
	}

	@Override
	public int getCapacity() {
		return maxE;
	}

	@Override
	public int getOutput() {
		return this.output;
	}

	@Override
	public double getOutputEnergyUnitsPerTick() {
		return output;
	}

	@Override
	public boolean isTeleporterCompatible(EnumFacing direction) {
		return usableForTp;
	}

	@Override
	public double getOfferedEnergy() {
		return Math.min(storedE, output);
	}

	@Override
	public void drawEnergy(double amount) {
		this.storedE -= amount;
		if (this.storedE < 0) this.storedE = 0;
	}

	@Override
	public int getSourceTier() {
		return this.tier;
	}

	@Override
	public double getDemandedEnergy() {
		return this.maxE - this.storedE;
	}

	@Override
	public int getSinkTier() {
		return this.tier;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		this.storedE += amount;
		if (storedE >= maxE) 
			storedE = maxE;
		return 0;
	}

}

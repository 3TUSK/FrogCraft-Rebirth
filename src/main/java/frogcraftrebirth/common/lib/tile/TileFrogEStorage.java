package frogcraftrebirth.common.lib.tile;

import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tile.IEnergyStorage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.util.EnumFacing;

public abstract class TileFrogEStorage extends TileFrog implements IEnergySink, IEnergySource, IEnergyStorage {

	public EnumFacing emitDir;
	public int storedE, maxE, output;
	protected boolean loaded = false;
	boolean usableForTp;
	
	public TileFrogEStorage(int maxEnergy, int output, EnumFacing emitTo, boolean allowTelep) {
		this.storedE = 0;
		this.maxE = maxEnergy;
		this.output = output;
		this.emitDir = emitTo;
		this.usableForTp = allowTelep;
	}
	
	public abstract int getTier();
	
	@Override
	public void invalidate() {
		if (!worldObj.isRemote && loaded) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.loaded = false;
		}
		super.invalidate();
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
		if (storedE > maxE) storedE = maxE;
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
		return this.getTier();
	}

	@Override
	public double getDemandedEnergy() {
		return this.maxE - this.storedE;
	}

	@Override
	public int getSinkTier() {
		return this.getTier();
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		this.storedE += amount;
		if (storedE >= maxE) storedE = maxE;
		return 0;
	}

}

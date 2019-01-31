/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.lib.tile;

import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tile.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;

public abstract class TileEnergyStorage extends TileEnergy implements IEnergySink, IEnergySource, IEnergyStorage {

	protected EnumFacing emitDir;
	protected int storedE;
	private int maxE;
	public int output;
	private int tier;
	private final boolean allowTp;
	
	protected TileEnergyStorage(int maxEnergy, int output, int tier, boolean allowTp) {
		this.storedE = 0;
		this.maxE = maxEnergy;
		this.output = output;
		this.tier = tier;
		this.allowTp = allowTp;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.storedE = tag.getInteger("charge");
		this.maxE = tag.getInteger("maxCharge");
		this.output = tag.getInteger("output");
	}

	@Nonnull
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("charge", storedE);
		tag.setInteger("maxCharge", maxE);
		tag.setInteger("output", output);
		return super.writeToNBT(tag);
	}
	
	@Override
	public NBTTagCompound writePacketData(NBTTagCompound data) {
		data.setInteger("charge", this.storedE);
		data.setInteger("maxCharge", this.maxE);
		data.setInteger("output", this.output);
		data.setInteger("tier", this.tier);
		return super.writePacketData(data);
	}

	@Override
	public void readPacketData(NBTTagCompound data) {
		this.storedE = data.getInteger("charge");
		this.maxE = data.getInteger("maxCharge");
		this.output = data.getInteger("output");
		this.tier = data.getInteger("tier");
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
		return allowTp;
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

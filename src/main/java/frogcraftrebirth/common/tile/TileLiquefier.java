/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:55:25 PM, Apr 2, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.tile;

import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileLiquefier extends TileEnergySink {
	
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	
	public int process;

	public TileLiquefier() {
		super(2, 10000);
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		super.update();
		//TODO
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.process = tag.getInteger("process");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setInteger("process", this.process);
		return super.writeToNBT(tag);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		else return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T)tank;
		else return super.getCapability(capability, facing);
	}

}

/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 2:55:25 PM, Apr 2, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.tile;

import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileLiquifier extends TileFrogMachine implements IFluidHandler {
	
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	
	public int process;

	public TileLiquifier() {
		super(4, "liquifier", 2, 10000);
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
	public int[] getSlotsForFace(EnumFacing side) {
		switch (side) {
			case UP:
				return new int[] {0};
			case DOWN:
				return new int[] {3};
			default:
				return (int[])null;
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack item, EnumFacing direction) {
		return index == 0;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack item, EnumFacing direction) {
		return index == 3;
	}

	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		return this.tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		return this.tank.getFluid().getFluid() != null ? false : this.tank.getFluid().getFluid() == fluid;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return new FluidTankInfo[] {this.tank.getInfo()};
	}

}

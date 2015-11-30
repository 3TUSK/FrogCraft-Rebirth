package frogcraftrewrite.api.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
/**
 * Create a instance of this class will provide a efficient way to manage fluid.
 * @author 3TUSK
 */
public class FrogFluidTank implements IFluidTank {

	private final int capacity;
	private FluidStack fluidInv;
	
	public FrogFluidTank (int capacity) {
		this.capacity = capacity;
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		this.fluidInv = FluidStack.loadFluidStackFromNBT(tag);
	}
	
	public void writeToNBT(NBTTagCompound tag) {	
		if (fluidInv != null) fluidInv.writeToNBT(tag);
	}
	
	@Override
	public FluidStack getFluid() {
		return fluidInv;
	}

	@Override
	public int getFluidAmount() {
		return fluidInv != null ? fluidInv.amount : 0;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (!doFill) return 0;
		
		if (this.fluidInv == null) {
			this.fluidInv = resource;
			return this.fluidInv.amount;
		}
		
		if (this.fluidInv.getFluid() == resource.getFluid()) {
			int newAmount = this.fluidInv.amount + resource.amount;
			newAmount = newAmount > capacity ? capacity : newAmount;
			return newAmount > capacity ? resource.amount : capacity - this.fluidInv.amount;
		}
		
		return 0;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (!doDrain) return null;
		
		if (this.fluidInv == null) return null;
		
		if (maxDrain > capacity || this.fluidInv.amount <= maxDrain) {
			FluidStack drained = this.fluidInv.copy();
			this.fluidInv = null;
			return drained;
		} else {
			int isDraining = this.fluidInv.amount - maxDrain;
			this.fluidInv.amount -= isDraining;
			return new FluidStack(this.fluidInv.getFluid(), isDraining);
		}
	}
	
	public void writeTankData(DataOutputStream output) throws IOException {
		output.writeInt(fluidInv != null ? fluidInv.getFluidID() : -1);
		output.writeInt(getFluidAmount());
	}
	
	public void readTankData(DataInputStream input) throws IOException {
		int fluidID = input.readInt(), fluidAmount = input.readInt();
		Fluid fluid = fluidID != -1 ? FluidRegistry.getFluid(fluidID) : null;
		if (fluid != null)
			this.forceFillTank(new FluidStack(fluid, fluidAmount));
		else
			this.forceFillTank(null);
	}
	
	public int getFluidID() {
		return this.fluidInv.getFluidID();
	}
	
	@SideOnly(Side.CLIENT)
	public void forceFillTank(FluidStack stack) {
		this.forceDrainTank();
		this.fluidInv = stack;
	}
	
	@SideOnly(Side.CLIENT)
	public void forceDrainTank() {
		this.fluidInv = null;
	}

}

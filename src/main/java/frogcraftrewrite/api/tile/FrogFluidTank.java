package frogcraftrewrite.api.tile;

import net.minecraft.nbt.NBTTagCompound;
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
		fluidInv.writeToNBT(tag);
	}
	
	@Override
	public FluidStack getFluid() {
		return fluidInv;
	}

	@Override
	public int getFluidAmount() {
		return fluidInv.amount;
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
			this.fluidInv = null;
			return this.fluidInv;
		} else {
			int isDraining = this.fluidInv.amount - maxDrain;
			this.fluidInv.amount -= isDraining;
			return new FluidStack(this.fluidInv.getFluid(), isDraining);
		}
	}

}

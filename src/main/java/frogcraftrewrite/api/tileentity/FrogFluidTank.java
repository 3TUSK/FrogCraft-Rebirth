package frogcraftrewrite.api.tileentity;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
/**
 * Create a instance of this class will provide a efficient way to manage fluid.
 * @author 3TUSK
 */
public class FrogFluidTank implements IFluidTank {

	int capacity;
	FluidStack fluidInv;
	
	public FrogFluidTank (int capacity) {
		this.capacity = capacity;
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

	//Todo: major logic
	
	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return null;
	}

}

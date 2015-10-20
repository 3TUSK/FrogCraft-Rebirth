package frogcraftrewrite.common.tile;

import frogcraftrewrite.api.tileentity.FrogFluidTank;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileFluidOutputHatch extends TileFrogInventory implements IFluidTank {

	private FrogFluidTank tank = new FrogFluidTank(8000);
	
	public TileFluidOutputHatch() {
		super(2, "TileEntityFluidOutput");
	}

	@Override
	public FluidStack getFluid() {
		return tank.getFluid();
	}

	@Override
	public int getFluidAmount() {
		return tank.getFluidAmount();
	}

	@Override
	public int getCapacity() {
		return tank.getCapacity();
	}

	@Override
	public FluidTankInfo getInfo() {
		return tank.getInfo();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

}

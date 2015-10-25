package frogcraftrewrite.common.tile;

import frogcraftrewrite.api.tile.FrogFluidTank;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileThermalCracker extends TileFrogMachine implements IFluidTank {
	
	private FrogFluidTank tank = new FrogFluidTank(16000);

	protected TileThermalCracker() {
		super(4, "TileThermalCracker", 2, 10000);
		//0 input 1 output 2 fluidContainer input 3 fluidContainer output
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
			case 0: 
				return new int[] {1};
			case 1: 
				return new int[] {0};
			default: 
				return null;
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return side == 0 && slot == 1;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return side == 1 && slot == 0;
	}

	@Override
	public FluidStack getFluid() {
		return this.tank.getFluid();
	}

	@Override
	public int getFluidAmount() {
		return this.tank.getFluidAmount();
	}

	@Override
	public int getCapacity() {
		return this.tank.getCapacity();
	}

	@Override
	public FluidTankInfo getInfo() {
		return this.tank.getInfo();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}
	
}

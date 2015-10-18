package frogcraftrewrite.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileConbustionFurnace extends TileFrogGenerator implements IFluidTank {

	public boolean isWorking;
	public int buffer, tankCapacity;
	protected FluidStack tank;
	
	public TileConbustionFurnace() {
		super(1, 64);
		this.inv = new ItemStack[5]; //in original FrogCraft there is 4, but I add one for solid waste
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
	}
	
	@Override
	public double getOfferedEnergy() {
		return isWorking ? super.getOfferedEnergy() : 0;
	}
	
	@Override
	public String getInventoryName() {
		return "";
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
			case 0:
				return new int[] {2}; //solid waste slot
			case 1:
				return new int[] {0}; //fuel slot
			default: 
				return (int[])null;
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return side == 1 && slot == 0;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return side == 0 && slot == 2;
	}

	@Override
	public FluidStack getFluid() {
		return tank;
	}

	@Override
	public int getFluidAmount() {
		return tank.amount;
	}

	@Override
	public int getCapacity() {
		return tankCapacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		//TODO
		return 0;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		//TODO
		return null;
	}

}

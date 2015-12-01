package frogcraftrewrite.common.tile;

import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrewrite.api.FrogFuelHandler;
import frogcraftrewrite.api.impl.FrogFluidTank;
import frogcraftrewrite.common.lib.tile.TileFrogGenerator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileCombustionFurnace extends TileFrogGenerator implements IFluidTank {

	public boolean isWorking;
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	public int time, timeMax;
	public int maxCharge = 5000;
	
	public TileCombustionFurnace() {
		super(4, "TileEntityCombustionFurnace", 1, 5000);
		//0 input 1 output 2 fluid container input 3 fluid container output
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
		if (this.getFluidAmount() >= this.getCapacity()) {
			this.isWorking = false;
			return;
		}
		//Use vanilla furnace standard.
		if (this.inv[0] != null && GameRegistry.getFuelValue(this.inv[0]) > 0) { 
			this.isWorking = true;
			this.decrStackSize(0, 1);
			this.timeMax = FrogFuelHandler.FUEL_REG.getBurnTime(this.inv[0]);
			this.time = FrogFuelHandler.FUEL_REG.getBurnTime(this.inv[0]);
		} else {
			this.timeMax = 0;
			this.isWorking = false;
		}
		
		if (this.isWorking) {
			this.charge += 10;
			this.time--;
		}
		
		if (this.time == 0) {
			this.timeMax = 0;
			this.isWorking = false;
			//inv[1] = FrogFuelHandler.FUEL_REG.
		}
		
		markDirty();
	}
	
	@Override
	public double getOfferedEnergy() {
		return isWorking ? super.getOfferedEnergy() : 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
			case 1:
				return new int[] {0}; //fuel slot
			default: 
				return (int[])null;
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return side == 1;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return side == 0 && slot == 2;
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
		return new FluidTankInfo(this);
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

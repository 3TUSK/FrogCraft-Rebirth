package frogcraftrewrite.common.tile;

import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.recipes.CombustionFurnaceRecipe;
import frogcraftrewrite.api.tile.FrogFluidTank;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileCombustionFurnace extends TileFrogGenerator implements IFluidTank {

	public boolean isWorking;
	public int tankCapacity;
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	private int heat;
	
	public TileCombustionFurnace() {
		super(4, "TileEntityCombustionFurnace", 1, 5000);
		//in original FrogCraft there is 4, but I will add one for solid waste
		//0 input 1 output 2 fluid container input 3 fluid container output
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.getFluidAmount() >= this.getCapacity()) {
			this.isWorking = false;
			markDirty();
			return;
		}
		//todo:kill combustion furnace recipe!!! There is no necessary to use it!!!
		CombustionFurnaceRecipe recipe = FrogAPI.managerCFG.<ItemStack>getRecipe(inv[0]);
		if (recipe != null) {
			this.isWorking = true;
			this.inv[0].stackSize--;
			this.heat = 100;
		} else
			this.isWorking = false;
		
		if (this.isWorking) {
			this.energy += 10;
			this.heat--;
		}
		if (this.heat == 0);//todo
	}
	
	@Override
	public double getOfferedEnergy() {
		return isWorking ? super.getOfferedEnergy() : 0;
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
		return this.tank.getFluid();
	}

	@Override
	public int getFluidAmount() {
		return this.tank.getFluidAmount();
	}

	@Override
	public int getCapacity() {
		return this.tankCapacity;
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

package frogcraftrewrite.common.tile;

import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.recipes.CondenseTowerRecipe;
import frogcraftrewrite.api.tile.FrogFluidTank;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileCondenseTower extends TileFrogMachine implements IFluidHandler {
	
	public FrogFluidTank tank = new FrogFluidTank(8000);
	private boolean isCompleted;
	
	@SuppressWarnings("unused") //Will be used later
	private static final char[] 
			STRUCTURE_ARRAY 		= new char[] {'p', 'c', 't', 't', 'o', 'o', 'o', 'o'},
			STRUCTURE_ARRAY_NO_PUMP	= new char[] {'c', 't', 't', 'o', 'o', 'o', 'o'};
	
	protected TileCondenseTower() {
		super(2, "TileCondenseTower", 2, 10000);
		//0,2 for input; 1,3 for output
	}
	
	public boolean checkStructure() {
		return false;
	}
	
	public void updateEntity() {
		super.updateEntity();
		if (!checkStructure())
			return;
		else {
			this.isCompleted = true;
			//TODO send message
		}
		
		CondenseTowerRecipe recipe = (CondenseTowerRecipe)FrogAPI.managerCT.<FluidStack>getRecipe(tank.getFluid());
		
		if (recipe != null) {
			
		}
	}

	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		this.tank.writeToNBT(tag);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
			case 0: return new int[] {1};
			case 1: return new int[] {0};
			default: return (int[])null;
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return side == 1 && slot == 0;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return side == 0 && slot == 1;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		return this.tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {this.tank.getInfo()};
	}

}

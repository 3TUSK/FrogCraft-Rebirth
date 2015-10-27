package frogcraftrewrite.common.tile;

import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.recipes.CondenseTowerRecipe;
import frogcraftrewrite.api.tile.FrogFluidTank;
import frogcraftrewrite.common.block.BlockCondenseTower;
import frogcraftrewrite.common.event.MultiBlockEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileCondenseTower extends TileFrogMachine implements IFluidHandler {
	
	public FrogFluidTank tank = new FrogFluidTank(8000);
	private boolean isCompleted = false, craftingFinished = false;
	private int tick;
	
	protected TileCondenseTower() {
		super(2, "TileCondenseTower", 2, 10000);
		//0 for input; 1 for output
	}
	
	private boolean checkStructure() {
		for (int i=1;i<7;i++) {
			if (i == 1 || i == 2) {
				if (!(worldObj.getBlock(xCoord, yCoord+i, zCoord) instanceof BlockCondenseTower) && !(worldObj.getBlockMetadata(xCoord, yCoord+i, zCoord) == 2))
					return false;
			} else {
				if (!(worldObj.getBlock(xCoord, yCoord+i, zCoord) instanceof BlockCondenseTower) && !(worldObj.getBlockMetadata(xCoord, yCoord+i, zCoord) == 3))
					return false;
			}
		}
		return true;
	}
	
	public boolean isCompleted() {
		return this.isCompleted;
	}
	
	public void updateEntity() {
		super.updateEntity();
		if (!isCompleted) {
			if (!checkStructure())
				return;
			else {
				this.isCompleted = true;
				MinecraftForge.EVENT_BUS.post(new MultiBlockEvent(this));
			}
		}
		
		CondenseTowerRecipe recipe = FrogAPI.managerCT.<FluidStack>getRecipe(tank.getFluid());
		
		if (recipe != null && !craftingFinished) {
			if (tick == 0) {
				this.drain(ForgeDirection.UNKNOWN, recipe.getInput().amount, true);
				this.tick = recipe.getTime();
				tick--;
				energy -= 500;//Should be configurable
			} else {
				tick--;
				energy -= 500;
			}
		} else if (craftingFinished){
			java.util.Set<FluidStack> outputs = recipe.getOutput();
			for (int i=3;i<=6;i++) {
				for (FluidStack fluid : outputs) {
					if (((TileFluidOutputHatch)worldObj.getTileEntity(xCoord, yCoord+i, zCoord)).canDrain(ForgeDirection.UNKNOWN, fluid.getFluid()));//TODO
				}
			}
		} else
			return;
		
		this.markDirty();
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

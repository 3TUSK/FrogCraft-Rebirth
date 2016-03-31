package frogcraftrebirth.common.tile;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.CondenseTowerRecipe;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.block.BlockCondenseTower;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileCondenseTower extends TileFrogMachine implements IFluidHandler {
	
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	private boolean structureCompleted = false, craftingFinished = false;
	public int tick;
	
	public TileCondenseTower() {
		super(2, "TileCondenseTower", 2, 10000);
		//0 for input; 1 for output
	}
	
	private boolean checkStructure() {
		for (int i=1;i<7;i++) {
			if (i == 1 || i == 2) {
				if (!(worldObj.getBlock(xCoord, yCoord+i, zCoord) instanceof BlockCondenseTower) && !(worldObj.getBlockMetadata(xCoord, yCoord+i, zCoord) == 1))
					return false;
			} else {
				if (!(worldObj.getBlock(xCoord, yCoord+i, zCoord) instanceof BlockCondenseTower) && !(worldObj.getBlockMetadata(xCoord, yCoord+i, zCoord) == 2))
					return false;
			}
		}
		return true;
	}
	
	public boolean isCompleted() {
		return this.structureCompleted;
	}
	
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) return;
		
		if (!structureCompleted) {
			if (!checkStructure())
				return;
		}
		
		CondenseTowerRecipe recipe = FrogAPI.managerCT.<FluidStack>getRecipe(tank.getFluid());
		
		if (recipe != null && !craftingFinished) {
			if (tick == 0) {
				this.drain(ForgeDirection.UNKNOWN, recipe.getInput().amount, true);
				this.tick = recipe.getTime();
			}
			tick--;
			charge -= 500;
		} else if (craftingFinished){
			java.util.Set<FluidStack> outputs = recipe.getOutput();
			for (int i=3;i<=6;i++) {
				for (FluidStack fluid : outputs) {
					if (((TileFluidOutputHatch)worldObj.getTileEntity(xCoord, yCoord+i, zCoord)).canDrain(ForgeDirection.UNKNOWN, fluid.getFluid())) {
						((TileFluidOutputHatch)worldObj.getTileEntity(xCoord, yCoord+i, zCoord)).drain(ForgeDirection.UNKNOWN, fluid, true);
					}
				}
			}
		}
		
		this.markDirty();
		this.sendTileUpdatePacket(this);
	}

	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.tick = tag.getInteger("tick");
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		this.tank.writeToNBT(tag);
		tag.setInteger("tick", this.tick);
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
		return fluid == FrogFluids.liquidAir;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {this.tank.getInfo()};
	}

	public FluidTankInfo[] getTankInfo() {
		return this.getTankInfo(ForgeDirection.UNKNOWN);
	}
}

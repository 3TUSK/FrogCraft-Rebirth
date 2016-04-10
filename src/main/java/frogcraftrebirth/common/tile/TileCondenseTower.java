package frogcraftrebirth.common.tile;

import java.util.ArrayList;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.api.tile.ICondenseTowerStructure;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileCondenseTower extends TileFrogMachine implements IFluidHandler {
	
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	private boolean structureCompleted = false;
	public int tick;
	private CondenseTowerRecipe recipe;
	private ArrayList<ICondenseTowerOutputHatch> outputs = new ArrayList<ICondenseTowerOutputHatch>();
	private ArrayList<ICondenseTowerStructure> structures = new ArrayList<ICondenseTowerStructure>();
	
	public TileCondenseTower() {
		super(2, "TileCondenseTower", 2, 10000);
		//0 for input; 1 for output
	}
	
	private boolean checkStructure() {
		for (int i = 1; i < 7; i++) {
			TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
			if (i < 3 && tile instanceof ICondenseTowerStructure) {
				this.registerSturcture((ICondenseTowerStructure)tile);
				continue;
			} else if (tile instanceof ICondenseTowerOutputHatch) {
				this.registerOutputHatch((ICondenseTowerOutputHatch)tile);
				continue;
			} else
				return false;
		}
		return true;
	}
	
	public boolean isCompleted() {
		return this.structureCompleted;
	}
	
	public void updateEntity() {
		if (worldObj.isRemote) 
			return;
		
		super.updateEntity();
		
		if (!structureCompleted) {
			if (!checkStructure()) {
				this.outputs.clear();
				this.structures.clear();
				return;
			}
			else
				structureCompleted = true;
		}
		
		if (recipe == null)
			recipe = FrogAPI.managerCT.<FluidStack>getRecipe(tank.getFluid());
		
		if (checkRecipe(this.recipe)) {
			this.tank.drain(recipe.getInput().amount, !worldObj.isRemote);
			this.tick = recipe.getTime();
		} else 
			return;
		
		--tick;
		charge -= 500;
		
		if (tick <= 0) { 
			// Theoretically tick cannot less than 0, but this is prevent bad things happening
			java.util.Set<FluidStack> outputs = recipe.getOutput();
			for (FluidStack fluid : outputs) {
				for (ICondenseTowerOutputHatch output : this.outputs) {
					if (output.canInject(fluid)) {
						output.inject(fluid, !worldObj.isRemote);
						break;
					}
				}
			}
			this.tick = 0;
			this.recipe = null;
		}
		
		this.markDirty();
		this.sendTileUpdatePacket(this);
	}

	private boolean checkRecipe(CondenseTowerRecipe aRecipe) {
		for (FluidStack fluid : aRecipe.getOutput()) {
			boolean checkPass = false;
			for (ICondenseTowerOutputHatch output : outputs) {
				if (output.canInject(fluid)) {
					checkPass = true;
					break;
				}
			}
			if (!checkPass)
				return false;
		}
		return true;
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
	
	public boolean registerOutputHatch(ICondenseTowerOutputHatch output) {
		return output != null ? outputs.add(output) : false;
	}
	
	public boolean registerSturcture(ICondenseTowerStructure structure) {
		return structure != null ? structures.add(structure) : false;
	}
}

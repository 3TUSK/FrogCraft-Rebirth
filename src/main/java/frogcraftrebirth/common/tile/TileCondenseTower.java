package frogcraftrebirth.common.tile;

import java.util.ArrayList;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.api.tile.ICondenseTowerPart;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileCondenseTower extends TileEnergySink implements ICondenseTowerCore {
	
	public final ItemStackHandler inv = new ItemStackHandler(2);
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	private boolean structureCompleted = false;
	public int tick;
	private ICondenseTowerRecipe recipe;
	private ArrayList<ICondenseTowerOutputHatch> outputs = new ArrayList<ICondenseTowerOutputHatch>();
	private ArrayList<ICondenseTowerPart> structures = new ArrayList<ICondenseTowerPart>();
	
	public TileCondenseTower() {
		super(2, 10000);
	}
	
	@Override
	public boolean checkStructure() {
		for (int i = 1; i < 7; i++) {
			TileEntity tile = worldObj.getTileEntity(this.getPos().up(i));
			if (i > 2 && tile instanceof ICondenseTowerOutputHatch) {
				((ICondenseTowerOutputHatch)tile).onConstruct(this);
				this.registerOutputHatch((ICondenseTowerOutputHatch)tile);
				continue;
			} else if (tile instanceof ICondenseTowerOutputHatch) {
				((ICondenseTowerPart)tile).onConstruct(this);
				this.registerSturcture((ICondenseTowerPart)tile);
				continue;
			} else {
				structureCompleted = false;
				return false;
			}
		}
		structureCompleted = true;
		return true;
	}
	
	@Override
	public boolean isCompleted() {
		return this.structureCompleted;
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote) 
			return;
		super.update();
		if (!structureCompleted) {
			if (!checkStructure()) {
				onDestruct(this);
				return;
			}
			else
				onConstruct(this);
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
	
	@Override
	public void behave() {
		
	}
	
	@Override
	public void onConstruct(ICondenseTowerCore core) {
		
	}
	
	@Override
	public void onDestruct(ICondenseTowerCore core) {
		for (ICondenseTowerOutputHatch output : outputs) {
			output.onDestruct(core);
		}
		for (ICondenseTowerPart part : structures) {
			part.onDestruct(core);
		}
		this.outputs.clear();
		this.structures.clear();
	}

	@Override
	public ICondenseTowerCore getMainBlock() {
		return this;
	}

	private boolean checkRecipe(ICondenseTowerRecipe aRecipe) {
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
		this.inv.deserializeNBT(tag.getCompoundTag("inv"));
		this.tick = tag.getInteger("tick");
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setTag("inv", inv.serializeNBT());
		tag.setInteger("tick", this.tick);
		return super.writeToNBT(tag);
	}

	//TODO: ItemHandler
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		else return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T)tank;
		else return super.getCapability(capability, facing);
	}
	
	public boolean registerOutputHatch(ICondenseTowerOutputHatch output) {
		return output != null ? outputs.add(output) : false;
	}
	
	public boolean registerSturcture(ICondenseTowerPart structure) {
		return structure != null ? structures.add(structure) : false;
	}
}

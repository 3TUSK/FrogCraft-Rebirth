package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.OreStack;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileAdvChemReactor extends TileEnergySink implements IHasWork {
	
	//0 for module, 1-5 for input, 6-10 for output, 11 for cell input and 12 for cell output
	public final IItemHandler module = new ItemStackHandler();
	public final IItemHandler input = new ItemStackHandler(5);
	public final IItemHandler output = new ItemStackHandler(5);
	public final IItemHandler cellInput = new ItemStackHandler();
	public final IItemHandler cellOutput = new ItemStackHandler();
	
	public int process, processMax;
	private boolean working;
	private IAdvChemRecRecipe recipe;
	
	public TileAdvChemReactor() {
		super(2, 100000);
	}
	
	@Override
	public boolean isWorking() {
		return working;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.process = tag.getInteger("process");
		this.processMax = tag.getInteger("processMax");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("working", this.working);
		tag.setInteger("process", this.process);
		tag.setInteger("processMax", this.processMax);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		this.process = input.readInt();
		this.processMax = input.readInt();
		this.working = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		output.writeInt(process);
		output.writeInt(processMax);
		output.writeBoolean(working);
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote) {
			worldObj.markBlockRangeForRenderUpdate(getPos(), getPos());
			return;
		}
		super.update();
		
		ItemStack[] inputs = new ItemStack[] {input.getStackInSlot(0), input.getStackInSlot(1), input.getStackInSlot(2), input.getStackInSlot(3), input.getStackInSlot(4)};
		
		recipe = (IAdvChemRecRecipe)FrogAPI.managerACR.<ItemStack>getRecipe(inputs);
		
		if (checkIngredient(recipe)) {
			this.consumeIngredient(recipe.getInputs());
			this.process = 0;
			this.processMax = recipe.getTime();
			this.working = true;
		}
		
		if (working && charge >= recipe.getEnergyRate()) {
			this.charge -= recipe.getEnergyRate();
			++process;
		}
		
		if (process == processMax) {
			this.produce();
			this.working = false;
			this.process = 0;
			this.processMax = 0;
		}

		this.markDirty();
		this.sendTileUpdatePacket(this);
	}
	
	private boolean checkIngredient(IAdvChemRecRecipe recipe) {
		if (recipe == null)
			return false;
		
		return false;
	}
	
	private void consumeIngredient(Collection<OreStack> toBeConsumed) {
		// to be implemented
	}
	
	private void produce() {
		// to be implemented
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? true : super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			switch (facing) {
			case UP:
				return (T)input;
			case DOWN:
				return (T)output;
			case NORTH:
			case EAST:
				return (T)cellInput;
			case SOUTH:
			case WEST:
				return (T)cellOutput;
			default:
				break;
			}
		}	
		return super.getCapability(capability, facing);
	}

}

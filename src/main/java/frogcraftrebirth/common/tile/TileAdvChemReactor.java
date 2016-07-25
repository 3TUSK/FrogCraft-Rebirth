package frogcraftrebirth.common.tile;

import java.util.Collection;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.OreStack;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.tile.IAdvChemReactor;
import frogcraftrebirth.common.lib.tile.TileFrogEnergySink;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileAdvChemReactor extends TileFrogEnergySink implements IAdvChemReactor {
	
	public final ItemStackHandler inv = new ItemStackHandler(13);
	
	public int process, processMax;
	private boolean working;
	private boolean changed = false;
	private IAdvChemRecRecipe recipe;
	
	public TileAdvChemReactor() {
		super(2, 100000);
		//0 for module, 1-5 for input, 6-10 for output, 11 for cell input and 12 for cell output
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
	public void update() {
		if (worldObj.isRemote) 
			return;
		super.update();
		
		ItemStack[] inputs = null;
		
		recipe = (IAdvChemRecRecipe)FrogAPI.managerACR.<ItemStack>getRecipe(inputs);
		
		if (checkIngredient(recipe)) {
			this.consumeIngredient(recipe.getOutputs());
			this.working = true;
			this.changed = true;
		}
		
		if (working && charge >= recipe.getEnergyRate()) {
			this.charge -= recipe.getEnergyRate();
			++process;
		} else
			return;
		
		if (process == processMax) {
			this.produce();
			this.changed = true;
			this.working = false;
		}
		
		if (changed) {
			this.markDirty();
			this.sendTileUpdatePacket(this);
			changed = false;
		}
	}
	
	@Override
	public double modifyReactionRate(ItemStack... catalyst) {
		return 1D;
	}
	
	@Override
	public boolean checkIngredient(IAdvChemRecRecipe recipe) {
		if (recipe == null)
			return false;
		
		return false;
	}
	
	@SuppressWarnings("unused")
	private void consumeIngredient(Collection<OreStack> toBeConsumed) {
		for (OreStack ore : toBeConsumed) {
			for (int i = 1; i <= 5 ;i++) {
				//if (ore.consumable(inv[i])) {
				//	ore.consume(inv[i]);
				//	break;
				//}
			}
		}
	}
	
	@SuppressWarnings("unused")
	public void produce() {
		for (OreStack ore : recipe.getOutputs()) {
			boolean match = false;
			for (int i = 6; i <= 10; i++) {
				//if (ore.stackable(inv[i])) {
				//	ore.stack(inv[i]);
				//	match = true;
				//	break;
				//}
			}
			
			if (!match) {
				//for (int i = 6; i <= 10; i++) 
				//	if (inv[i] == null) {
				//		ore.stack(inv[i]);
				//		break;
				//	}
			}
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? true : super.hasCapability(capability, facing);
	}

}

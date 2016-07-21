package frogcraftrebirth.common.tile;

import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

/**
 * Will be separated into a new mod, hopefully...
 */
@Deprecated
public abstract class TileFrogInductionalDevice extends TileFrogMachine {
	
	private static final int PROCESS_MAX = 100;

	//Processing progress, as well as the max value
	public int process;
	public int heat;
	public int tick;

	private boolean isInENet;

	public TileFrogInductionalDevice(String name) {
		super(13, name, 2, 10000);
		// [0] is the charger slot, 1-6 are input, 7-12 are output
		this.heat = 0;
		this.process = 0;
		this.tick = 0;
	}

	@Override
	public void invalidate() {
		if (!worldObj.isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
		super.invalidate();
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
		
		if (!isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}

		boolean invalidInput = true;
		for (int a = 1; a <= 6; a++) {
			if (canProcess())
				invalidInput = false;
		}

		if (!invalidInput && hasOutputSpace()) {
			tick++;
			if (canProcess()) {
				process++;
				if (heat > 30) {
					if (heat % 5 == 0) {
						heat++;
					}
					if (heat > 90) {
						process += 4;
						charge -= 5;
					} else if (heat > 50) {
						process += 3;
						charge -= 20;
					} else {
						process += 1;
						charge -= 40;
					}
				} else {
					charge -= 50;
				}
			}
			if (process == PROCESS_MAX) {
				process(hasOutputSpace());
				process = 0;
			}
		}

		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
			if (tick >= 50) {
				heat += 1;
				tick = 0;
			}
			if (heat > 100)
				heat = 100;
		} else {
			heat -= (Math.sqrt(heat / 5)) / 40 + 0.009F;
			if (heat < 0)
				heat = 0;
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		process = tag.getInteger("process");
		heat = tag.getInteger("heat");
		tick = tag.getInteger("tick");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("process", process);
		tag.setInteger("heat", heat);
		tag.setInteger("tick", tick);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack aStack) {
		return slot >= 1 && slot <= 6;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side != 5 ? new int[] { 1, 2, 3, 4, 5, 6 } : new int[] { 7, 8, 9, 10, 11, 12 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack aStack, int side) {
		return slot >= 1 && slot <= 6;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack aStack, int side) {
		return slot >= 7 && slot <= 12;
	}

	protected abstract boolean canProcess();

	protected abstract ItemStack getOutputFrom(ItemStack input);

	private boolean hasOutputSpace() {
		for (int n = 7; n < 12; n++) {
			if (inv[n] == null) {
				return true;
			}
		}

		for (int n = 7; n < 12; n++) {
			if (inv[n].getItem() == getOutputFrom(inv[n]).getItem()) {
				if (inv[n].stackSize < inv[n].getMaxStackSize())
					return true;
			}
		}
		return false;
	}

	private void process(boolean isSimluate) {
		if (isSimluate)
			return;

		for (int n = 1; n <= 6; n++) {
			ItemStack output = getOutputFrom(inv[n]);
			if (output != null) {
				if (inv[n + 6] == null) {
					setInventorySlotContents(n + 6, output.copy());
				} else if (inv[n + 6].getItem() == output.getItem()) {
					inv[n + 6].stackSize += output.stackSize;
					if (inv[n + 6].stackSize >= inv[n + 6].getMaxStackSize()) {
						inv[n + 6].stackSize = inv[n + 6].getMaxStackSize();
					}
				} else {

				}
			}
		}
	}

}

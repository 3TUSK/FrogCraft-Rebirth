package frogcraftrewrite.common.lib.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;

public abstract class TileFrogInductionalDevice extends TileFrog implements IEnergySink, ISidedInventory {
	private final int PROCESS_MAX = 100;

	/** The inventory. */
	public ItemStack[] inv;
	/** The amount of energy contained */
	public int energy, energyMax;
	/** Processing progress, as well as the max value */
	public int process;
	public int heat;
	public int tick;

	private boolean isInENet;

	public TileFrogInductionalDevice() {
		this.inv = new ItemStack[13];
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
						energy -= 5;
					} else if (heat > 50) {
						process += 3;
						energy -= 20;
					} else {
						process += 1;
						energy -= 40;
					}
				} else {
					energy -= 50;
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
		NBTTagList invList = tag.getTagList("inventory", 10);
		for (int n = 0; n < invList.tagCount(); n++) {
			NBTTagCompound aItem = invList.getCompoundTagAt(n);
			byte slot = aItem.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(aItem);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("process", process);
		tag.setInteger("heat", heat);
		tag.setInteger("tick", tick);
		NBTTagList list = new NBTTagList();
		for (int n = 0; n < inv.length; n++) {
			ItemStack stack = inv[n];
			if (stack != null) {
				NBTTagCompound itemTag = new NBTTagCompound();
				itemTag.setByte("Slot", (byte) n);
				stack.writeToNBT(itemTag);
				list.appendTag(itemTag);
			}
		}
		tag.setTag("inventory", list);
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return direction != ForgeDirection.UP && direction != ForgeDirection.DOWN;
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int itemNum) {
		ItemStack stack = inv[slot];
		if (stack.stackSize <= itemNum)
			return null;
		stack.stackSize -= itemNum;
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
		inv[slot] = stack;
	}

	@Override
	public abstract String getInventoryName();

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
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

	@Override
	public double getDemandedEnergy() {
		return 128.0D;
	}

	@Override
	public int getSinkTier() {
		return 2;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		energy += amount;
		if (energy > energyMax)
			energy = energyMax;
		return 0D;
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

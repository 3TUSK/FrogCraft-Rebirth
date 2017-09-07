package frogcraftrebirth.common.lib.capability;

import frogcraftrebirth.common.lib.recipes.FrogRecipeInputs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;

/**
 * An specialized {@link IItemHandler} that has only one slot and
 * can only accept empty IC2 Universal Fluid Cell.
 *
 * Used by Adv. Chemical Reactor for fast recipe checking.
 */
public class ItemHandlerUniversalCell implements IItemHandler, IItemHandlerModifiable {

	private int count;

	/**
	 * Getter of the count of cells stored.
	 * @return current count of cells
	 */
	public int getCellCount() {
		return this.count;
	}

	@Override
	public int getSlots() {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @implNote
	 * By default, it returns a read-only view of the content; modifying this view
	 * will cause no effect on this IItemHandler.
	 */
	@Nonnull
	@Override
	public ItemStack getStackInSlot(int slot) {
		if (slot == 0) {
			ItemStack item = FrogRecipeInputs.UNI_CELL.copy();
			item.setCount(count);
			return item;
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Nonnull
	@Override
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
		if (slot == 0 && FrogRecipeInputs.UNI_CELL.isItemEqual(stack)) {
			int newCount = this.count += stack.getCount();
			if (newCount > 64) {
				if (!simulate) {
					this.count = 64;
				}
				return stack.splitStack(64 - this.count);
			} else {
				if (!simulate) {
					this.count = newCount;
				}
				return ItemStack.EMPTY;
			}
		}
		return stack;
	}

	@Nonnull
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot == 0) {
			int newCount = this.count -= amount;
			if (newCount < 0) {
				ItemStack inReturn = FrogRecipeInputs.UNI_CELL.copy();
				inReturn.setCount(this.count);
				if (!simulate) {
					this.count = 0;
				}
				return inReturn;
			} else {
				ItemStack inReturn = FrogRecipeInputs.UNI_CELL.copy();
				inReturn.setCount(amount);
				if (!simulate) {
					this.count = newCount;
				}
				return inReturn;
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}

	// Implemented so that Forge's SlotItemHandler can be used.
	@Override
	public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
		if (slot == 0 && FrogRecipeInputs.UNI_CELL.isItemEqual(stack)) {
			this.count = stack.getCount();
		}
	}
}

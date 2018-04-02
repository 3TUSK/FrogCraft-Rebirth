/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * A specialized {@link IItemHandler} that also implements {@link IFluidHandler},
 * effectively making it storing both ItemStack and FluidStack at the same time.
 * That being said, a slot in the sense of IItemHandler may hold FluidStack.
 */
@SuppressWarnings("all") // TODO Clean up all warnings
public final class ItemFluidHybridHandler
		implements IItemHandler, IItemHandlerModifiable, IFluidHandler {

	enum IOStrategy {
		/**
		 * Represent the status of permitting neither input nor output.
		 */
		ENCLOSED(false, false),
		/**
		 * Represent the status of permitting input only.
		 */
		IN(true, false),
		/**
		 * Represent the status of permitting output only.
		 */
		OUT(false, true),
		/**
		 * Represent the status of permitting both input and output.
		 */
		OPEN(true, true);

		final boolean permitInput, permitOutput;

		IOStrategy(boolean permitInput, boolean permitOutput) {
			this.permitInput = permitInput;
			this.permitOutput = permitOutput;
		}
	}

	private static final class ItemFluidHybridSlot implements IFluidTank {
		@Nonnull
		private ItemStack itemContent = ItemStack.EMPTY;
		@Nullable
		private FluidStack fluidContent = null;
		private int capacity;
		IOStrategy strategy = IOStrategy.OPEN;

		public boolean isOccupiedByItem() {
			return !this.itemContent.isEmpty() && this.fluidContent == null;
		}

		public boolean isOccupiedByFluid() {
			return this.fluidContent != null && this.itemContent.isEmpty();
		}

		public ItemStack getItemContent() {
			return isOccupiedByFluid() ? ItemStack.EMPTY : this.itemContent;
		}

		@Nullable
		public FluidStack getFluidContent() {
			return isOccupiedByItem() ?  null : this.fluidContent;
		}

		void forceSetContent(final ItemStack itemStack) {
			this.itemContent = itemStack;
			this.fluidContent = null;
		}

		public ItemStack tryInsert(@Nonnull ItemStack stack, boolean simulate) {
			if (this.isOccupiedByItem() && this.itemContent.isItemEqual(stack)) {
				ItemStack remainder = simulate ? stack.copy() : stack;
				remainder.splitStack(Math.min(remainder.getCount() + this.itemContent.getCount(), this.itemContent.getMaxStackSize()));
				return remainder;
			} else {
				return stack;
			}
		}

		@Nullable
		public FluidStack tryInsert(@Nullable FluidStack stack, boolean simulate) {
			if (this.isOccupiedByFluid() && this.fluidContent.isFluidEqual(stack)) {
				FluidStack remainder = simulate ? stack.copy() : stack;
				remainder.amount -= Math.min(remainder.amount + this.fluidContent.amount, this.capacity);
				return remainder;
			} else {
				return stack;
			}
		}

		/**
		 * @param size Size of item to be extracted
		 * @param simulate true if this operation is analog only
		 * @return The extracted stack, EMPTY if cannot extract anything
		 */
		// TODO Should we switch to int?
		public ItemStack tryExtract(byte size, boolean simulate) {
			if (this.isOccupiedByItem()) {
				return simulate ? this.itemContent.copy().splitStack(size) : this.itemContent.splitStack(size);
			} else {
				return ItemStack.EMPTY;
			}
		}

		@Nullable
		@Override
		public FluidStack getFluid() {
			return this.getFluidContent();
		}

		@Override
		public int getFluidAmount() {
			return this.isOccupiedByFluid() ? this.fluidContent.amount : 0;
		}

		@Override
		public int getCapacity() {
			return this.capacity;
		}

		@Override
		public FluidTankInfo getInfo() {
			return new FluidTankInfo(this);
		}

		@Override
		public int fill(FluidStack resource, boolean doFill) {
			if (resource != null) {
				if (resource.isFluidEqual(this.fluidContent)) {
					int previousAmount = this.fluidContent.amount;
					int result = Math.min(resource.amount + this.fluidContent.amount, this.capacity);
					if (doFill) {
						this.fluidContent.amount = result;
					}
					return result == this.capacity ? result - previousAmount : resource.amount;
				} else if (this.fluidContent == null) {
					if (doFill) {
						this.fluidContent = resource;
					}
					return resource.amount;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}

		@Nullable
		@Override
		public FluidStack drain(int maxDrain, boolean doDrain) {
			if (this.fluidContent == null) {
				return null;
			} else {
				if (maxDrain <= 0) {
					return null;
				} else if (this.fluidContent.amount <= maxDrain) {
					if (doDrain) {
						this.fluidContent = null;
					}
					return fluidContent.copy();
				} else {
					FluidStack result = new FluidStack(this.getFluidContent().getFluid(), maxDrain);
					if (doDrain) {
						this.fluidContent.amount -= maxDrain;
					}
					return result;
				}
			}
		}
	}

	private static final class HybridTankProperties implements IFluidTankProperties {

		private final ItemFluidHybridSlot tank;

		private HybridTankProperties(ItemFluidHybridSlot tank) {
			this.tank = tank;
		}

		@Nullable
		@Override
		public FluidStack getContents() {
			return tank.getFluidContent();
		}

		@Override
		public int getCapacity() {
			return tank.getCapacity();
		}

		@Override
		public boolean canFill() {
			return tank.strategy.permitInput;
		}

		@Override
		public boolean canDrain() {
			return tank.strategy.permitOutput;
		}

		@Override
		public boolean canFillFluidType(FluidStack fluidStack) {
			return canFill() && fluidStack == null ? tank.fluidContent == null : fluidStack.isFluidEqual(tank.fluidContent);
		}

		@Override
		public boolean canDrainFluidType(FluidStack fluidStack) {
			return (!canDrain() || fluidStack != null) && fluidStack.isFluidEqual(tank.fluidContent);
		}
	}

	private final ItemFluidHybridSlot inv[];

	public ItemFluidHybridHandler(final int size) {
		this.inv = new ItemFluidHybridSlot[size];
		Arrays.fill(this.inv, new ItemFluidHybridSlot());
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return Arrays.stream(inv).map(HybridTankProperties::new).toArray(IFluidTankProperties[]::new);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		for (ItemFluidHybridSlot slot : inv) {
			FluidStack result = slot.tryInsert(resource, !doFill);
			if (result == null) {
				if (resource != null) {
					return resource.amount;
				}
			} else {
				return resource.amount - result.amount;
			}
		}
		return 0;
	}

	@Nullable
	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		for (ItemFluidHybridSlot slot : inv) {
			if (slot.strategy.permitOutput && slot.getFluidContent().isFluidEqual(resource)) {
				return slot.drain(resource.amount, doDrain);
			}
		}
		return null;
	}

	@Nullable
	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		for (ItemFluidHybridSlot slot : inv) {
			if (slot.strategy.permitOutput) {
				return slot.drain(maxDrain, doDrain);
			}
		}
		return null;
	}

	@Override
	public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
		if (slot < inv.length) {
			this.inv[slot].forceSetContent(stack);
		}
	}

	@Override
	public int getSlots() {
		return this.inv.length;
	}

	@Nonnull
	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot < inv.length ? inv[slot].getItemContent() : ItemStack.EMPTY;
	}

	@Nonnull
	@Override
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
		if (slot < inv.length) {
			return inv[slot].tryInsert(stack, simulate);
		} else {
			return stack;
		}
	}

	@Nonnull
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot < inv.length) {
			return inv[slot].tryExtract((byte)amount, simulate);
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public int getSlotLimit(int slot) {
		return slot < inv.length ? inv[slot].isOccupiedByItem() ? inv[slot].getItemContent().getMaxStackSize() : 0 : 0;
	}
}

/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
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

package frogcraftrebirth.common.lib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * A {@code MultiTypeStorage} is a holder of array of {@link Slot} objects, which
 * are wrapper of arbitrary content, like {@code ItemStack} or {@code FluidStack}.
 * <br/>
 * The main purpose of this class is to store different objects, such as both
 * {@code ItemStack} and {@code FluidStack}, in the same container, for sake of
 * easier management in complex situations (such as
 * {@link frogcraftrebirth.common.tile.TileAdvChemReactor Adv. Chem. Reactor})
 * where there is real need of holding more than two types of objects.
 */
public final class MultiTypeStorage {

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

	public interface Slot<T> {

		@Nullable
		T getView();

		Class<T> getType();

		boolean isEmpty();

		T insert(T toAdd, boolean simulate);

		T extract(int toTake, boolean simulate);

		default <R> R getContentOrDefault(Class<R> type, R fallback) {
			if (type == this.getType()) {
				return type.cast(this.getView());
			} else {
				return fallback;
			}
		}

		default <R> R insertOrReturnVerbatim(Class<R> type, R toAdd, boolean simulate) {
			if (type == this.getType()) {
				return type.cast(this.insert(this.getType().cast(toAdd), simulate));
			} else {
				return toAdd;
			}
		}

		default <R> R extractOrReturnEmpty(Class<R> type, int toTake, boolean simulate, R fallbackEmpty) {
			if (type == this.getType()) {
				return type.cast(this.extract(toTake, simulate));
			} else {
				return fallbackEmpty;
			}
		}

	}

	public static final class ItemSlot implements Slot<ItemStack> {

		private ItemStack content;

		ItemSlot() {
			this(ItemStack.EMPTY);
		}

		ItemSlot(@Nonnull ItemStack content) {
			this.content = content;
		}

		@Nonnull
		@Override
		public ItemStack getView() {
			return content;
		}

		@Override
		public Class<ItemStack> getType() {
			return ItemStack.class;
		}

		@Override
		public boolean isEmpty() {
			return content.isEmpty();
		}

		@Nonnull
		@Override
		public ItemStack insert(ItemStack toAdd, boolean simulate) {
			if (this.content.isEmpty()) {
				if (!simulate) {
					this.content = toAdd;
				}
				return ItemStack.EMPTY;
			} else {
				if (this.content.isItemEqual(toAdd)) {
					int finalSize = Math.min(this.content.getMaxStackSize(), this.content.getCount() + toAdd.getCount());
					if (finalSize < this.content.getMaxStackSize()) {
						if (!simulate) {
							this.content.setCount(finalSize);
						}
						return ItemStack.EMPTY;
					} else {
						int sizeToReturn = this.content.getCount() + toAdd.getCount() - finalSize;
						if (!simulate) {
							this.content.setCount(finalSize);
						}
						ItemStack remainder = toAdd.copy();
						remainder.setCount(sizeToReturn);
						return remainder;
					}
				} else {
					return toAdd;
				}
			}
		}

		@Nonnull
		@Override
		public ItemStack extract(int toTake, boolean simulate) {
			if (toTake < 1 || this.content.isEmpty()) {
				return ItemStack.EMPTY;
			} else {
				return simulate ? this.content.copy().splitStack(toTake) : this.content.splitStack(toTake);
			}
		}
	}

	public static final class FluidSlot implements Slot<FluidStack>, IFluidTank {

		public static final int DEFAULT_CAPACITY = 8000;

		private FluidStack content;
		private final int capacity;

		// Reasonable size of 8000. What could go wrong with it?!
		public FluidSlot() {
			this(DEFAULT_CAPACITY, null);
		}

		public FluidSlot(int capacity) {
			this(capacity, null);
		}

		public FluidSlot(FluidStack content) {
			this(DEFAULT_CAPACITY, content);
		}

		public FluidSlot(int capacity, FluidStack content) {
			this.capacity = capacity;
			this.content = content;
		}

		@Override
		public FluidStack getView() {
			return content;
		}

		@Override
		public Class<FluidStack> getType() {
			return FluidStack.class;
		}

		@Override
		public boolean isEmpty() {
			return content == null || content.amount <= 0;
		}

		@Override
		public FluidStack insert(FluidStack toAdd, boolean simulate) {
			int accepted = this.fill(toAdd, !simulate);
			if (accepted < toAdd.amount) {
				return new FluidStack(toAdd.getFluid(), toAdd.amount - accepted);
			} else {
				return null;
			}
		}

		@Override
		public FluidStack extract(int toTake, boolean simulate) {
			return this.drain(toTake, !simulate);
		}

		@Nullable
		@Override
		public FluidStack getFluid() {
			return content;
		}

		@Override
		public int getFluidAmount() {
			return content == null ? 0 : content.amount;
		}

		@Override
		public int getCapacity() {
			return this.capacity;
		}

		@Override
		public FluidTankInfo getInfo() {
			return new FluidTankInfo(this.content, this.capacity);
		}

		@Override
		public int fill(FluidStack resource, boolean doFill) {
			if (resource == null) {
				return 0;
			}
			if (this.content == null) {
				if (doFill) {
					this.content = resource;
				}
				return resource.amount;
			} else {
				int combined = Math.min(this.capacity, this.content.amount + resource.amount);
				int accepted;
				if (combined < capacity) {
					accepted = resource.amount;
				} else {
					accepted = this.capacity - this.content.amount;
				}
				if (doFill) {
					this.content.amount = combined;
				}
				return accepted;
			}
		}

		@Nullable
		@Override
		public FluidStack drain(int maxDrain, boolean doDrain) {
			if (this.content == null) {
				return null;
			} else {
				FluidStack result = this.content.copy();
				int drained = Math.min(this.content.amount, maxDrain);
				if (doDrain) {
					this.content.amount -= drained;
					if (this.content.amount < 1) {
						this.content = null;
					}
				}
				result.amount = drained;
				return result;
			}
		}
	}

	public static final class EmptySlot implements Slot<Void> {

		public static final EmptySlot INSTANCE = new EmptySlot();

		private EmptySlot() {}

		@Override
		public Void getView() {
			return null;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public Class<Void> getType() {
			return Void.TYPE;
		}

		@Override
		public Void insert(Void toAdd, boolean simulate) {
			return toAdd;
		}

		@Override
		public Void extract(int toTake, boolean simulate) {
			return null;
		}

		@Override
		public <R> R getContentOrDefault(Class<R> type, R fallback) {
			return fallback;
		}

		@Override
		public <R> R insertOrReturnVerbatim(Class<R> type, R toAdd, boolean simulate) {
			return toAdd;
		}

		@Override
		public <R> R extractOrReturnEmpty(Class<R> type, int toTake, boolean simulate, R fallbackEmpty) {
			return fallbackEmpty;
		}
	}

	private final Slot<?> inv[];

	public MultiTypeStorage(int size) {
		this.inv = new Slot<?>[size];
		Arrays.fill(inv, EmptySlot.INSTANCE);
	}

	public int size() {
		return inv.length;
	}

	public Slot<?> indexOf(int index) {
		return inv[index];
	}

	// TODO For fluid, there is need of customizing capacity. We need a Supplier of some sort...
	void set(int index, Slot<?> newSlot) {
		this.inv[index] = newSlot;
	}

	@Nullable
	public <V> V createHandler(Class<V> type) {
		if (type == IItemHandler.class) {
			return type.cast(new ItemHandlerView());
		} else if (type == IFluidHandler.class) {
			return type.cast(new FluidHandlerView());
		} else {
			return null;
		}
	}

	final class ItemHandlerView implements IItemHandler {

		@Override
		public int getSlots() {
			return MultiTypeStorage.this.size();
		}

		@Nonnull
		@Override
		public ItemStack getStackInSlot(int slot) {
			return MultiTypeStorage.this.indexOf(slot).getContentOrDefault(ItemStack.class, ItemStack.EMPTY);
		}

		@Nonnull
		@Override
		public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
			Slot<?> toQuery = MultiTypeStorage.this.indexOf(slot);
			if (toQuery.isEmpty()) {
				if (!simulate) {
					MultiTypeStorage.this.set(slot, new ItemSlot(stack));
				}
				return ItemStack.EMPTY;
			} else {
				return toQuery.insertOrReturnVerbatim(ItemStack.class, stack, simulate);
			}
		}

		@Nonnull
		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			Slot<?> toQuery = MultiTypeStorage.this.indexOf(slot);
			if (toQuery.isEmpty()) {
				return ItemStack.EMPTY;
			} else {
				return toQuery.extractOrReturnEmpty(ItemStack.class, amount, simulate, ItemStack.EMPTY);
			}
		}

		@Override
		public int getSlotLimit(int slot) {
			return MultiTypeStorage.this.indexOf(slot).getContentOrDefault(ItemStack.class, ItemStack.EMPTY).getMaxStackSize();
		}
	}

	final class FluidHandlerView implements IFluidHandler {

		@Override
		public IFluidTankProperties[] getTankProperties() {
			FluidTankInfo infoArray[] = new FluidTankInfo[MultiTypeStorage.this.size()];
			for (int index = 0; index < MultiTypeStorage.this.size(); index++) {
				Slot<?> toQuery = MultiTypeStorage.this.indexOf(index);
				if (toQuery instanceof IFluidTank) {
					infoArray[index] = ((IFluidTank) toQuery).getInfo();
				} else {
					infoArray[index] = new FluidTankInfo(null, 0);
				}
			}
			return FluidTankProperties.convert(infoArray);
		}

		@Override
		public int fill(FluidStack resource, boolean doFill) {
			if (resource == null) {
				return 0;
			}
			int accepted = 0;
			FluidStack toFill = resource.copy();
			for (int index = 0; index < MultiTypeStorage.this.size(); index++) {
				if (accepted >= resource.amount) {
					break;
				}
				Slot<?> toQuery = MultiTypeStorage.this.indexOf(index);
				if (toQuery.isEmpty()) {
					FluidStack moveToNewSlot = toFill.copy();
					moveToNewSlot.amount = Math.min(FluidSlot.DEFAULT_CAPACITY, toFill.amount);
					if (doFill) {
						MultiTypeStorage.this.set(index, new FluidSlot(moveToNewSlot));
					}
					toFill.amount -= moveToNewSlot.amount;
					accepted += moveToNewSlot.amount;
				} else if (toQuery.getType() == FluidStack.class) {
					toFill = toQuery.insertOrReturnVerbatim(FluidStack.class, toFill, !doFill);
					if (toFill == null) {
						accepted = resource.amount;
						break;
					} else {
						accepted = resource.amount - toFill.amount;
					}
				}
			}
			accepted = Math.min(resource.amount, accepted);
			return accepted;
		}

		@Nullable
		@Override
		public FluidStack drain(FluidStack resource, boolean doDrain) {
			if (resource == null) {
				return null;
			}
			for (int index = 0; index < MultiTypeStorage.this.size(); index++) {
				Slot<?> toQuery = MultiTypeStorage.this.indexOf(index);
				if (toQuery.getType() == FluidStack.class && !toQuery.isEmpty() && resource.isFluidEqual(toQuery.getContentOrDefault(FluidStack.class, null))) {
					return toQuery.extractOrReturnEmpty(FluidStack.class, resource.amount, !doDrain, null);
				}
			}
			return null;
		}

		@Nullable
		@Override
		public FluidStack drain(int maxDrain, boolean doDrain) {
			for (int index = 0; index < MultiTypeStorage.this.size(); index++) {
				Slot<?> toQuery = MultiTypeStorage.this.indexOf(index);
				if (toQuery.getType() == FluidStack.class && !toQuery.isEmpty()) {
					return toQuery.extractOrReturnEmpty(FluidStack.class, maxDrain, !doDrain, null);
				}
			}
			return null;
		}
	}

}

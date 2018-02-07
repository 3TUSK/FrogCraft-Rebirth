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

package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class IterableFrogRecipeInputsBackedByIItemHandler implements Iterable<IFrogRecipeInput> {

	private final IItemHandler handlerWrapped;

	public IterableFrogRecipeInputsBackedByIItemHandler(IItemHandler handler) {
		this.handlerWrapped = handler;
	}

	@Nonnull
	@Override
	public Iterator<IFrogRecipeInput> iterator() {
		return new Iterator<IFrogRecipeInput>() {
			private final int count = handlerWrapped.getSlots();
			private int ptr = 0;
			@Override
			public boolean hasNext() {
				return ptr < count;
			}

			@Override
			public IFrogRecipeInput next() {
				if (ptr < count) {
					return new FrogRecipeInputItemStack(handlerWrapped.getStackInSlot(ptr++));
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

	@Override
	public void forEach(Consumer<? super IFrogRecipeInput> action) {
		for (IFrogRecipeInput iFrogRecipeInput : this) {
			action.accept(iFrogRecipeInput);
		}
	}

	@Override
	public Spliterator<IFrogRecipeInput> spliterator() {
		return new Spliterator<IFrogRecipeInput>() {
			private int ptr = 0;
			@Override
			public boolean tryAdvance(Consumer<? super IFrogRecipeInput> action) {
				if (action == null)
					throw new NullPointerException("Consumer cannot be null");
				if (ptr < handlerWrapped.getSlots()) {
					action.accept(new FrogRecipeInputItemStack(handlerWrapped.getStackInSlot(ptr++)));
					return true;
				} else {
					return false;
				}
			}

			/**
			 * {@inheritDoc}
			 * @implSpec
			 * This implementation will always return null due to the fact that
			 * instance of ItemHandler cannot be partitioned by all means.
			 * @return Always returns null
			 */
			@Override
			public Spliterator<IFrogRecipeInput> trySplit() {
				return null;
			}

			@Override
			public long estimateSize() {
				return handlerWrapped.getSlots() - ptr;
			}

			/**
			 * {@inheritDoc}
			 * @implSpec
			 * This implemention, by default, has characteristics of
			 * {@link #ORDERED}, {@link #SIZED}, and {@link #NONNULL}.
			 * @return
			 */
			@Override
			public int characteristics() {
				return ORDERED & SIZED & NONNULL;
			}
		};
	}
}

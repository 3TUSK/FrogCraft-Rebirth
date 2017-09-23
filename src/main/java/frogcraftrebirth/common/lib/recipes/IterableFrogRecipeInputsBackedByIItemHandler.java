package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraftforge.items.IItemHandler;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class IterableFrogRecipeInputsBackedByIItemHandler implements Iterable<IFrogRecipeInput> {

	private final IItemHandler handlerWrapped;

	public IterableFrogRecipeInputsBackedByIItemHandler(IItemHandler handler) {
		this.handlerWrapped = handler;
	}

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
		Iterator<IFrogRecipeInput> itr = iterator();
		while (itr.hasNext()) {
			action.accept(itr.next());
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

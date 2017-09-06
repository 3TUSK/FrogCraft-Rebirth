package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class FrogRecipeInputItemStack implements IFrogRecipeInput {

	private final ItemStack stack;

	public FrogRecipeInputItemStack(ItemStack stack) {
		this.stack = stack;
	}

	@Override
	public boolean matches(Object actualInput) {
		return actualInput instanceof ItemStack && stack.isItemEqual((ItemStack)actualInput);
	}

	@Nonnull
	@Override
	public <T> List<T> getActualInputs(Class<T> type) {
		return type == ItemStack.class ? Collections.singletonList(type.cast(stack)) : Collections.EMPTY_LIST;
	}

	@Override
	public int getSize() {
		return stack.getCount();
	}

	@Nullable
	@Override
	public <I> I accepts(Class<I> type, I actualInput) {
		if (type == ItemStack.class) {
			ItemStack casted = (ItemStack)actualInput;
			if (casted.isEmpty()) {
				return type.cast(ItemStack.EMPTY);
			} else {
				casted.shrink(stack.getCount());
				return actualInput;
			}
		}
		return actualInput; //If type mismatch, don't touch it.
	}

	@Override
	public boolean equals(Object o) {
		return o == this || o instanceof FrogRecipeInputItemStack && ((FrogRecipeInputItemStack)o).stack.equals(this.stack);
	}
}

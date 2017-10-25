package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class FrogRecipeInputEmpty implements IFrogRecipeInput {

	// New instance is not permitted.
	FrogRecipeInputEmpty() {}

	@Override
	public boolean matches(IFrogRecipeInput input) {
		return input == FrogRecipeInputs.EMPTY;
	}

	@Override
	public boolean matches(Object actualInput) {
		return actualInput == null || actualInput == ItemStack.EMPTY;
	}

	@Nonnull
	@Override
	public <T> List<T> getActualInputs(Class<T> type) {
		return Collections.emptyList();
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Nullable
	@Override
	public <I> I accepts(Class<I> type, I actualInput) {
		return actualInput;
	}
}

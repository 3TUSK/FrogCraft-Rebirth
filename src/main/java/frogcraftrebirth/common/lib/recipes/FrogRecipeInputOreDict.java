package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class FrogRecipeInputOreDict implements IFrogRecipeInput {

	private final String entry;
	private final int amount;

	public FrogRecipeInputOreDict(String entry, int size) {
		this.entry = entry;
		this.amount = size;
	}

	public boolean matches(IFrogRecipeInput input) {
		return OreDictionary.getOres(entry).stream().anyMatch(input::matches);
	}

	@Override
	public boolean matches(Object actualInput) {
		return actualInput instanceof ItemStack && OreDictionary.getOres(entry).stream().anyMatch(test -> test.isItemEqual((ItemStack)actualInput));
	}

	@Nonnull
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getActualInputs(Class<T> type) {
		return type == ItemStack.class ? (List<T>) OreDictionary.getOres(this.entry) : Collections.EMPTY_LIST;
	}

	@Override
	public int getSize() {
		return this.amount;
	}

	@Nullable
	@Override
	public <I> I accepts(Class<I> type, I actualInput) {
		if (type == ItemStack.class) {
			ItemStack casted = (ItemStack)actualInput;
			casted.shrink(amount);
			return actualInput;
		} else {
			return actualInput;
		}
	}

	@Override
	public boolean equals(Object o) {
		return this == o || o instanceof FrogRecipeInputOreDict && ((FrogRecipeInputOreDict)o).entry.equals(this.entry);
	}
}

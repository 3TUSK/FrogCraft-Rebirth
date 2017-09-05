package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public final class FrogRecipeInputs {

	private FrogRecipeInputs() {}

	public static final ItemStack UNI_CELL = IC2Items.getItem("fluid_cell");

	public static final Function<IFrogRecipeInput, List<ItemStack>> MAP_TO_ITEM = i -> i.getActualInputs(ItemStack.class);

	public static Collection<IFrogRecipeInput> wrap(ItemStack... stacks) {
		List<IFrogRecipeInput> list = new ArrayList<>(stacks.length);
		for (ItemStack stack : stacks) {
			if (UNI_CELL.isItemEqual(stack)) {
				try {
					list.add(new FrogRecipeInputUniversalFluidCell(stack));
				} catch (Exception e) {
					list.add(new FrogRecipeInputItemStack(stack));
				}
			} else {
				list.add(new FrogRecipeInputItemStack(stack));
			}
		}
		return list;
	}
}

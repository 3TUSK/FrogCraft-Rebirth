package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class FrogRecipeInputUniversalFluidCell implements IFrogRecipeInput {

	private final FluidStack stack;

	public FrogRecipeInputUniversalFluidCell(FluidStack stack) {
		this.stack = stack;
	}

	public FrogRecipeInputUniversalFluidCell(ItemStack stack) {
		if (FrogRecipeInputs.UNI_CELL.isItemEqual(stack)) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
			if (handler != null) {
				FluidStack f = handler.getTankProperties()[0].getContents();
				if (f != null) {
					this.stack = f.copy();
				} else { //TODO this seems too brutal
					throw new NullPointerException("Cannot pass empty IC2 Universal Fluid Cell here!");
				}
			} else {
				throw new NullPointerException("An IC2 Universal Fluid Cell is missing its fluid handler, possibly due to corrupted data");
			}
		} else {
			throw new IllegalArgumentException("Only IC2 Universal Fluid Cell can pass into this constructor");
		}
	}

	@Override
	public boolean matches(Object actualInput) {
		return actualInput instanceof ItemStack && FrogRecipeInputs.UNI_CELL.isItemEqual((ItemStack)actualInput) && stack.isFluidEqual((ItemStack)actualInput);
	}

	@Nonnull
	@Override
	public <T> List<T> getActualInputs(Class<T> type) {
		if (type == ItemStack.class) {
			ItemStack result = FrogRecipeInputs.UNI_CELL.copy();
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(result);
			if (handler != null) { // For safety sake
				handler.fill(stack.copy(), true);
				return Collections.singletonList(type.cast(result));
			}
		}

		return Collections.emptyList();
	}

	@Override
	public int getSize() {
		return stack.amount;
	}

	@Nullable
	@Override
	public <I> I accepts(Class<I> type, I actualInput) {
		if (type == ItemStack.class) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler((ItemStack)actualInput);
			if (handler != null) { // For safety sake
				handler.fill(stack.copy(), true);
				return actualInput;
			}
		}

		return actualInput;
	}
}

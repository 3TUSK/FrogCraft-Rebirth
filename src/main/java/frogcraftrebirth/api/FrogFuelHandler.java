package frogcraftrebirth.api;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.IFuelHandler;

/**
 * This FuelHanlder implementation will handle both vanilla fuel registration 
 * in a more generic way; it will also handle Combustion Furnace fuel and 
 * byproducts registration.
 * 
 * @see frogcraftrebirth.common.tile.TileCombustionFurnace
 * @see net.minecraft.tileentity.TileEntityFurnace#getItemBurnTime
 * @author 3TUSK
 */
public final class FrogFuelHandler implements IFuelHandler {

	/**
	 * Use 1000mB as default volume.
	 * @see FrogFuelHandler#regFuelByproduct(ItemStack fuel, Fluid byproduct)
	 */
	public static final int BUCKET_VOLUME = 1000;

	FrogFuelHandler() {
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		for (Entry<ItemStack, Integer> entry : fuelMap.entrySet()) {
			if (fuel.isItemEqual(entry.getKey()))
				return entry.getValue().intValue();
		}
		return 0;
	}

	@Nullable
	public FluidStack getFluidByproduct(@Nullable ItemStack aStack) {
		if (aStack == null)
			return null;
		for (Entry<ItemStack, FluidStack> entry : fuel2FluidMap.entrySet()) {
			if (aStack.isItemEqual(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}

	@Nullable
	public ItemStack getItemByproduct(@Nullable ItemStack aStack) {
		if (aStack == null)
			return null;
		for (Entry<ItemStack, ItemStack> entry : fuel2ByproductMap.entrySet()) {
			if (aStack.isItemEqual(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}

	public void regFuel(@Nonnull Item fuel, int timeInTicks) {
		regFuel(new ItemStack(fuel, 1), timeInTicks);
	}

	public void regFuel(@Nonnull ItemStack fuel, int timeInTicks) {
		fuelMap.put(fuel, timeInTicks < 0 ? 0 : timeInTicks);
	}

	public void regFuelByproduct(@Nonnull ItemStack fuel, @Nonnull Fluid byproduct) {
		regFuelByproduct(fuel, new FluidStack(byproduct, BUCKET_VOLUME));
	}

	public void regFuelByproduct(@Nonnull ItemStack fuel, @Nonnull FluidStack byproduct) {
		fuel2FluidMap.put(fuel, byproduct);
	}

	public void regFuelByproduct(@Nonnull ItemStack fuel, @Nonnull ItemStack byproduct) {
		fuel2ByproductMap.put(fuel, byproduct);
	}

	private final Map<ItemStack, Integer> fuelMap = new LinkedHashMap<ItemStack, Integer>();
	private final Map<ItemStack, FluidStack> fuel2FluidMap = new LinkedHashMap<ItemStack, FluidStack>();
	private final Map<ItemStack, ItemStack> fuel2ByproductMap = new LinkedHashMap<ItemStack, ItemStack>();
}

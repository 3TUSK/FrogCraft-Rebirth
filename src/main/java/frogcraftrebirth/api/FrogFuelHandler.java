package frogcraftrebirth.api;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * This FuelHanlder implementation will handle vanilla fuel registration 
 * in a more generic way; it will also handle Combustion Furnace fuel and 
 * byproducts registration.
 * 
 * @see frogcraftrebirth.common.tile.TileCombustionFurnace
 * @see net.minecraft.tileentity.TileEntityFurnace#getItemBurnTime
 * @author 3TUSK
 */
public final class FrogFuelHandler {

	/**
	 * Use 1000mB as default volume.
	 * @see FrogFuelHandler#regFuelByproduct(ItemStack fuel, Fluid byproduct)
	 */
	public static final int BUCKET_VOLUME = 1000;

	FrogFuelHandler() {
	}

	/**
	 * @deprecated Use TileEntityFurnace#getItemBurnTime
	 * @param fuel The item to be queried for burn time
	 * @return The burn time
	 */
	@Deprecated
	public int getBurnTime(@Nonnull ItemStack fuel) {
		return net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime(fuel);
	}

	@Nullable
	public FluidStack getFluidByproduct(@Nonnull ItemStack aStack) {
		if (aStack.isEmpty())
			return null;
		for (Entry<ItemStack, FluidStack> entry : fuel2FluidMap.entrySet()) {
			if (aStack.isItemEqual(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}
	
	@Nullable
	public FluidStack getFluidByproduct(@Nonnull String ore) {
		return ore2FluidMap.get(ore);
	}

	@Nonnull
	public ItemStack getItemByproduct(@Nonnull ItemStack aStack) {
		if (aStack.isEmpty())
			return ItemStack.EMPTY;
		for (Entry<ItemStack, ItemStack> entry : fuel2ByproductMap.entrySet()) {
			if (aStack.isItemEqual(entry.getKey()))
				return entry.getValue();
		}
		return ItemStack.EMPTY;
	}
	
	@Nonnull
	public ItemStack getItemByproduct(@Nonnull String ore) {
		return ore2ByproductMap.getOrDefault(ore, ItemStack.EMPTY);
	}

	@Deprecated
	public void regFuel(@Nonnull Item fuel, int timeInTicks) {
		regFuel(new ItemStack(fuel, 1), timeInTicks);
	}

	@Deprecated
	public void regFuel(@Nonnull ItemStack fuel, int timeInTicks) {
		//no-op
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
	
	public void regFuelByproduct(@Nonnull String ore, @Nonnull Fluid byproduct) {
		regFuelByproduct(ore, new FluidStack(byproduct, BUCKET_VOLUME));
	}
	
	public void regFuelByproduct(@Nonnull String ore, @Nonnull FluidStack byproduct) {
		ore2FluidMap.put(ore, byproduct);
	}
	
	public void regFuelByproduct(@Nonnull String ore, @Nonnull ItemStack byproduct) {
		ore2ByproductMap.put(ore, byproduct);
	}

	private final Map<ItemStack, FluidStack> fuel2FluidMap = new LinkedHashMap<>();
	private final Map<ItemStack, ItemStack> fuel2ByproductMap = new LinkedHashMap<>();
	private final Map<String, FluidStack> ore2FluidMap = new HashMap<>();
	private final Map<String, ItemStack> ore2ByproductMap = new HashMap<>();
}

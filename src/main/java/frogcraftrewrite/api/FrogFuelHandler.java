package frogcraftrewrite.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public final class FrogFuelHandler implements IFuelHandler{
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();

	private FrogFuelHandler() {}
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		for (ItemStack stack : fuelMap.keySet()) {
			if (stack.isItemEqual(fuel))
				return fuelMap.get(stack);
		}
		return 0;
	}
	
	public void reg(@Nonnull Item fuel, int timeInTicks) {
		reg(new ItemStack(fuel, 1), timeInTicks);
	}
	
	public void reg(@Nonnull ItemStack fuel, int timeInTicks){
		fuelMap.put(fuel, timeInTicks < 0 ? 0 : timeInTicks);
	}

	private Map<ItemStack, Integer> fuelMap = new HashMap<ItemStack, Integer>();
}

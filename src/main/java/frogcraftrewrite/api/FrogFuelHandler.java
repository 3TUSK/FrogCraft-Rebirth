package frogcraftrewrite.api;

import java.util.HashMap;

import javax.annotation.Nonnull;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public final class FrogFuelHandler implements IFuelHandler{

	private HashMap<ItemStack, Integer> fuelMap = new HashMap<ItemStack, Integer>();
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();
	
	//Unconstructable
	private FrogFuelHandler() {}
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		try {
			return getBurnTime(fuel);
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	public void reg(@Nonnull Item fuel, int timeInTicks) {
		reg(new ItemStack(fuel, 1), timeInTicks);
	}
	
	public void reg(@Nonnull ItemStack fuel, int timeInTicks){
		fuelMap.put(fuel, timeInTicks < 0 ? 0 : timeInTicks);
	}

}

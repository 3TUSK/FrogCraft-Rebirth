package frogcraftrewrite.api;

import java.util.HashMap;

import javax.annotation.Nonnull;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public final class FrogFuelHandler implements IFuelHandler{

	private HashMap<Item, Integer> fuelMap = new HashMap<Item, Integer>();
	
	public static final FrogFuelHandler FUEL_REG = new FrogFuelHandler();
	
	//Unconstructable
	private FrogFuelHandler() {}
	
	int getBurnTime(Item fuel) {
		try {
			return fuelMap.get(fuel);
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		return getBurnTime(fuel.getItem());
	}
	
	public void reg(ItemStack fuel, int timeInTicks) {
		reg(fuel.getItem(), timeInTicks);
	}
	
	public void reg(@Nonnull Item fuel, int timeInTicks){
		fuelMap.put(fuel, timeInTicks < 0 ? 0 : timeInTicks);
	}

}

package frogcraftrewrite.common.registry;

import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.FrogFuelHandler;
import frogcraftrewrite.api.recipes.ThermalCrackerRecipe;
import frogcraftrewrite.common.lib.FrogItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RegFrogRecipes {
	
	public static void init() {
		FrogAPI.managerTC.add(new ThermalCrackerRecipe(new ItemStack(Blocks.dirt), new ItemStack(Blocks.diamond_block), null, 1000));
	}
	
	public static void postInit() {
		//Register crafting recipe!
		FrogFuelHandler.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 6), 4800);
		FrogFuelHandler.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 7), 400);
	}

}

package frogcraftrewrite.common.registry;

import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.FrogFuelHandler;
import frogcraftrewrite.api.recipes.PyrolyzerRecipe;
import frogcraftrewrite.common.lib.FrogItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class RegFrogRecipes {
	
	public static void init() {
		FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.dirt), new ItemStack(Blocks.diamond_block), new FluidStack(FluidRegistry.LAVA, 1000), 100));
	}
	
	public static void postInit() {
		//Register crafting recipe!
		FrogFuelHandler.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 6), 4800);
		FrogFuelHandler.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 7), 400);
	}

}

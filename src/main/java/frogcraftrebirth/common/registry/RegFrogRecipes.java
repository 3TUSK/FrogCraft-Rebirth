package frogcraftrebirth.common.registry;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.PyrolyzerRecipe;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.FrogItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RegFrogRecipes {
	
	public static void init() {
		FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Items.coal), new ItemStack(FrogItems.itemIngot, 1, 7), new FluidStack(FrogFluids.coalTar, 50), 100));
		FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.cobblestone), new ItemStack(FrogItems.itemDust, 1, 2), new FluidStack(FrogFluids.carbonDioxide, 50), 100));
	}
	
	public static void postInit() {
		//Register crafting recipe!
		FrogAPI.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 6), 4800);
		FrogAPI.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 7), 400);
	}
	
	private static void defaultRecipe() {
		
	}
	
	private static void gregtechRecipe() {
		
	}
	
	private static void ic2classicRecipe() {
		
	}

}

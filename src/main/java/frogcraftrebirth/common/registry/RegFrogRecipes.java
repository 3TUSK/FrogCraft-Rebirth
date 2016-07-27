package frogcraftrebirth.common.registry;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import ic2.api.item.IC2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RegFrogRecipes {
	
	public static void init() {
		FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Items.COAL), new ItemStack(FrogItems.itemIngot, 1, 7), new FluidStack(FrogFluids.coalTar, 50), 100));
		FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(FrogItems.itemDust, 1, 2), new FluidStack(FrogFluids.carbonDioxide, 50), 100));
	
		defaultCraftingRecipe();
		initOreDict();
	}
	
	public static void postInit() {
		FrogAPI.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 3), 16000);
		FrogAPI.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 4), 1800);
		
	}
	
	private static void defaultCraftingRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FrogBlocks.mobilePowerStation), new Object[] {"ICI", "IBI", "IFI", 'I', "plateIron", 'C', "circuitAdvanced", 'B', IC2Items.getItem("te", "batbox"), 'F', IC2Items.getItem("te", "electric_furnace")}));
	}
	
	static void initOreDict() {
		OreDictionary.registerOre("railgun", FrogItems.ionCannon);
		OreDictionary.registerOre("jinkela", FrogItems.jinkela);
		
		OreDictionary.registerOre("crystalTiberium", new ItemStack(FrogItems.tiberium, 1, 0));
		OreDictionary.registerOre("crystalTiberium", new ItemStack(FrogItems.tiberium, 1, 1));
		OreDictionary.registerOre("crystalTiberium", new ItemStack(FrogItems.tiberium, 1, 2));
		OreDictionary.registerOre("crystalTiberiumRed", new ItemStack(FrogItems.tiberium, 1, 0));
		OreDictionary.registerOre("crystalTiberiumBlue", new ItemStack(FrogItems.tiberium, 1, 1));
		OreDictionary.registerOre("crystalTiberiumGreen", new ItemStack(FrogItems.tiberium, 1, 2));
		
		OreDictionary.registerOre("oreCarnallite", new ItemStack(FrogBlocks.frogOres, 1, 0));
		OreDictionary.registerOre("oreDewalquite", new ItemStack(FrogBlocks.frogOres, 1, 1));
		OreDictionary.registerOre("oreFluorapatite", new ItemStack(FrogBlocks.frogOres, 1, 2));
	}
}

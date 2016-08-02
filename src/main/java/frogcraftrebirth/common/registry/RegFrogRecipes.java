package frogcraftrebirth.common.registry;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.config.ConfigMain;
import ic2.api.item.IC2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RegFrogRecipes {
	
	public static void init() {
		defaultCraftingRecipe();
		initOreDict();
		//Note: because we don't have GregTech, several fluid is still missing. This will be fixed when I get those fluid back.
		FrogAPI.managerCT.add(new CondenseTowerRecipe(100, new FluidStack(FrogFluids.coalTar, 5), new FluidStack[] {new FluidStack(FrogFluids.benzene, 2), new FluidStack(FrogFluids.ammonia, 1), new FluidStack(FrogFluids.carbonOxide, 2)}));
		FrogAPI.managerCT.add(new CondenseTowerRecipe(100, new FluidStack(FluidRegistry.getFluid("ic2air"), 10), new FluidStack[] {new FluidStack(FrogFluids.argon, 1), new FluidStack(FrogFluids.oxygen, 7), new FluidStack(FrogFluids.carbonDioxide, 2)}));
		
		FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Items.COAL), new ItemStack(FrogItems.itemIngot, 1, 4), new FluidStack(FrogFluids.coalTar, 50), 100));
		FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(FrogItems.itemDust, 1, 2), new FluidStack(FrogFluids.carbonDioxide, 1000), 100));
	
		FrogAPI.FUEL_REG.regFuelByproduct(new ItemStack(Items.COAL, 1, 0), FrogFluids.carbonDioxide);
		FrogAPI.FUEL_REG.regFuelByproduct(new ItemStack(Items.COAL, 1, 1), FrogFluids.carbonDioxide);
		FrogAPI.FUEL_REG.regFuelByproduct("dustCoal", FrogFluids.carbonDioxide);
		FrogAPI.FUEL_REG.regFuelByproduct("dustCharcoal", FrogFluids.carbonDioxide);
		FrogAPI.FUEL_REG.regFuelByproduct("dustSulfur", FrogFluids.sulfurDioxide);
	}
	
	public static void postInit() {
		FrogAPI.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 3), 16000);
		FrogAPI.FUEL_REG.regFuel(new ItemStack(FrogItems.itemIngot, 1, 4), 1800);
		
		FrogAPI.FUEL_REG.regFuel(IC2Items.getItem("dust", "sulfur"), ConfigMain.enableClassicMode ? 1600 : 1200);
	}
	
	private static void defaultCraftingRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FrogBlocks.mobilePowerStation), new Object[] {"ICI", "IBI", "IFI", 'I', "plateIron", 'C', "circuitAdvanced", 'B', IC2Items.getItem("te", "batbox"), 'F', IC2Items.getItem("te", "electric_furnace")}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FrogBlocks.condenseTowerPart, 1, 0), new Object[] {"MAM", "CPC", "CYC", 'M', IC2Items.getItem("mining_pipe", "pipe"), 'A', "circuitAdvanced", 'C', IC2Items.getItem("fluid_cell"), 'P', IC2Items.getItem("te", "pump"), 'Y', new ItemStack(FrogBlocks.condenseTowerPart, 1, 1)}));
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

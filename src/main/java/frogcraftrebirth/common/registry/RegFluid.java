package frogcraftrebirth.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.block.BlockNitricAcid;
import ic2.api.item.IC2Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class RegFluid {
	
	public static void init() {
		//TODO Add missing fluid info. Basically according to wikipedia.
		FrogFluids.ammonia = new Fluid("ammonia")
				.setDensity(694).setRarity(EnumRarity.epic).setGaseous(true);
		FrogFluids.argon = new Fluid("argon")
				.setDensity(1784).setRarity(EnumRarity.rare).setGaseous(true);
		FrogFluids.benzene = new Fluid("benzene")
				.setDensity(877).setRarity(EnumRarity.epic).setGaseous(true);
		FrogFluids.bromine = new Fluid("bromine")
				.setDensity(3103).setRarity(EnumRarity.uncommon);
		FrogFluids.carbonOxide = new Fluid("carbonOxide")
				.setDensity(1250).setRarity(EnumRarity.uncommon).setGaseous(true);
		FrogFluids.carbonDioxide = new Fluid("carbonDioxide")
				.setDensity(1980).setRarity(EnumRarity.common).setGaseous(true);
		FrogFluids.coalTar = new Fluid("coalTar")
				.setRarity(EnumRarity.rare).setViscosity(2000);
		FrogFluids.fluorine = new Fluid("flourine")
				.setDensity(1696).setRarity(EnumRarity.epic).setGaseous(true);
		FrogFluids.nitricAcid = new Fluid("nitricAcid")
				.setDensity(1420).setRarity(EnumRarity.rare);
		FrogFluids.liquidAir = new Fluid("liquifiedAir")
				.setRarity(EnumRarity.epic);
		FrogFluids.nitrogenOxide = new Fluid("nitrogenOxide")
				.setDensity(1340).setRarity(EnumRarity.rare).setGaseous(true);
		FrogFluids.oxygen = new Fluid("oxygen")
				.setDensity(1429).setRarity(EnumRarity.common).setGaseous(true);
		FrogFluids.sulfurDioxide = new Fluid("sulfurDioxide")
				.setRarity(EnumRarity.uncommon).setGaseous(true);
		FrogFluids.sulfurTrioxide = new Fluid("sulfurTrioxide")
				.setRarity(EnumRarity.rare).setGaseous(true);
		
		regFluid(FrogFluids.ammonia);
		regFluid(FrogFluids.argon);
		regFluid(FrogFluids.benzene);
		regFluid(FrogFluids.bromine);
		regFluid(FrogFluids.carbonOxide);
		regFluid(FrogFluids.carbonDioxide);
		regFluid(FrogFluids.coalTar);
		regFluid(FrogFluids.fluorine);
		regFluid(FrogFluids.nitricAcid);
		regFluid(FrogFluids.liquidAir);
		regFluid(FrogFluids.nitrogenOxide);
		regFluid(FrogFluids.oxygen);
		regFluid(FrogFluids.sulfurDioxide);
		regFluid(FrogFluids.sulfurTrioxide);
		
		FluidContainerRegistry.registerFluidContainer(FrogFluids.ammonia, new ItemStack(FrogItems.itemCell, 1, 0), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.argon, new ItemStack(FrogItems.itemCell, 1, 1), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.benzene, new ItemStack(FrogItems.itemCell, 1, 2), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.bromine, new ItemStack(FrogItems.itemCell, 1, 3), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.carbonOxide, new ItemStack(FrogItems.itemCell, 1, 4), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.carbonDioxide, new ItemStack(FrogItems.itemCell, 1, 5), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.coalTar, new ItemStack(FrogItems.itemCell, 1, 6), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.fluorine, new ItemStack(FrogItems.itemCell, 1, 7), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.nitricAcid, new ItemStack(FrogItems.itemCell, 1, 8), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.liquidAir, new ItemStack(FrogItems.itemCell, 1, 9), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.nitrogenOxide, new ItemStack(FrogItems.itemCell, 1, 11), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.oxygen, new ItemStack(FrogItems.itemCell, 1, 12), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.sulfurDioxide, new ItemStack(FrogItems.itemCell, 1, 13), IC2Items.getItem("cell"));
		FluidContainerRegistry.registerFluidContainer(FrogFluids.sulfurTrioxide, new ItemStack(FrogItems.itemCell, 1, 14), IC2Items.getItem("cell"));
	
		FrogBlocks.fluidNitricAcid = new BlockNitricAcid(FrogFluids.nitricAcid);
		GameRegistry.registerBlock(FrogBlocks.fluidNitricAcid, "nitricAcid");
		FrogFluids.nitricAcid.setBlock(FrogBlocks.fluidNitricAcid);
		
	}
	
	static void regFluid(Fluid fluid) {
		if (FluidRegistry.isFluidRegistered(fluid.getName()))
			fluid = FluidRegistry.getFluid(fluid.getName());
		
		FluidRegistry.registerFluid(fluid);
	}

}

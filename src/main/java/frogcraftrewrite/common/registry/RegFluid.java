package frogcraftrewrite.common.registry;

import frogcraftrewrite.api.FrogFluids;
import frogcraftrewrite.api.FrogItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class RegFluid {
	
	public static void init() {
		//TODO Add missing fluid info. Basically according to wikipedia.
		FrogFluids.ammonia = new Fluid("ammonia")
				.setLuminosity(10).setRarity(EnumRarity.epic).setGaseous(true);
		FrogFluids.argon = new Fluid("argon")
				.setLuminosity(1000).setRarity(EnumRarity.rare).setGaseous(true);
		FrogFluids.benzene = new Fluid("benzene")
				.setLuminosity(100).setRarity(EnumRarity.uncommon).setGaseous(true);
		FrogFluids.bromine = new Fluid("bromine")
				.setLuminosity(50).setRarity(EnumRarity.uncommon);
		FrogFluids.carbonDioxide = new Fluid("carbonDioxide")
				.setLuminosity(1000).setGaseous(true);
		FrogFluids.carbonOxide = new Fluid("carbonOxide")
				.setLuminosity(1000).setGaseous(true);
		
		regFluid(FrogFluids.ammonia);
		regFluid(FrogFluids.argon);
		
		FluidContainerRegistry.registerFluidContainer(FrogFluids.ammonia, new ItemStack(FrogItems.itemCell, 1, 0));
	}
	
	static void regFluid(Fluid fluid) {
		if (FluidRegistry.isFluidRegistered(fluid.getName()))
			fluid = FluidRegistry.getFluid(fluid.getName());
		
		FluidRegistry.registerFluid(fluid);
	}

}

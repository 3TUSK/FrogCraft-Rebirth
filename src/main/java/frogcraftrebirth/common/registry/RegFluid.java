package frogcraftrebirth.common.registry;

import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.block.BlockFluidFrog;
import frogcraftrebirth.common.block.BlockNitricAcid;
import frogcraftrebirth.common.lib.FrogFluid;
import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class RegFluid {
	
	public static void init() {
		FrogFluids.ammonia = new FrogFluid("ammonia", 694, 240, true, EnumRarity.EPIC);
		FrogFluids.argon = new FrogFluid("argon", 1784, 300, true, EnumRarity.RARE);
		FrogFluids.benzene = new FrogFluid("benzene", 877, 300, true, EnumRarity.EPIC);
		FrogFluids.bromine = new FrogFluid("bromine", 3103, 300, false, EnumRarity.UNCOMMON);
		FrogFluids.carbonOxide = new FrogFluid("carbon_oxide", 1250, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.carbonDioxide = new FrogFluid("carbon_dioxide", 1980, 300, true, EnumRarity.COMMON);
		FrogFluids.coalTar = new FrogFluid("coal_tar", 2000, 300, false, EnumRarity.RARE).setViscosity(2000);
		FrogFluids.fluorine = new FrogFluid("fluorine", 1696, 300, true, EnumRarity.EPIC);
		FrogFluids.nitricAcid = new FrogFluid("nitric_acid", "nitric_acid_flow", "nitric_acid",  1420, 300, false, EnumRarity.RARE);
		FrogFluids.nitrogenOxide = new FrogFluid("nitrogen_oxide", 1340, 300, true, EnumRarity.RARE);
		FrogFluids.oxygen = new FrogFluid("oxygen", 1429, 300, true, EnumRarity.COMMON);
		FrogFluids.sulfurDioxide = new FrogFluid("sulfur_dioxide", 1640, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.sulfurTrioxide = new FrogFluid("sulfur_trioxide", 1800, 300, true, EnumRarity.RARE);
		
		regFluid(FrogFluids.ammonia);
		regFluid(FrogFluids.argon);
		regFluid(FrogFluids.benzene);
		regFluid(FrogFluids.bromine);
		regFluid(FrogFluids.carbonOxide);
		regFluid(FrogFluids.carbonDioxide);
		regFluid(FrogFluids.coalTar, true);
		regFluid(FrogFluids.fluorine);
		regFluid(FrogFluids.nitricAcid, true);
		regFluid(FrogFluids.nitrogenOxide);
		regFluid(FrogFluids.oxygen);
		regFluid(FrogFluids.sulfurDioxide);
		regFluid(FrogFluids.sulfurTrioxide);
		
		new BlockNitricAcid(FrogFluids.nitricAcid);
		new BlockFluidFrog(FrogFluids.ammonia, "fluid.ammonia");
		new BlockFluidFrog(FrogFluids.argon, "fluid.argon");
		new BlockFluidFrog(FrogFluids.benzene, "fluid.benzene");
		new BlockFluidFrog(FrogFluids.bromine, "fluid.bromine");
		new BlockFluidFrog(FrogFluids.carbonDioxide, "fluid.carbonDioxide");
		new BlockFluidFrog(FrogFluids.carbonOxide, "fluid.carbonOxide");
		new BlockFluidFrog(FrogFluids.coalTar, "fluid.coalTar");
		new BlockFluidFrog(FrogFluids.fluorine, "fluid.Fluorine");
		new BlockFluidFrog(FrogFluids.nitrogenOxide, "fluid.nitrogenOxide");
		new BlockFluidFrog(FrogFluids.oxygen, "fluid.Oxygen");
		new BlockFluidFrog(FrogFluids.sulfurDioxide, "fluid.sulfurDioxide");
		new BlockFluidFrog(FrogFluids.sulfurTrioxide, "fluid.sulfurTrioxide");
	}
	
	private static void regFluid(Fluid fluid) {
		regFluid(fluid, false);
	}
	
	private static void regFluid(Fluid fluid, boolean regBucket) {
		if (!FluidRegistry.registerFluid(fluid))
			fluid = FluidRegistry.getFluid(fluid.getName());
		if (regBucket)
			FluidRegistry.addBucketForFluid(fluid);
	}

}

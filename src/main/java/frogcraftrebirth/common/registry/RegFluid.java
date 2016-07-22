package frogcraftrebirth.common.registry;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.block.BlockNitricAcid;
//import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegFluid {
	
	public static void init() {
		FrogFluids.ammonia = new Fluid("ammonia", getTexture("ammonia", false), getTexture("ammonia", true))
				.setDensity(694).setRarity(EnumRarity.EPIC).setGaseous(true);
		FrogFluids.argon = new Fluid("argon", getTexture("argon", false), getTexture("argon", true))
				.setDensity(1784).setRarity(EnumRarity.RARE).setGaseous(true);
		FrogFluids.benzene = new Fluid("benzene", getTexture("benzene", false), getTexture("benzene", true))
				.setDensity(877).setRarity(EnumRarity.EPIC).setGaseous(true);
		FrogFluids.bromine = new Fluid("bromine", getTexture("bromine", false), getTexture("bromine", true))
				.setDensity(3103).setRarity(EnumRarity.UNCOMMON);
		FrogFluids.carbonOxide = new Fluid("carbonOxide", getTexture("CO", false), getTexture("CO", true))
				.setDensity(1250).setRarity(EnumRarity.UNCOMMON).setGaseous(true);
		FrogFluids.carbonDioxide = new Fluid("carbonDioxide", getTexture("CO2", false), getTexture("CO2", true))
				.setDensity(1980).setRarity(EnumRarity.COMMON).setGaseous(true);
		FrogFluids.coalTar = new Fluid("coalTar", getTexture("coalTar", false), getTexture("coalTar", true))
				.setRarity(EnumRarity.RARE).setViscosity(2000);
		FrogFluids.fluorine = new Fluid("fluorine", getTexture("fluorine", false), getTexture("fluorine", true))
				.setDensity(1696).setRarity(EnumRarity.EPIC).setGaseous(true);
		FrogFluids.nitricAcid = new Fluid("nitricAcid", getTexture("HNO3", false), getTexture("HNO3", true))
				.setDensity(1420).setRarity(EnumRarity.RARE);
		FrogFluids.nitrogenOxide = new Fluid("nitrogenOxide", getTexture("NO", false), getTexture("NO", true))
				.setDensity(1340).setRarity(EnumRarity.RARE).setGaseous(true);
		FrogFluids.oxygen = new Fluid("oxygen", getTexture("oxygen", false), getTexture("oxygen", true))
				.setDensity(1429).setRarity(EnumRarity.COMMON).setGaseous(true);
		FrogFluids.sulfurDioxide = new Fluid("sulfurDioxide", getTexture("SO2", false), getTexture("SO2", true))
				.setRarity(EnumRarity.UNCOMMON).setGaseous(true);
		FrogFluids.sulfurTrioxide = new Fluid("sulfurTrioxide", getTexture("SO3", false), getTexture("SO3", true))
				.setRarity(EnumRarity.RARE).setGaseous(true);
		
		regFluid(FrogFluids.ammonia);
		regFluid(FrogFluids.argon);
		regFluid(FrogFluids.benzene);
		regFluid(FrogFluids.bromine);
		regFluid(FrogFluids.carbonOxide);
		regFluid(FrogFluids.carbonDioxide);
		regFluid(FrogFluids.coalTar);
		regFluid(FrogFluids.fluorine);
		regFluid(FrogFluids.nitricAcid);
		regFluid(FrogFluids.nitrogenOxide);
		regFluid(FrogFluids.oxygen);
		regFluid(FrogFluids.sulfurDioxide);
		regFluid(FrogFluids.sulfurTrioxide);
		
		FrogBlocks.fluidNitricAcid = new BlockNitricAcid(FrogFluids.nitricAcid).setRegistryName("nitric_acid");
		GameRegistry.<Block>register(FrogBlocks.fluidNitricAcid);
		FrogFluids.nitricAcid.setBlock(FrogBlocks.fluidNitricAcid);
		//ItemFrogBlock.initItemBlock();
	}
	
	private static ResourceLocation getTexture(String name, boolean flow) {
		return new ResourceLocation("frogcraftrebirth", "blocks/fluids/" + name);
	}
	
	private static void regFluid(Fluid fluid) {
		if (!FluidRegistry.registerFluid(fluid))
			fluid = FluidRegistry.getFluid(fluid.getName());
		FluidRegistry.addBucketForFluid(fluid);
	}

}

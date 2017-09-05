package frogcraftrebirth.common.registry;

import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.block.BlockNitricAcid;
import frogcraftrebirth.common.lib.FrogFluid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Function;

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
		FrogFluids.methane = new FrogFluid("methane", 656, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.nitricAcid = new FrogFluid("nitric_acid", "nitric_acid_flow", "nitric_acid",  1420, 300, false, EnumRarity.RARE);
		FrogFluids.nitrogen = new FrogFluid("nitrogen", 1251, 160, true, EnumRarity.COMMON);
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
		regFluid(FrogFluids.methane);
		regFluid(FrogFluids.nitricAcid, true, fluid -> new BlockNitricAcid(fluid).setRegistryName("nitric_acid"));
		regFluid(FrogFluids.nitrogen);
		regFluid(FrogFluids.nitrogenOxide);
		regFluid(FrogFluids.oxygen);
		regFluid(FrogFluids.sulfurDioxide);
		regFluid(FrogFluids.sulfurTrioxide);
	}
	
	private static void regFluid(Fluid fluid) {
		regFluid(fluid, false);
	}

	private static void regFluid(Fluid fluid, boolean regBucket) {
		regFluid(fluid, regBucket, null);
	}

	private static void regFluid(Fluid fluid, boolean regBucket, @Nullable Function<Fluid, Block> getBlock) {
		if (!FluidRegistry.registerFluid(fluid))
			fluid = FluidRegistry.getFluid(fluid.getName());
		if (regBucket)
			FluidRegistry.addBucketForFluid(fluid);
		Block block = getBlock == null ? new BlockFluidClassic(fluid, Material.WATER).setRegistryName(fluid.getName()) : getBlock.apply(fluid);
		ForgeRegistries.BLOCKS.register(block);
		fluid.setBlock(block);
	}

}

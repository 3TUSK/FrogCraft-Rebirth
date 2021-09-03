/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogGameObjects;
import frogcraftrebirth.common.block.*;
import frogcraftrebirth.common.item.*;
import frogcraftrebirth.common.lib.FrogFluid;
import frogcraftrebirth.common.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = FrogAPI.MODID)
public final class FrogRegistries {

	@SubscribeEvent
	public static void regBlock(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.registerAll(
				new BlockMineral(MapColor.DIRT, "shovel", 0).setHardness(5.0F).setResistance(15.0F).setTranslationKey("carnallite").setRegistryName("carnallite"),
				new BlockMineral(MapColor.STONE, "pickaxe", 2).setHardness(5.0F).setResistance(15.0F).setTranslationKey("dewalquite").setRegistryName("dewalquite"),
				new BlockMineral(MapColor.STONE, "pickaxe", 2).setHardness(5.0F).setResistance(15.0F).setTranslationKey("fluorapatite").setRegistryName("fluorapatite"),
				new BlockHSU(TileHSU.class).setTranslationKey("hsu").setRegistryName("hsu"),
				new BlockHSU(TileHSUUltra.class).setTranslationKey("uhsu").setRegistryName("uhsu"),
				new BlockMPS().setTranslationKey("mobile_power_station").setRegistryName("mobile_power_station"),
				new BlockMachinery(TileAirPump.class).setTranslationKey("air_pump").setRegistryName("air_pump"),
				new BlockMachinery(TileLiquefier.class).setTranslationKey("liquefier").setRegistryName("liquefier"),
				new BlockMachineryDirectional(TileAdvChemReactor.class).setTranslationKey("advanced_chemical_reactor").setRegistryName("advanced_chemical_reactor"),
				new BlockMachineryDirectional(TileAdvBlastFurnace.class).setTranslationKey("advanced_blast_furnace").setRegistryName("advanced_blast_furnace"),
				new BlockMachineryDirectional(TileCombustionFurnace.class).setTranslationKey("combustion_furnace").setRegistryName("combustion_furnace"),
				new BlockMachineryDirectional(TilePyrolyzer.class).setTranslationKey("pyrolyzer").setRegistryName("pyrolyzer"),
				new BlockMachineryDirectional(TileCondenseTower.class).setTranslationKey("condense_tower_core").setRegistryName("condense_tower_core"),
				new BlockMechanism(TileCondenseTowerStructure.class).setTranslationKey("condense_tower_cylinder").setRegistryName("condense_tower_cylinder"),
				new BlockMechanismDirectional(TileFluidOutputHatch.class).setTranslationKey("condense_tower_outlet").setRegistryName("condense_tower_outlet")
		);
		GameRegistry.registerTileEntity(TileMobilePowerStation.class, new ResourceLocation(FrogAPI.MODID, "mobile_power_station"));
		GameRegistry.registerTileEntity(TileHSU.class, new ResourceLocation(FrogAPI.MODID, "hybrid_storage_unit"));
		GameRegistry.registerTileEntity(TileHSUUltra.class, new ResourceLocation(FrogAPI.MODID, "ultra_hybrid_storage_unit"));
		GameRegistry.registerTileEntity(TileAirPump.class, new ResourceLocation(FrogAPI.MODID, "air_pump"));
		GameRegistry.registerTileEntity(TileCondenseTower.class, new ResourceLocation(FrogAPI.MODID, "condense_tower_core"));
		GameRegistry.registerTileEntity(TileCondenseTowerStructure.class, new ResourceLocation(FrogAPI.MODID, "condense_tower_cylinder"));
		GameRegistry.registerTileEntity(TileFluidOutputHatch.class, new ResourceLocation(FrogAPI.MODID, "condense_tower_outlet"));
		GameRegistry.registerTileEntity(TileCombustionFurnace.class, new ResourceLocation(FrogAPI.MODID, "combustion_furnace"));
		GameRegistry.registerTileEntity(TilePyrolyzer.class, new ResourceLocation(FrogAPI.MODID, "thermal_cracker"));
		GameRegistry.registerTileEntity(TileAdvChemReactor.class, new ResourceLocation(FrogAPI.MODID, "advanced_chemical_reactor"));
		GameRegistry.registerTileEntity(TileLiquefier.class, new ResourceLocation(FrogAPI.MODID, "liquefier"));
		GameRegistry.registerTileEntity(TileAdvBlastFurnace.class, new ResourceLocation(FrogAPI.MODID, "adv_blast_furnace"));

		FrogFluids.ammonia = new FrogFluid("ammonia", 694, 240, true, EnumRarity.EPIC);
		FrogFluids.argon = new FrogFluid("argon", 1784, 300, true, EnumRarity.RARE);
		FrogFluids.benzene = new FrogFluid("benzene", 877, 300, true, EnumRarity.EPIC);
		FrogFluids.bromine = new FrogFluid("bromine", 3103, 300, false, EnumRarity.UNCOMMON);
		FrogFluids.carbonOxide = new FrogFluid("carbon_oxide", 1250, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.carbonDioxide = new FrogFluid("carbon_dioxide", 1980, 300, true, EnumRarity.COMMON);
		FrogFluids.chlorine = new FrogFluid("chlorine", 320, 300, true, EnumRarity.RARE);
		FrogFluids.coalTar = new FrogFluid("coal_tar", 2000, 300, false, EnumRarity.RARE).setViscosity(2000);
		FrogFluids.fluorine = new FrogFluid("fluorine", 1696, 300, true, EnumRarity.EPIC);
		FrogFluids.glycerol = new FrogFluid("glycerol", 2000, 300, false, EnumRarity.UNCOMMON);
		FrogFluids.liquefiedAir = new FrogFluid("liquefied_air", 1293, 160, false, EnumRarity.EPIC);
		FrogFluids.methane = new FrogFluid("methane", 656, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.nitricAcid = new FrogFluid("nitric_acid", "nitric_acid_flow", "nitric_acid", 1420, 300, false, EnumRarity.RARE);
		FrogFluids.nitrogen = new FrogFluid("nitrogen", 1251, 160, true, EnumRarity.COMMON);
		FrogFluids.nitrogenOxide = new FrogFluid("nitrogen_oxide", 1340, 300, true, EnumRarity.RARE);
		FrogFluids.oleum = new FrogFluid("oleum", 1820, 300, false, EnumRarity.RARE);
		FrogFluids.potassiumHydroxide = new FrogFluid("potassium_hydroxide", 2120, 300, false, EnumRarity.RARE);
		FrogFluids.sulfuricAcid = new FrogFluid("sulfuric_acid", 1840, 300, false, EnumRarity.RARE);
		FrogFluids.sulfurDioxide = new FrogFluid("sulfur_dioxide", 1640, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.sulfurTrioxide = new FrogFluid("sulfur_trioxide", 1800, 300, true, EnumRarity.RARE);

		regFluids(
				FrogFluids.ammonia,
				FrogFluids.argon,
				FrogFluids.benzene,
				FrogFluids.bromine,
				FrogFluids.carbonOxide,
				FrogFluids.carbonDioxide,
				FrogFluids.chlorine,
				FrogFluids.fluorine,
				FrogFluids.glycerol,
				FrogFluids.liquefiedAir,
				FrogFluids.methane,
				FrogFluids.nitrogen,
				FrogFluids.nitrogenOxide,
				FrogFluids.oleum,
				FrogFluids.potassiumHydroxide,
				FrogFluids.sulfurDioxide,
				FrogFluids.sulfurTrioxide
		);
		regFluids(registry,
				FrogFluids.coalTar,
				FrogFluids.sulfuricAcid
		);
		FluidRegistry.registerFluid(FrogFluids.nitricAcid);
		Block nitricAcidBlock = new BlockNitricAcid(FrogFluids.nitricAcid).setRegistryName("nitric_acid");
		registry.register(nitricAcidBlock);
		FrogFluids.nitricAcid.setBlock(nitricAcidBlock);
		FluidRegistry.addBucketForFluid(FrogFluids.nitricAcid);
    }

	@SubscribeEvent
	public static void regItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new ItemFrogBlock(FrogGameObjects.CARNALLITE).setRegistryName("carnallite"),
				new ItemFrogBlock(FrogGameObjects.DEWALQUITE).setRegistryName("dewalquite"),
				new ItemFrogBlock(FrogGameObjects.FLUORAPATITE).setRegistryName("fluorapatite"),
				new ItemFrogBlock(FrogGameObjects.HSU).setRegistryName("hsu"),
				new ItemFrogBlock(FrogGameObjects.UHSU).setRegistryName("uhsu"),
				new ItemFrogBlock(FrogGameObjects.AIR_PUMP).setRegistryName("air_pump"),
				new ItemFrogBlock(FrogGameObjects.LIQUEFIER).setRegistryName("liquefier"),
				new ItemFrogBlock(FrogGameObjects.ADV_CHEM_REACTOR).setRegistryName("advanced_chemical_reactor"),
				new ItemFrogBlock(FrogGameObjects.ADV_BLAST_FURNACE).setRegistryName("advanced_blast_furnace"),
				new ItemFrogBlock(FrogGameObjects.COMBUSTION_FURNACE).setRegistryName("combustion_furnace"),
				new ItemFrogBlock(FrogGameObjects.PYROLYZER).setRegistryName("pyrolyzer"),
				new ItemFrogBlock(FrogGameObjects.CONDENSE_TOWER_CORE).setRegistryName("condense_tower_core"),
				new ItemFrogBlock(FrogGameObjects.CONDENSE_TOWER_CYLINDER).setRegistryName("condense_tower_cylinder"),
				new ItemFrogBlock(FrogGameObjects.CONDENSE_TOWER_OUTLET).setRegistryName("condense_tower_outlet"),
				new ItemMPS((BlockMPS) FrogGameObjects.MPS).setRegistryName("mobile_power_station"),
				new ItemAmmoniaCoolant("60k", 60000).setRegistryName("ammonia_coolant_60k"),
				new ItemAmmoniaCoolant("180k", 180000).setRegistryName("ammonia_coolant_180k"),
				new ItemAmmoniaCoolant("360k", 360000).setRegistryName("ammonia_coolant_360k"),
				new ItemDecayBattery().setTranslationKey("uranium_decay_battery").setRegistryName("uranium_decay_battery"),
				new ItemDecayBattery().setTranslationKey("thorium_decay_battery").setRegistryName("thorium_decay_battery"),
				new ItemDecayBattery().setTranslationKey("plutonium_decay_battery").setRegistryName("plutonium_decay_battery"),
				new ItemJinkela().setRegistryName("jinkela"),
				create("heating_module"),
				create("electrolysis_module"),
				create("ammonia_catalyst_module"),
				create("sulfur_trioxide_module"),
				create("aluminium_ingot"),
				create("magnalium_ingot"),
				create("titanium_ingot"),
				create("magnesium_ingot"),
				create("aluminium_dust"),
				create("magnalium_dust"),
				create("titanium_dust"),
				create("magnesium_dust"),
				create("tiny_aluminium_dust"),
				create("tiny_magnalium_dust"),
				create("tiny_titanium_dust"),
				create("tiny_magnesium_dust"),
				create("aluminium_plate"),
				create("magnalium_plate"),
				create("titanium_plate"),
				create("magnesium_plate"),
				create("dense_aluminium_plate"),
				create("dense_magnalium_plate"),
				create("dense_titanium_plate"),
				create("dense_magnesium_plate"),
				create("aluminium_casing"),
				create("magnalium_casing"),
				create("titanium_casing"),
				create("magnesium_casing"),
				create("crushed_carnallite_ore"),
				create("crushed_dewalquite_ore"),
				create("crushed_fluorapatite_ore"),
				create("purified_carnallite_ore"),
				create("purified_dewalquite_ore"),
				create("purified_fluorapatite_ore"),
				create("carnallite_dust"),
				create("dewalquite_dust"),
				create("fluorapatite_dust"),
				create("tiny_carnallite_dust"),
				create("tiny_dewalquite_dust"),
				create("tiny_fluorapatite_dust"),
				create("ammonium_nitrate_dust"),
				create("calcite_dust"),
				create("calcium_silicate_dust"),
				create("gypsum_dust"),
				create("quicklime_dust"),
				create("silica_dust"),
				create("slaked_lime_dust"),
				create("urea_dust"),
				create("tiny_ammonium_nitrate_dust"),
				create("tiny_calcite_dust"),
				create("tiny_calcium_silicate_dust"),
				create("tiny_gypsum_dust"),
				create("tiny_quicklime_dust"),
				create("tiny_silica_dust"),
				create("tiny_slaked_lime_dust"),
				create("tiny_urea_dust"),
				create("aluminium_oxide_dust"),
				create("calcium_fluoride_dust"),
				create("magnesium_bromide_dust"),
				create("potassium_chloride_dust"),
				create("sodium_chloride_dust"),
				create("titanium_oxide_dust"),
				create("vanadium_oxide_dust"),
				create("phosphorus"),
				create("soap"),
				new ItemFlammable(18000).setTranslationKey("frogcraftrebirth.briquette").setRegistryName("briquette"),
				new ItemFlammable(1600).setTranslationKey("frogcraftrebirth.shattered_coal_coke").setRegistryName("shattered_coal_coke"),
				new ItemFlammable(200).setTranslationKey("frogcraftrebirth.lipid").setRegistryName("lipid"),
				new ItemPotassium().setTranslationKey("frogcraftrebirth.potassium").setRegistryName("potassium")
		);
	}

	@SubscribeEvent
	public static void onMissingBlock(RegistryEvent.MissingMappings<Block> event) {
		event.getMappings().forEach(RegistryEvent.MissingMappings.Mapping::ignore);
	}

	@SubscribeEvent
	public static void onMissingItem(RegistryEvent.MissingMappings<Item> event) {
		event.getMappings().forEach(RegistryEvent.MissingMappings.Mapping::ignore);
	}

	private static Item create(String uid) {
		return new ItemFrog().setTranslationKey(FrogAPI.MODID + '.' + uid).setRegistryName(FrogAPI.MODID, uid);
	}

	private static void regFluids(Fluid... fluids) {
		for (Fluid f : fluids) {
			FluidRegistry.registerFluid(f);
		}
	}

	private static void regFluids(IForgeRegistry<Block> registry, Fluid... fluids) {
		for (Fluid fluid : fluids) {
			FluidRegistry.registerFluid(fluid);
			Block b = new BlockFluidClassic(fluid, Material.WATER).setRegistryName(fluid.getName());
			registry.register(b);
			fluid.setBlock(b);
			FluidRegistry.addBucketForFluid(fluid);
		}
	}

}

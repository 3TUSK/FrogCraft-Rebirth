/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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
import frogcraftrebirth.common.lib.util.ItemFactory;
import frogcraftrebirth.common.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = FrogAPI.MODID)
public final class FrogRegistries {

	@SubscribeEvent
	public static void regBlock(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.registerAll(
				new BlockMineral(MapColor.DIRT, "shovel", 0).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("frogcraftrebirth.carnallite").setRegistryName("carnallite"),
				new BlockMineral(MapColor.STONE, "pickaxe", 2).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("frogcraftrebirth.dewalquite").setRegistryName("dewalquite"),
				new BlockMineral(MapColor.STONE, "pickaxe", 2).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("frogcraftrebirth.fluorapatite").setRegistryName("fluorapatite"),
				new BlockHSU(TileHSU.class).setUnlocalizedName("frogcraftrebirth.hsu").setRegistryName("hsu"),
				new BlockHSU(TileHSUUltra.class).setUnlocalizedName("frogcraftrebirth.uhsu").setRegistryName("uhsu"),
				new BlockMPS().setUnlocalizedName("frogcraftrebirth.mobile_power_station").setRegistryName("mobile_power_station"),
				new BlockMachinery(TileAirPump.class).setRegistryName("frogcraftrebirth:air_pump"),
				new BlockMachinery(TileLiquefier.class).setRegistryName("frogcraftrebirth:liquefier"),
				new BlockMachineryDirectional(TileAdvChemReactor.class).setRegistryName("frogcraftrebirth:advanced_chemical_reactor"),
				new BlockMachineryDirectional(TileAdvBlastFurnace.class).setRegistryName("frogcraftrebirth:advanced_blast_furnace"),
				new BlockMachineryDirectional(TileCombustionFurnace.class).setRegistryName("frogcraftrebirth:combustion_furnace"),
				new BlockMachineryDirectional(TilePyrolyzer.class).setRegistryName("frogcraftrebirth:pyrolyzer"),
				new BlockMachineryDirectional(TileCondenseTower.class).setRegistryName("frogcraftrebirth:condense_tower_core"),
				new BlockMechanism(TileCondenseTowerStructure.class).setRegistryName("frogcraftrebirth:condense_tower_cylinder"),
				new BlockMechanismDirectional(TileFluidOutputHatch.class).setRegistryName("frogcraftrebirth:condense_tower_outlet")
		);
		GameRegistry.registerTileEntity(TileMobilePowerStation.class, "frogcraft_mobile_power_station");
		GameRegistry.registerTileEntity(TileHSU.class, "frogcraft_hybrid_storage_unit");
		GameRegistry.registerTileEntity(TileHSUUltra.class, "frogcraft_ultra_hybrid_storage_unit");
		GameRegistry.registerTileEntity(TileAirPump.class, "frogcraft_air_pump");
		GameRegistry.registerTileEntity(TileCondenseTower.class, "frogcraft_condense_tower_core");
		GameRegistry.registerTileEntity(TileCondenseTowerStructure.class, "frogcraft_condense_tower_cylinder");
		GameRegistry.registerTileEntity(TileFluidOutputHatch.class, "frogcraft_condense_tower_outlet");
		GameRegistry.registerTileEntity(TileCombustionFurnace.class, "frogcraft_combustion_furnace");
		GameRegistry.registerTileEntity(TilePyrolyzer.class, "frogcraft_thermal_cracker");
		GameRegistry.registerTileEntity(TileAdvChemReactor.class, "frogcraft_advanced_chemical_reactor");
		GameRegistry.registerTileEntity(TileLiquefier.class, "frogcraft_liquefier");
		GameRegistry.registerTileEntity(TileAdvBlastFurnace.class, "frogcraft_adv_blast_furnace");

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
		FrogFluids.methane = new FrogFluid("methane", 656, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.nitricAcid = new FrogFluid("nitric_acid", "nitric_acid_flow", "nitric_acid", 1420, 300, false, EnumRarity.RARE);
		FrogFluids.nitrogen = new FrogFluid("nitrogen", 1251, 160, true, EnumRarity.COMMON);
		FrogFluids.nitrogenOxide = new FrogFluid("nitrogen_oxide", 1340, 300, true, EnumRarity.RARE);
		FrogFluids.oleum = new FrogFluid("oleum", 1820, 300, false, EnumRarity.RARE);
		FrogFluids.sulfuricAcid = new FrogFluid("sulfuric_acid", 1840, 300, false, EnumRarity.RARE);
		FrogFluids.sulfurDioxide = new FrogFluid("sulfur_dioxide", 1640, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.sulfurTrioxide = new FrogFluid("sulfur_trioxide", 1800, 300, true, EnumRarity.RARE);

		regFluidWithoutBucket(registry,
				FrogFluids.ammonia,
				FrogFluids.argon,
				FrogFluids.benzene,
				FrogFluids.carbonOxide,
				FrogFluids.carbonDioxide,
				FrogFluids.chlorine,
				FrogFluids.fluorine,
				FrogFluids.methane,
				FrogFluids.nitrogen,
				FrogFluids.nitrogenOxide,
				FrogFluids.oleum,
				FrogFluids.sulfurDioxide,
				FrogFluids.sulfurTrioxide
		);
		regFluidWithBucket(registry,
				FrogFluids.bromine,
				FrogFluids.coalTar,
				FrogFluids.glycerol,
				FrogFluids.sulfuricAcid
		);
		regFluid(registry, FrogFluids.nitricAcid, true, fluid -> new BlockNitricAcid(fluid).setRegistryName("nitric_acid"));
	}

	@SubscribeEvent
	public static void regItem(RegistryEvent.Register<Item> event) {
		final ItemFactory factory = new ItemFactory();
		event.getRegistry().registerAll(
				new ItemBlock(FrogGameObjects.CARNALLITE).setRegistryName("carnallite"),
				new ItemBlock(FrogGameObjects.DEWALQUITE).setRegistryName("dewalquite"),
				new ItemBlock(FrogGameObjects.FLUORAPATITE).setRegistryName("fluorapatite"),
				new ItemBlock(FrogGameObjects.HSU).setRegistryName("hsu"),
				new ItemBlock(FrogGameObjects.UHSU).setRegistryName("uhsu"),
				new ItemBlock(FrogGameObjects.AIR_PUMP).setRegistryName("air_pump"),
				new ItemBlock(FrogGameObjects.LIQUEFIER).setRegistryName("liquefier"),
				new ItemBlock(FrogGameObjects.ADV_CHEM_REACTOR).setRegistryName("advanced_chemical_reactor"),
				new ItemBlock(FrogGameObjects.ADV_BLAST_FURNACE).setRegistryName("advanced_blast_furnace"),
				new ItemBlock(FrogGameObjects.COMBUSTION_FURNACE).setRegistryName("combustion_furnace"),
				new ItemBlock(FrogGameObjects.PYROLYZER).setRegistryName("pyrolyzer"),
				new ItemBlock(FrogGameObjects.CONDENSE_TOWER_CORE).setRegistryName("condense_tower_core"),
				new ItemBlock(FrogGameObjects.CONDENSE_TOWER_CYLINDER).setRegistryName("condense_tower_cylinder"),
				new ItemBlock(FrogGameObjects.CONDENSE_TOWER_OUTLET).setRegistryName("condense_tower_outlet"),
				new ItemMPS((BlockMPS) FrogGameObjects.MPS).setRegistryName(FrogGameObjects.MPS.getRegistryName()),
				new ItemAmmoniaCoolant("60K", 6000).setRegistryName("ammonia_coolant_60k"),
				new ItemAmmoniaCoolant("180K", 18000).setRegistryName("ammonia_coolant_180k"),
				new ItemAmmoniaCoolant("360K", 36000).setRegistryName("ammonia_coolant_360k"),
				new ItemDecayBattery("U").setRegistryName("uranium_decay_battery"),
				new ItemDecayBattery("Th").setRegistryName("thorium_decay_battery"),
				new ItemDecayBattery("Pu").setRegistryName("plutoium_decay_battery"),
				new ItemJinkela().setRegistryName("jinkela"),
				factory.create("heating_module"),
				factory.create("electrolysis_module"),
				factory.create("ammonia_catalyst_module"),
				factory.create("sulfur_trioxide_module"),
				factory.create("aluminium_ingot"),
				factory.create("magnalium_ingot"),
				factory.create("titanium_ingot"),
				factory.create("magnesium_ingot"),
				factory.create("aluminium_dust"),
				factory.create("magnalium_dust"),
				factory.create("titanium_dust"),
				factory.create("magnesium_dust"),
				factory.create("tiny_aluminium_dust"),
				factory.create("tiny_magnalium_dust"),
				factory.create("tiny_titanium_dust"),
				factory.create("tiny_magnesium_dust"),
				factory.create("aluminium_plate"),
				factory.create("magnalium_plate"),
				factory.create("titanium_plate"),
				factory.create("magnesium_plate"),
				factory.create("dense_aluminium_plate"),
				factory.create("dense_magnalium_plate"),
				factory.create("dense_titanium_plate"),
				factory.create("dense_magnesium_plate"),
				factory.create("aluminium_casing"),
				factory.create("magnalium_casing"),
				factory.create("titanium_casing"),
				factory.create("magnesium_casing"),
				factory.create("crushed_carnallite_ore"),
				factory.create("crushed_dewalquite_ore"),
				factory.create("crushed_fluorapatite_ore"),
				factory.create("purified_carnallite_ore"),
				factory.create("purified_dewalquite_ore"),
				factory.create("purified_fluorapatite_ore"),
				factory.create("carnallite_dust"),
				factory.create("dewalquite_dust"),
				factory.create("fluorapatite_dust"),
				factory.create("tiny_carnallite_dust"),
				factory.create("tiny_dewalquite_dust"),
				factory.create("tiny_fluorapatite_dust"),
				factory.create("ammonium_nitrate_dust"),
				factory.create("calcite_dust"),
				factory.create("calcium_silicate_dust"),
				factory.create("gypsum_dust"),
				factory.create("quicklime_dust"),
				factory.create("silica_dust"),
				factory.create("slaked_lime_dust"),
				factory.create("urea_dust"),
				factory.create("tiny_ammonium_nitrate_dust"),
				factory.create("tiny_calcite_dust"),
				factory.create("tiny_calcium_silicate_dust"),
				factory.create("tiny_gypsum_dust"),
				factory.create("tiny_quicklime_dust"),
				factory.create("tiny_silica_dust"),
				factory.create("tiny_slaked_lime_dust"),
				factory.create("tiny_urea_dust"),
				factory.create("aluminium_oxide_dust"),
				factory.create("calcium_fluoride_dust"),
				factory.create("magnesium_bromide_dust"),
				factory.create("potassium_chloride_dust"),
				factory.create("sodium_chloride_dust"),
				factory.create("titanium_oxide_dust"),
				factory.create("vanadium_oxide_dust"),
				factory.create("phosphorus"),
				new ItemFlammable(18000).setUnlocalizedName("frogcraftrebirth.briquette").setRegistryName("briquette"),
				new ItemFlammable(1600).setUnlocalizedName("frogcraftrebirth.shattered_coal_coke").setRegistryName("shattered_coal_coke"),
				new ItemFlammable(200).setUnlocalizedName("frogcraftrebirth.lipid").setRegistryName("lipid"),
				new ItemResource() {
					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem) {
						if (!entityItem.getEntityWorld().isRemote && !entityItem.getItem().isEmpty() && entityItem.getItem().getItem() == this) {
							if (entityItem.getEntityWorld().getBlockState(entityItem.getPosition()).getBlock() == FrogGameObjects.NITRIC_ACID) {
								//TODO set up explosion and grant advancement to players
								entityItem.setDead();
								return true;
							}
						}
						return false;
					}
				}.setUnlocalizedName("potassium").setRegistryName("potassium")
		);
		FrogRecipes.initOreDict();
	}

	@Deprecated
	private static void regFluidWithoutBucket(IForgeRegistry<Block> registry, Fluid... fluids) {
		for (Fluid fluid : fluids) {
			regFluid(registry, fluid, false, null);
		}
	}

	@Deprecated
	private static void regFluidWithBucket(IForgeRegistry<Block> registry, Fluid... fluids) {
		for (Fluid fluid : fluids) {
			regFluid(registry, fluid, true, null);
		}
	}

	@Deprecated
	private static void regFluid(IForgeRegistry<Block> registry, Fluid fluid, boolean regBucket, @Nullable Function<Fluid, Block> getBlock) {
		regFluid(registry, fluid, regBucket, true, null);
	}

	private static void regFluid(IForgeRegistry<Block> registry, Fluid fluid, boolean regBucket, boolean regBlock, @Nullable Function<Fluid, Block> getBlock) {
		FluidRegistry.registerFluid(fluid);
		if (regBlock && fluid.getBlock() == null) {
			Block block = getBlock == null ? new BlockFluidClassic(fluid, Material.WATER).setRegistryName(fluid.getName()) : getBlock.apply(fluid);
			registry.register(block);
			fluid.setBlock(block);
			if (regBucket) {
				FluidRegistry.addBucketForFluid(fluid);
			}
		}
	}

}

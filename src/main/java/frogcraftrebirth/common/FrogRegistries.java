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
import frogcraftrebirth.api.FrogConstants;
import frogcraftrebirth.api.FrogRegistees;
import frogcraftrebirth.common.block.*;
import frogcraftrebirth.common.item.*;
import frogcraftrebirth.common.lib.FrogFluid;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import frogcraftrebirth.common.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
        		new BlockHSU(),
				new BlockMPS(),
				new BlockCondenseTower(),
				new BlockGenerator(),
                new BlockMachine(),
				new BlockMachine2(),
				new BlockMineral(MapColor.DIRT, "shovel", 0).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("frogcraftrebirth.carnallite").setRegistryName("carnallite"),
				new BlockMineral(MapColor.STONE, "pickaxe", 2).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("frogcraftrebirth.dewalquite").setRegistryName("dewalquite"),
				new BlockMineral(MapColor.STONE, "pickaxe", 2).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("frogcraftrebirth.fluorapatite").setRegistryName("fluorapatite")
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
		FrogFluids.nitricAcid = new FrogFluid("nitric_acid", "nitric_acid_flow", "nitric_acid",  1420, 300, false, EnumRarity.RARE);
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
        event.getRegistry().registerAll(
                new ItemFrogBlock(FrogRegistees.CONDENSE_TOWER, aStack -> BlockCondenseTower.Part.values()[aStack.getMetadata() & 0b11].getName()).setRegistryName(FrogRegistees.CONDENSE_TOWER.getRegistryName()),
                new ItemBlock(Block.getBlockFromName("frogcraftrebirth:carnallite")),
				new ItemBlock(Block.getBlockFromName("frogcraftrebirth:dewalquite")),
				new ItemBlock(Block.getBlockFromName("frogcraftrebirth:fluorapatite")),
				new ItemFrogBlock(FrogRegistees.GENERATOR, aStack -> "combustionFurnace").setRegistryName(FrogRegistees.GENERATOR.getRegistryName()),
                new ItemFrogBlock(FrogRegistees.HSU, aStack -> BlockHSU.Level.values()[aStack.getMetadata() % 2].getName()).setRegistryName(FrogRegistees.HSU.getRegistryName()),
                new ItemFrogBlock(FrogRegistees.MACHINE, aStack -> BlockMachine.Type.values()[aStack.getMetadata() & 0b11].getName()).setRegistryName(FrogRegistees.MACHINE.getRegistryName()),
                new ItemFrogBlock(FrogRegistees.MACHINE2, sStack -> BlockMachine2.Type.values()[sStack.getMetadata() & 0b11].getName()).setRegistryName(FrogRegistees.MACHINE2.getRegistryName()),
                new ItemMPS((BlockMPS) FrogRegistees.MPS).setRegistryName(FrogRegistees.MPS.getRegistryName()),
                new ItemAmmoniaCoolant("60K", 6000).setRegistryName("ammonia_coolant_60k"),
                new ItemAmmoniaCoolant("180K", 18000).setRegistryName("ammonia_coolant_180k"),
                new ItemAmmoniaCoolant("360K", 36000).setRegistryName("ammonia_coolant_360k"),
                new ItemResources("reactionModule", "Heating", "Electrolyze", "Ammonia", "V2O5").setRegistryName("catalyst_module"),
                new ItemDecayBattery("U").setRegistryName("uranium_decay_battery"),
                new ItemDecayBattery("Th").setRegistryName("thorium_decay_battery"),
                new ItemDecayBattery("Pu").setRegistryName("plutoium_decay_battery"),
                new ItemJinkela().setRegistryName("jinkela"),
				new ItemResources("metal_ingot", FrogConstants.METALLIC_MATERIAL_TYPES).setRegistryName("metal_ingot"),
				new ItemResources("metal_dust", FrogConstants.METALLIC_MATERIAL_TYPES).setRegistryName("metal_dust"),
				new ItemResources("metal_dust_tiny", FrogConstants.METALLIC_MATERIAL_TYPES).setRegistryName("metal_dust_tiny"),
				new ItemResources("metal_plate", FrogConstants.METALLIC_MATERIAL_TYPES).setRegistryName("metal_plate"),
				new ItemResources("metal_plate_dense", FrogConstants.METALLIC_MATERIAL_TYPES).setRegistryName("metal_plate_dense"),
				new ItemResources("metal_casing", FrogConstants.METALLIC_MATERIAL_TYPES).setRegistryName("metal_casing"),
				new ItemResources("ore_crushed", FrogConstants.ORE_TYPES).setRegistryName("ore_crushed"),
				new ItemResources("ore_purified", FrogConstants.ORE_TYPES).setRegistryName("ore_purified"),
				new ItemResources("ore_dust", FrogConstants.ORE_TYPES).setRegistryName("ore_dust"),
				new ItemResources("ore_dust_tiny", FrogConstants.ORE_TYPES).setRegistryName("ore_dust_tiny"),
				new ItemResources("non_metal_dust", FrogConstants.NON_METAL_MATERIAL_TYPES).setRegistryName("non_metal_dust"),
				new ItemResources("non_metal_dust_tiny", FrogConstants.NON_METAL_MATERIAL_TYPES).setRegistryName("non_metal_dust_tiny"),
				new ItemResources("intermediate_product", FrogConstants.INTERMEDIATE_TYPES).setRegistryName("intermediate_product"),
				new ItemResources("inflammable", FrogConstants.INFLAMMABLE) {
					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem) {
						if (entityItem.getItem().isEmpty()) {
							return false;
						}
						if (!entityItem.getEntityWorld().isRemote && entityItem.getItem().getMetadata() == 3) { // 3 == Potassium
							if (entityItem.getEntityWorld().getBlockState(entityItem.getPosition()).getBlock() == FrogRegistees.NITRIC_ACID) {
								//TODO set up explosion and grant advancement to players
								entityItem.setDead();
								return false;
							}
						}
						return true;
					}
					@Override
					public int getItemBurnTime(ItemStack stack) {
						switch (stack.getMetadata()) {
							case 0: return 18000; // Briquette
							case 1: return 200;   // Lipid Cluster
							case 4: return 1600;  // Shattered Coal Coke
							default: return 0;
						}
					}
				}.setRegistryName("inflammable")
        );

        FrogRecipes.initOreDict();
    }

    @SubscribeEvent
	public static void onMissingMappingBlock(RegistryEvent.MissingMappings<Block> missingMappings) {
		missingMappings.getMappings().forEach(RegistryEvent.MissingMappings.Mapping::ignore);
	}

	@SubscribeEvent
	public static void onMissingMappingItem(RegistryEvent.MissingMappings<Item> missingMappings) {
		missingMappings.getMappings().forEach(RegistryEvent.MissingMappings.Mapping::ignore);
	}

	@SubscribeEvent
	public static void onMissingMappingPotion(RegistryEvent.MissingMappings<Potion> missingMappings) {
		missingMappings.getMappings().forEach(RegistryEvent.MissingMappings.Mapping::ignore);
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

package frogcraftrebirth.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(FrogAPI.MODID)
public final class FrogRegistees {
	@GameRegistry.ObjectHolder("hybrid_storage_unit")
	public static final Block HSU = null;
	@GameRegistry.ObjectHolder("mobile_power_station")
	public static final Block MPS = null;
	@GameRegistry.ObjectHolder("condense_tower")
	public static final Block CONDENSE_TOWER = null;
	@GameRegistry.ObjectHolder("generator")
	public static final Block GENERATOR = null;
	@GameRegistry.ObjectHolder("machine")
	public static final Block MACHINE = null;
	@GameRegistry.ObjectHolder("machine_2")
	public static final Block MACHINE2 = null;
	@GameRegistry.ObjectHolder("ore")
	public static final Block ORE = null;
	/**
	 * Scheduled to be removed permanently with no replacement.
	 * Use TiberiumCraft if replacement is needed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("tiberium_crystal")
	public static final Block TIBERIUM = null;
	@GameRegistry.ObjectHolder("nitric_acid")
	public static final Block NITRIC_ACID = null;

	@GameRegistry.ObjectHolder("ammonia_coolant_60k")
	public static final Item AMMONIA_COOLANT_60K = null;
	@GameRegistry.ObjectHolder("ammonia_coolant_180k")
	public static final Item AMMONIA_COOLANT_180K = null;
	@GameRegistry.ObjectHolder("ammonia_coolant_360k")
	public static final Item AMMONIA_COOLANT_360K = null;

	@GameRegistry.ObjectHolder("ore_crushed")
	public static final Item ORE_CRUSHED = null;
	@GameRegistry.ObjectHolder("ore_purified")
	public static final Item ORE_PURIFIED = null;
	@GameRegistry.ObjectHolder("ore_dust")
	public static final Item ORE_DUST = null;
	@GameRegistry.ObjectHolder("ore_dust_tiny")
	public static final Item ORE_DUST_TINY = null;
	@GameRegistry.ObjectHolder("metal_ingot")
	public static final Item METAL_INGOT = null;
	@GameRegistry.ObjectHolder("metal_plate")
	public static final Item METAL_PLATE = null;
	@GameRegistry.ObjectHolder("metal_plate_dense")
	public static final Item METAL_PLATE_DENSE = null;
	@GameRegistry.ObjectHolder("metal_dust")
	public static final Item METAL_DUST = null;
	@GameRegistry.ObjectHolder("metal_dust_tiny")
	public static final Item METAL_DUST_TNIY = null;
	@GameRegistry.ObjectHolder("metal_casing")
	public static final Item METAL_CASING = null;
	@GameRegistry.ObjectHolder("non_metal_dust")
	public static final Item NON_METAL_DUST = null;
	@GameRegistry.ObjectHolder("non_metal_dust_tiny")
	public static final Item NON_METAL_DUST_TINY = null;
	@GameRegistry.ObjectHolder("intermediate_product")
	public static final Item INTERMEDIATE = null;
	@GameRegistry.ObjectHolder("inflammable")
	public static final Item INFLAMMABLE = null;

	/**
	 * Scheduled to be removed with more than one replacement.
	 * Conversion recipes will be provided before removed in next bump in major version.
	 * After the major version bump, this field will be removed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("ingot")
	public static final Item INGOT = null;
	/**
	 * Scheduled to be removed with more than one replacement.
	 * Conversion recipes will be provided before removed in next bump in major version.
	 * After the major version bump, this field will be removed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("dust")
	public static final Item DUST = null;
	/**
	 * Scheduled to be removed with more than one replacement.
	 * Conversion recipes will be provided before removed in next bump in major version.
	 * After the major version bump, this field will be removed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("crushed")
	public static final Item CRUSHED_DUST = null;
	/**
	 * Scheduled to be removed with more than one replacement.
	 * Conversion recipes will be provided before removed in next bump in major version.
	 * After the major version bump, this field will be removed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("purified")
	public static final Item PURIFIED_DUST = null;
	/**
	 * Scheduled to be removed with more than one replacement.
	 * Conversion recipes will be provided before removed in next bump in major version.
	 * After the major version bump, this field will be removed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("small_pile_dust")
	public static final Item SMALL_PILE_DUST = null;
	@GameRegistry.ObjectHolder("catalyst_module")
	public static final Item REACTION_MODULE = null;
	/**
	 * Scheduled to be removed permanently with no replacement.
	 * Use TiberiumCraft if replacement is needed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("ion_cannon")
	public static final Item ION_CANNON = null;
	/**
	 * Scheduled to be removed permanently with no replacement.
	 * Use TiberiumCraft if replacement is needed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("ion_cannon_frame")
	public static final Item ION_CANNON_FRAME = null;
	@GameRegistry.ObjectHolder("uranium_decay_battery")
	public static final Item DECAY_BATTERY_URANIUM = null;
	@GameRegistry.ObjectHolder("thorium_decay_battery")
	public static final Item DECAY_BATTERY_THORIUM = null;
	@GameRegistry.ObjectHolder("plutoium_decay_battery")
	public static final Item DECAY_BATTERY_PLOTONIUM = null;
	@GameRegistry.ObjectHolder("jinkela")
	public static final Item JINKELA = null;
	/**
	 * Scheduled to be removed permanently with no replacement.
	 * Use TiberiumCraft if replacement is needed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("tiberium")
	public static final Item TIBERIUM_ITEM = null;
	@GameRegistry.ObjectHolder("fluid_armor")
	public static final Item FLUID_ARMOR = null;

	/**
	 * Scheduled to be removed permanently with no replacement.
	 * Use TiberiumCraft if replacement is needed.
	 */
	@Deprecated
	@GameRegistry.ObjectHolder("tiberium")
	public static final Potion POTION_TIBERIUM = null;
}

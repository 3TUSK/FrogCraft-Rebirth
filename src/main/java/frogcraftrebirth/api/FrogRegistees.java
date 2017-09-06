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
	@GameRegistry.ObjectHolder("ore")
	public static final Block ORE = null;
	@GameRegistry.ObjectHolder("nitric_acid")
	public static final Block NITRIC_ACID = null;

	@GameRegistry.ObjectHolder("ammonia_coolant_60k")
	public static final Item AMMONIA_COOLANT_60K = null;
	@GameRegistry.ObjectHolder("ammonia_coolant_180k")
	public static final Item AMMONIA_COOLANT_180K = null;
	@GameRegistry.ObjectHolder("ammonia_coolant_360k")
	public static final Item AMMONIA_COOLANT_360K = null;
	@GameRegistry.ObjectHolder("ingot")
	public static final Item INGOT = null;
	@GameRegistry.ObjectHolder("dust")
	public static final Item DUST = null;
	@GameRegistry.ObjectHolder("crushed")
	public static final Item CRUSHED_DUST = null;
	@GameRegistry.ObjectHolder("purified")
	public static final Item PURIFIED_DUST = null;
	@GameRegistry.ObjectHolder("small_pile_dust")
	public static final Item SMALL_PILE_DUST = null;
	@GameRegistry.ObjectHolder("catalyst_module")
	public static final Item REACTION_MODULE = null;
	@GameRegistry.ObjectHolder("uranium_decay_battery")
	public static final Item DECAY_BATTERY_URANIUM = null;
	@GameRegistry.ObjectHolder("thorium_decay_battery")
	public static final Item DECAY_BATTERY_THORIUM = null;
	@GameRegistry.ObjectHolder("plutoium_decay_battery")
	public static final Item DECAY_BATTERY_PLOTONIUM = null;
	@GameRegistry.ObjectHolder("jinkela")
	public static final Item JINKELA = null;
	@GameRegistry.ObjectHolder("fluid_armor")
	public static final Item FLUID_ARMOR = null;

	@GameRegistry.ObjectHolder("tiberium")
	public static final Potion POTION_TIBERIUM = null;
}

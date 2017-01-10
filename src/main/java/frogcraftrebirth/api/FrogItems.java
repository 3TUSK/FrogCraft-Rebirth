package frogcraftrebirth.api;

import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(FrogAPI.MODID)
public class FrogItems {
	
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

	@GameRegistry.ObjectHolder("ion_cannon")
	public static final Item ION_CANNON = null;
	@GameRegistry.ObjectHolder("ion_cannon_frame")
	public static final Item ION_CANNON_FRAME = null;
	
	@GameRegistry.ObjectHolder("uranium_decay_battery")
	public static final Item DECAY_BATTERY_URANIUM = null;
	@GameRegistry.ObjectHolder("thorium_decay_battery")
	public static final Item DECAY_BATTERY_THORIUM = null;
	@GameRegistry.ObjectHolder("plutoium_decay_battery")
	public static final Item DECAY_BATTERY_PLOTRIUM = null;
	
	@GameRegistry.ObjectHolder("jinkela")
	public static final Item JINKELA = null;
	@GameRegistry.ObjectHolder("tiberium")
	public static final Item TIBERIUM = null;
	
	@GameRegistry.ObjectHolder("fluid_armor")
	public static final Item FLUID_ARMOR = null;
	
	@Deprecated
	@Nullable
	public static final ItemStack PNEUMATIC_COMPRESSOR = null;

}

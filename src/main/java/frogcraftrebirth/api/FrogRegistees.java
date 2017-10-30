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

package frogcraftrebirth.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(FrogAPI.MODID)
public final class FrogRegistees {
	@GameRegistry.ObjectHolder("hybrid_storage_unit")
	public static final Block HSU;
	@GameRegistry.ObjectHolder("mobile_power_station")
	public static final Block MPS;
	@GameRegistry.ObjectHolder("condense_tower")
	public static final Block CONDENSE_TOWER;
	@GameRegistry.ObjectHolder("generator")
	public static final Block GENERATOR;
	@GameRegistry.ObjectHolder("machine")
	public static final Block MACHINE;
	@GameRegistry.ObjectHolder("machine_2")
	public static final Block MACHINE2;
	@GameRegistry.ObjectHolder("ore")
	public static final Block ORE;
	@GameRegistry.ObjectHolder("nitric_acid")
	public static final Block NITRIC_ACID;

	@GameRegistry.ObjectHolder("ammonia_coolant_60k")
	public static final Item AMMONIA_COOLANT_60K;
	@GameRegistry.ObjectHolder("ammonia_coolant_180k")
	public static final Item AMMONIA_COOLANT_180K;
	@GameRegistry.ObjectHolder("ammonia_coolant_360k")
	public static final Item AMMONIA_COOLANT_360K;

	@GameRegistry.ObjectHolder("ore_crushed")
	public static final Item ORE_CRUSHED;
	@GameRegistry.ObjectHolder("ore_purified")
	public static final Item ORE_PURIFIED;
	@GameRegistry.ObjectHolder("ore_dust")
	public static final Item ORE_DUST;
	@GameRegistry.ObjectHolder("ore_dust_tiny")
	public static final Item ORE_DUST_TINY;
	@GameRegistry.ObjectHolder("metal_ingot")
	public static final Item METAL_INGOT;
	@GameRegistry.ObjectHolder("metal_plate")
	public static final Item METAL_PLATE;
	@GameRegistry.ObjectHolder("metal_plate_dense")
	public static final Item METAL_PLATE_DENSE;
	@GameRegistry.ObjectHolder("metal_dust")
	public static final Item METAL_DUST;
	@GameRegistry.ObjectHolder("metal_dust_tiny")
	public static final Item METAL_DUST_TNIY;
	@GameRegistry.ObjectHolder("metal_casing")
	public static final Item METAL_CASING;
	@GameRegistry.ObjectHolder("non_metal_dust")
	public static final Item NON_METAL_DUST;
	@GameRegistry.ObjectHolder("non_metal_dust_tiny")
	public static final Item NON_METAL_DUST_TINY;
	@GameRegistry.ObjectHolder("intermediate_product")
	public static final Item INTERMEDIATE;
	@GameRegistry.ObjectHolder("inflammable")
	public static final Item INFLAMMABLE;
	@GameRegistry.ObjectHolder("catalyst_module")
	public static final Item REACTION_MODULE;
	@GameRegistry.ObjectHolder("uranium_decay_battery")
	public static final Item DECAY_BATTERY_URANIUM;
	@GameRegistry.ObjectHolder("thorium_decay_battery")
	public static final Item DECAY_BATTERY_THORIUM;
	@GameRegistry.ObjectHolder("plutoium_decay_battery")
	public static final Item DECAY_BATTERY_PLOTONIUM;
	@GameRegistry.ObjectHolder("jinkela")
	public static final Item JINKELA;
	@GameRegistry.ObjectHolder("fluid_armor")
	public static final Item FLUID_ARMOR;

	static {
		HSU = null;
		MPS = null;
		CONDENSE_TOWER = null;
		GENERATOR = null;
		MACHINE = null;
		MACHINE2 = null;
		ORE = null;
		NITRIC_ACID = null;
		AMMONIA_COOLANT_60K = null;
		AMMONIA_COOLANT_180K = null;
		AMMONIA_COOLANT_360K = null;
		ORE_CRUSHED = null;
		ORE_PURIFIED = null;
		ORE_DUST = null;
		ORE_DUST_TINY = null;
		METAL_INGOT = null;
		METAL_PLATE = null;
		METAL_PLATE_DENSE = null;
		METAL_DUST = null;
		METAL_DUST_TNIY = null;
		METAL_CASING = null;
		NON_METAL_DUST = null;
		NON_METAL_DUST_TINY = null;
		INTERMEDIATE = null;
		INFLAMMABLE = null;
		REACTION_MODULE = null;
		DECAY_BATTERY_URANIUM = null;
		DECAY_BATTERY_THORIUM = null;
		DECAY_BATTERY_PLOTONIUM = null;
		JINKELA = null;
		FLUID_ARMOR = null;
	}
}

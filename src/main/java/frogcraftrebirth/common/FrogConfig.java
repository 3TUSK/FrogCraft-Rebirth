/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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
import net.minecraftforge.common.config.Config;

@SuppressWarnings({"CanBeFinal", "WeakerAccess"})
@Config(modid = FrogAPI.MODID)
@Config.LangKey("config.frogcraftrebirth.general")
public final class FrogConfig {

	@Config.Comment("All options related to modpack creation.")
	@Config.Name("ModpackOptions")
	public static final ModpackOptions modpackOptions = new ModpackOptions();

	@Config.Comment("All options related to cross-mod compatibility")
	@Config.Name("CompatibilityOptions")
	public static final CompatibilityOptions compatibilityOptions = new CompatibilityOptions();

	@Config.Comment("EU consumption rate of Air Pump, in EU/t.")
	@Config.LangKey("config.frogcraftrebirth.machine.airpump_power_rate")
	@Config.Name("AirPumpPowerRate")
	@Config.RangeInt(min = 1, max = 10000)
	public static int airPumpPowerRate = 32;

	@Config.Comment("Liquid air generation speed of Air Pump, in mB/t.")
	@Config.LangKey("config.frogcraftrebirth.machine.airpump_gen_speed")
	@Config.Name("AirPumpGenSpeed")
	@Config.RangeInt(min = 1, max = 1000)
	public static int airPumpGenerationRate = 50;

	@Config.Comment("Energy generation rate of Combustion Furnace, in EU/t.")
	@Config.LangKey("config.frogcraftrebirth.machine.combustion_furnace")
	@Config.Name("CombustionFurnaceGen")
	@Config.RangeInt(min = 1)
	public static int combustionFurnaceGenRate = 10;

	@Config.Comment("Set to false to disable all world generation from FrogCraft: Rebirth.")
	@Config.LangKey("config.frogcraftrebirth.worldgen")
	@Config.Name("EnableWorldGeneration")
	@Config.RequiresMcRestart
	public static boolean enableWorldGen = true;

	public static final class CompatibilityOptions {
		public boolean enableTechRebornCompatibility = false;
	}

	@Config.LangKey("config.frogcraftrebirth.modpack")
	public static final class ModpackOptions {

		@Config.Comment("If true, aluminium will have aluminum series ore dictionary entries.")
		@Config.LangKey("config.frogcraftrebirth.modpack.alt_aluminium_name.all")
		@Config.Name("AlternativeAluminiumOreDictEntry")
		@Config.RequiresMcRestart
		public boolean altAluminiumOreDict = false;

		@Config.Comment("If true, dustAluminum will be registered for aluminium dust. Will do nothing if altAluminiumOreDict is set to false.")
		@Config.LangKey("config.frogcraftrebirth.modpack.alt_aluminium_name.dust")
		@Config.Name("AlternativeAluminiumOreDictEntryForDust")
		@Config.RequiresMcRestart
		public boolean altAluminiumDustOreDict = false;

		@Config.Comment("If true, ingotAluminium will be registered for aluminium ingot. Will do nothing if altAluminiumOreDict is set to false.")
		@Config.LangKey("config.frogcraftrebirth.modpack.alt_aluminium_name.ingot")
		@Config.Name("AlternativeAluminiumOreDictEntryForIngot")
		@Config.RequiresMcRestart
		public boolean altAluminiumIngotOreDict = false;

		@Config.Comment("If true, plateAluminum will be registered for aluminium plate. Will do nothing if altAluminiumOreDict is set to false.")
		@Config.LangKey("config.frogcraftrebirth.modpack.alt_aluminium_name.plate")
		@Config.Name("AlternativeAluminiumOreDictEntryForPlate")
		@Config.RequiresMcRestart
		public boolean altAluminiumPlateOreDict = false;

		@Config.Comment("If true, plateDenseAluminum will be registered for aluminium plate. Will do nothing if altAluminiumOreDict is set to false.")
		@Config.LangKey("config.frogcraftrebirth.modpack.alt_aluminium_name.dense_plate")
		@Config.Name("AlternativeAluminiumOreDictEntryForDensePlate")
		@Config.RequiresMcRestart
		public boolean altAluminiumDensePlateOreDict;

		@Config.Comment("If false, FrogCraft: Rebirth won't register anything to Ore Dictionary.")
		@Config.Name("EnableOreDictEntries")
		@Config.RequiresMcRestart
		public boolean enableOreDictEntries;

		@Config.Comment("If false, all recipes will gone. Useful for modpack creators when they need some total overhaul.")
		@Config.LangKey("config.frogcraftrebirth.modpack.master_recipe_switch")
		@Config.Name("EnableAllRecipes")
		@Config.RequiresMcRestart
		public boolean enableRecipes = true;

	}

}

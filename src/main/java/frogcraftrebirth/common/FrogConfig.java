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
import net.minecraftforge.common.config.Config;

@Config(modid = FrogAPI.MODID)
@Config.LangKey("config.fcrebirth.general")
public class FrogConfig {

	@Config.Comment("All options related to modpack creation.")
	@Config.Name("ModpackOptions")
	public static ModpackOptions modpackOptions = new ModpackOptions();

	@Config.Comment("EU consumption rate of Air Pump, in EU/t.")
	@Config.LangKey("config.fcrebirth.machine.airpump_power_rate")
	@Config.Name("AirPumpPowerRate")
	@Config.RangeInt(min = 1, max = 10000)
	public static int airPumpPowerRate = 120;

	@Config.Comment("Liquid air generation speed of Air Pump, in mB/t.")
	@Config.LangKey("config.fcrebirth.machine.airpump_gen_speed")
	@Config.Name("AirPumpGenSpeed")
	@Config.RangeInt(min = 1, max = 1000)
	public static int airPumpGenerationRate = 50;

	@Config.Comment("Energy generation rate of Combustion Furnace, in EU/t.")
	@Config.LangKey("config.fcrebirth.machine.combustion_furnace")
	@Config.Name("CombustionFurnaceGen")
	@Config.RangeInt(min = 1)
	public static int combustionFurnaceGenRate = 10;

	@Config.Comment("Set to false to disable all world generation from FrogCraft: Rebirth.")
	@Config.LangKey("config.fcrebirth.worldgen")
	@Config.Name("EnableWorldGeneration")
	@Config.RequiresMcRestart
	public static boolean enableWorldGen = true;

	@Config.LangKey("config.fcrebirth.modpack")
	public static class ModpackOptions {

		@Config.Comment("Set it to true to disable all recipes. Useful for modpack creators.")
		@Config.LangKey("config.fcrebirth.modpack.master_recipe_switch")
		@Config.Name("EnableAllRecipes")
		@Config.RequiresMcRestart
		public boolean enableRecipes = true;

		@Config.Name("EnableCondenseTower")
		@Config.RequiresMcRestart
		public boolean enableCondenseTower = true;

		@Config.Name("EnableAirPump")
		@Config.RequiresMcRestart
		public boolean enableAirPump = true;

		@Config.Name("EnableMobilePowerStation")
		@Config.RequiresMcRestart
		public boolean enableMobilePowerStation = true;

		@Config.Name("EnableLiquefier")
		@Config.RequiresMcRestart
		public boolean enableLiquefier = true;

		@Config.Name("EnablePyrolyzer")
		@Config.RequiresMcRestart
		public boolean enablePyrolyzer = true;

		@Config.Name("EnableOres")
		@Config.RequiresMcRestart
		public boolean enableOres = true;

		@Config.Name("EnableAmmoniaCoolant")
		@Config.RequiresMcRestart
		public boolean enableAmmoniaCoolant = true;

		@Config.Name("EnableDecayBattery")
		@Config.RequiresMcRestart
		public boolean enableDecayBattery = true;

		@Config.Name("EnableJinkela")
		@Config.RequiresMcRestart
		public boolean enableJinkela = true;
	}

}

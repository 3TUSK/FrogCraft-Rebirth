package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAPI;
import net.minecraftforge.common.config.Config;

@Config(modid = FrogAPI.MODID)
@Config.LangKey("config.fcrebirth.general")
public class FrogConfig {

	@Config.Comment("Set it to true to disable all recipes. Useful for modpack creators.")
	@Config.LangKey("config.fcrebirth.modpack")
	@Config.Name("ModpackMode")
	@Config.RequiresMcRestart
	public static boolean modpackMode = false;

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

}

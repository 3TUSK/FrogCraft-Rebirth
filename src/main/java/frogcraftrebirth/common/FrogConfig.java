package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAPI;
import net.minecraftforge.common.config.Config;

@Config(modid = FrogAPI.MODID)
@Config.LangKey("config.fcrebirth")
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
}

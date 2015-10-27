package frogcraftrewrite.common.lib.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigMain {

	public static double railgunDamageScale;
	public static int airPumpPowerRate;
	public static int airPumpGenerateSpeed;
	
	public static void initMainConfig(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		airPumpPowerRate = config.get("Machine", "AirPumpPowerRate", 100).getInt();
		airPumpGenerateSpeed = config.get("Machine", "AirPumpGenerateSpeed", 1).getInt();
		railgunDamageScale = config.getFloat("RailgunDamageScale", "Misc", 100, Double.MIN_EXPONENT, Double.MAX_EXPONENT, null, "frogcraft.config.railgunDamageScale");

		
		if (config.hasChanged())
			config.save();
	}
	
}

package frogcraftrebirth.common.lib.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigMain {
	
	static Configuration config;

	public static double railgunDamageScale;
	public static int airPumpPowerRate;
	public static int airPumpGenerateSpeed;
	public static int combustionFurnacePowerRate;
	
	public static boolean enableTCAspect;
	
	public static void initMainConfig(File file) {
		config = new Configuration(file);
		
		config.load();
		
		airPumpPowerRate = config.get("Machine", "AirPumpPowerRate", 120).getInt();
		airPumpGenerateSpeed = config.get("Machine", "AirPumpGenerateSpeed", 50).getInt();
		combustionFurnacePowerRate = config.get("Machine", "CombustionFurnacePowerRate", 10).getInt();
		
		railgunDamageScale = config.getFloat("RailgunDamageScale", "Misc", 100, Double.MIN_EXPONENT, Double.MAX_EXPONENT, null, "frogcraft.config.railgunDamageScale");

		enableTCAspect = config.get("Compatibility", "addAspectIntoItems", true).getBoolean();
		
		if (config.hasChanged())
			config.save();
	}
	
}

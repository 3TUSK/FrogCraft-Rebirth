package frogcraftrewrite.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

	public static double railgunDamageScale;
	
	public static void initConfig(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		railgunDamageScale = config.getFloat("RailgunDamageScale", "Misc", 100, Double.MIN_EXPONENT, Double.MAX_EXPONENT, null, "frogcraft.config.railgunDamageScale");
	
		if (config.hasChanged())
			config.save();
	}
	
}

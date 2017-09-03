package frogcraftrebirth.common.lib.config;

import java.io.File;

import frogcraftrebirth.api.FrogAPI;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @deprecated Will be migrated to {@link frogcraftrebirth.common.FrogConfig}.
 */
@Deprecated
public final class ConfigMain {
	
	private ConfigMain() {
		throw new UnsupportedOperationException();
	}
	
	//Require a thorough clean up	
	
	static Configuration config;

	public static double railgunDamageScale;
	public static int airPumpPowerRate;
	public static int airPumpGenerateSpeed;
	public static int combustionFurnacePowerRate;

	public static boolean enableModpackCreationMode;
	public static boolean enableClassicMode;

	/**
	 * Entry point of initialization of configuration file used by FrogCraft: Rebirth.
	 * @deprecated Not used anymore since migration to <literal>@Config</literal>.
	 * @param event The depending <code>FMLPreInitializationEvent</code> injected.
	 */
	@Deprecated
	public static void init(FMLPreInitializationEvent event) {
		File configDir = new File(event.getModConfigurationDirectory(), FrogAPI.MODID);	
		if (!configDir.exists())
			configDir.mkdirs();
		ConfigMain.initMainConfig(new File(configDir, "FrogMain.cfg"));
	}
	
	private static void initMainConfig(File file) {
		config = new Configuration(file);
		
		config.load();
		
		enableModpackCreationMode = config.getBoolean("EnableModpackCreationMode", "General", false, "Set this to ture will let FrogCraft: Rebirth not loading any recipes, providing convenience for modpack creators.", "frogcraft.config.general.modpack");
		enableClassicMode = config.get("General", "EnableClassicMode", false, "").getBoolean();
		
		airPumpPowerRate = config.get("Machine", "AirPumpPowerRate", 120).getInt();
		airPumpGenerateSpeed = config.get("Machine", "AirPumpGenerateSpeed", 50).getInt();
		combustionFurnacePowerRate = config.get("Machine", "CombustionFurnacePowerRate", 10).getInt();
		
		railgunDamageScale = config.getFloat("RailgunDamageScale", "Misc", 100, Double.MIN_EXPONENT, Double.MAX_EXPONENT, null, "frogcraft.config.railgunDamageScale");

		if (config.hasChanged())
			config.save();
	}
	
}

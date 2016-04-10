package frogcraftrebirth.common.registry;

import java.util.LinkedHashMap;

import cpw.mods.fml.common.Loader;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogItems;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class RegFrogAchievements {
	
	static LinkedHashMap<String, Achievement> map = new LinkedHashMap<>();
	static AchievementPage frogPage;
	
	public static void init() {
		map.put("EVT", EVT);
		map.put("RAILGUN", RAILGUN);
		map.put("POTASSIUM", POTASSIUM);
		map.put("GAS_PUMP", GAS_PUMP);
		map.put("LIQUIFIER ", LIQUIFIER);
		map.put("HSU", HSU);
		map.put("UHSU", UHSU);
		map.put("ADV_CHEM_REACTOR", ADV_CHEM_REACTOR);
		map.put("JINKELA", JINKELA);
		map.put("CONDENSE_TOWER_CORE", CONDENSE_TOWER_CORE);
		map.put("CONDENSE_TOWER", CONDENSE_TOWER);
		map.put("NITRIC_ACID", NITRIC_ACID);
		
		if (Loader.isModLoaded("gregtech"))
			map.put("PNEUMATIC_COMPRESSOR", PNEUMATIC_COMPRESSOR);
		
		frogPage = new AchievementPage(FrogAPI.MODID, (Achievement[]) map.values().toArray());
		AchievementPage.registerAchievementPage(frogPage);
	}
	
	static final Achievement EVT = new Achievement("EVT", "EVT", 0, 0, (ItemStack)null, null);
	static final Achievement RAILGUN = new Achievement("RAILGUN", "railgun", 0, -2, new ItemStack(FrogItems.railgun), null);
	static final Achievement POTASSIUM = new Achievement("POTASSIUM", "potassiumExplosion", -2, 0, new ItemStack(FrogItems.itemIngot, 1, 0), null);
	static final Achievement GAS_PUMP = new Achievement("GAS_PUMP", "gasPump", 2, 0, new ItemStack(FrogBlocks.machines, 1, 1), null);
	static final Achievement PNEUMATIC_COMPRESSOR = new Achievement("PNEUMATIC_COMPRESSOR", "pneumaticCompressor", 2, 2, FrogItems.pneumaticCompressor, GAS_PUMP);
	static final Achievement LIQUIFIER = new Achievement("LIQUIFER", "liquifier", 2, -2, new ItemStack(FrogBlocks.machines), GAS_PUMP);
	static final Achievement HSU = new Achievement("HSU", "HSU", 0, 2, new ItemStack(FrogBlocks.hybridStorageUnit), EVT);
	static final Achievement UHSU = new Achievement("UHSU", "UHSU", 0, 4, new ItemStack(FrogBlocks.hybridStorageUnit, 1, 1), HSU);
	static final Achievement ADV_CHEM_REACTOR = new Achievement("ADV_CHEM_REACTOR", "advChemReactor", 4, 0, new ItemStack(FrogBlocks.machines, 1, 0), GAS_PUMP);
	static final Achievement JINKELA = new Achievement("JINKELA", "jinkela", 4, -2, new ItemStack(FrogItems.jinkela), ADV_CHEM_REACTOR);
	static final Achievement CONDENSE_TOWER_CORE = new Achievement("CONDENSE_TOWER_CORE", "condenseTowerCore", 2, -4, new ItemStack(FrogBlocks.condenseTowerPart), LIQUIFIER);
	static final Achievement CONDENSE_TOWER = new Achievement("CONDENSE_TOWER", "condenseTowerWall", 4, -4, new ItemStack(FrogBlocks.condenseTowerPart), CONDENSE_TOWER_CORE);
	static final Achievement NITRIC_ACID = new Achievement("NITRIC_ACID", "nitricAcidExplodsion", -2, 2, new ItemStack(FrogBlocks.fluidNitricAcid), POTASSIUM);

}

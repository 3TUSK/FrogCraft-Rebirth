package frogcraftrebirth.common.registry;

//import cpw.mods.fml.common.Loader;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogItems;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class RegFrogAchievements {
	
	public static void init() {
		AchievementPage.registerAchievementPage(new AchievementPage(FrogAPI.MODID, 
				EVT, RAILGUN, POTASSIUM, GAS_PUMP, PNEUMATIC_COMPRESSOR, LIQUIFIER, HSU, UHSU, ADV_CHEM_REACTOR, JINKELA, CONDENSE_TOWER_CORE, CONDENSE_TOWER, NITRIC_ACID));
	}
	
	static final Achievement EVT = new Achievement("EVT", "get_EVT", 0, 0, new ItemStack(FrogBlocks.hybridStorageUnit), null);
	static final Achievement RAILGUN = new Achievement("RAILGUN", "get_railgun", 0, -2, new ItemStack(FrogItems.ionCannon), null);
	static final Achievement POTASSIUM = new Achievement("POTASSIUM", "killed_by_Potassium", -2, 0, new ItemStack(FrogItems.itemIngot, 1, 0), null);
	static final Achievement GAS_PUMP = new Achievement("GAS_PUMP", "gaspump", 2, 0, new ItemStack(FrogBlocks.machines, 1, 1), null);
	static final Achievement PNEUMATIC_COMPRESSOR = new Achievement("PNEUMATIC_COMPRESSOR", "pneumaticCompressor", 2, 2, FrogItems.pneumaticCompressor, GAS_PUMP);
	static final Achievement LIQUIFIER = new Achievement("LIQUIFER", "liquifier", 2, -2, new ItemStack(FrogBlocks.machines), GAS_PUMP);
	static final Achievement HSU = new Achievement("HSU", "hsu", 0, 2, new ItemStack(FrogBlocks.hybridStorageUnit), EVT);
	static final Achievement UHSU = new Achievement("UHSU", "uhsu", 0, 4, new ItemStack(FrogBlocks.hybridStorageUnit, 1, 1), HSU);
	static final Achievement ADV_CHEM_REACTOR = new Achievement("ADV_CHEM_REACTOR", "get_ACR", 4, 0, new ItemStack(FrogBlocks.machines, 1, 0), GAS_PUMP);
	static final Achievement JINKELA = new Achievement("JINKELA", "goldclod", 4, -2, new ItemStack(FrogItems.jinkela), ADV_CHEM_REACTOR);
	static final Achievement CONDENSE_TOWER_CORE = new Achievement("CONDENSE_TOWER_CORE", "condensetower", 2, -4, new ItemStack(FrogBlocks.condenseTowerPart), LIQUIFIER);
	static final Achievement CONDENSE_TOWER = new Achievement("CONDENSE_TOWER", "condensetower2", 4, -4, new ItemStack(FrogBlocks.condenseTowerPart), CONDENSE_TOWER_CORE);
	static final Achievement NITRIC_ACID = new Achievement("NITRIC_ACID", "suicideExpert", -2, 2, new ItemStack(FrogBlocks.fluidNitricAcid), POTASSIUM);

}

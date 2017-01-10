package frogcraftrebirth.common.registry;

//import cpw.mods.fml.common.Loader;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogBlocks;
import frogcraftrebirth.api.FrogItems;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class RegFrogAchievements {
	
	public static void init() {
		AchievementPage.registerAchievementPage(new AchievementPage(FrogAPI.MODID, 
				EVT, RAILGUN, POTASSIUM, GAS_PUMP, /*PNEUMATIC_COMPRESSOR,*/ LIQUEFIER, HSU, UHSU, ADV_CHEM_REACTOR, JINKELA, CONDENSE_TOWER_CORE, CONDENSE_TOWER, NITRIC_ACID));
	}
	
	static final Achievement EVT = new Achievement("EVT", "get_EVT", 0, 0, new ItemStack(FrogBlocks.HSU), null);
	static final Achievement RAILGUN = new Achievement("RAILGUN", "get_railgun", 0, -2, new ItemStack(FrogItems.ION_CANNON), null);
	static final Achievement POTASSIUM = new Achievement("POTASSIUM", "killed_by_Potassium", -2, 0, new ItemStack(FrogItems.INGOT, 1, 0), null);
	static final Achievement GAS_PUMP = new Achievement("GAS_PUMP", "gaspump", 2, 0, new ItemStack(FrogBlocks.MACHINE, 1, 1), null);
	//static final Achievement PNEUMATIC_COMPRESSOR = new Achievement("PNEUMATIC_COMPRESSOR", "pneumaticCompressor", 2, 2, FrogItems.pneumaticCompressor, GAS_PUMP);
	static final Achievement LIQUEFIER = new Achievement("LIQUIFER", "liquifier", 2, -2, new ItemStack(FrogBlocks.MACHINE), GAS_PUMP);
	static final Achievement HSU = new Achievement("HSU", "hsu", 0, 2, new ItemStack(FrogBlocks.HSU), EVT);
	static final Achievement UHSU = new Achievement("UHSU", "uhsu", 0, 4, new ItemStack(FrogBlocks.HSU, 1, 1), HSU);
	static final Achievement ADV_CHEM_REACTOR = new Achievement("ADV_CHEM_REACTOR", "get_ACR", 4, 0, new ItemStack(FrogBlocks.MACHINE, 1, 0), GAS_PUMP);
	static final Achievement JINKELA = new Achievement("JINKELA", "goldclod", 4, -2, new ItemStack(FrogItems.JINKELA), ADV_CHEM_REACTOR);
	static final Achievement CONDENSE_TOWER_CORE = new Achievement("CONDENSE_TOWER_CORE", "condensetower", 2, -4, new ItemStack(FrogBlocks.CONDENSE_TOWER), LIQUEFIER);
	static final Achievement CONDENSE_TOWER = new Achievement("CONDENSE_TOWER", "condensetower2", 4, -4, new ItemStack(FrogBlocks.CONDENSE_TOWER), CONDENSE_TOWER_CORE);
	static final Achievement NITRIC_ACID = new Achievement("NITRIC_ACID", "suicideExpert", -2, 2, new ItemStack(FrogBlocks.FLUID_NITRIC_ACID), POTASSIUM);

}

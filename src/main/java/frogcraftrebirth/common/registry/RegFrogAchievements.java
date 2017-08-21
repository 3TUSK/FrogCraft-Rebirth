package frogcraftrebirth.common.registry;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogRegistees;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class RegFrogAchievements {
	
	public static void init() {
		AchievementPage.registerAchievementPage(new AchievementPage(FrogAPI.MODID, 
				EVT, RAILGUN, POTASSIUM, GAS_PUMP, LIQUEFIER, HSU, UHSU, ADV_CHEM_REACTOR, JINKELA, CONDENSE_TOWER_CORE, CONDENSE_TOWER/*, NITRIC_ACID*/));
	}
	
	private static final Achievement EVT = new Achievement("EVT", "get_EVT", 0, 0, new ItemStack(FrogRegistees.HSU), null);
	private static final Achievement RAILGUN = new Achievement("RAILGUN", "get_railgun", 0, -2, new ItemStack(FrogRegistees.ION_CANNON), null);
	private static final Achievement POTASSIUM = new Achievement("POTASSIUM", "killed_by_Potassium", -2, 0, new ItemStack(FrogRegistees.INGOT, 1, 0), null);
	private static final Achievement GAS_PUMP = new Achievement("GAS_PUMP", "gaspump", 2, 0, new ItemStack(FrogRegistees.MACHINE, 1, 1), null);
	private static final Achievement LIQUEFIER = new Achievement("LIQUIFER", "liquifier", 2, -2, new ItemStack(FrogRegistees.MACHINE), GAS_PUMP);
	private static final Achievement HSU = new Achievement("HSU", "hsu", 0, 2, new ItemStack(FrogRegistees.HSU), EVT);
	private static final Achievement UHSU = new Achievement("UHSU", "uhsu", 0, 4, new ItemStack(FrogRegistees.HSU, 1, 1), HSU);
	private static final Achievement ADV_CHEM_REACTOR = new Achievement("ADV_CHEM_REACTOR", "get_ACR", 4, 0, new ItemStack(FrogRegistees.MACHINE, 1, 0), GAS_PUMP);
	private static final Achievement JINKELA = new Achievement("JINKELA", "goldclod", 4, -2, new ItemStack(FrogRegistees.JINKELA), ADV_CHEM_REACTOR);
	private static final Achievement CONDENSE_TOWER_CORE = new Achievement("CONDENSE_TOWER_CORE", "condensetower", 2, -4, new ItemStack(FrogRegistees.CONDENSE_TOWER), LIQUEFIER);
	private static final Achievement CONDENSE_TOWER = new Achievement("CONDENSE_TOWER", "condensetower2", 4, -4, new ItemStack(FrogRegistees.CONDENSE_TOWER), CONDENSE_TOWER_CORE);
	//private static final Achievement NITRIC_ACID = new Achievement("NITRIC_ACID", "suicideExpert", -2, 2, new ItemStack(FrogRegistees.FLUID_NITRIC_ACID), POTASSIUM);

}

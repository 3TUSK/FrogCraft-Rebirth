package frogcraftrewrite.common.registry;

import frogcraftrewrite.common.lib.FrogBlocks;
import frogcraftrewrite.common.lib.FrogItems;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class RegFrogAchievements {
	
	//Temporary block, mark a achievement has an icon from which block does not exist yet.
	private static net.minecraft.block.Block unfinished;
	
	public static AchievementPage frogPage;
	
	public static void init() {
		frogPage = new AchievementPage("FrogCraftRebirth", FrogAchievement.exportAll());
		AchievementPage.registerAchievementPage(frogPage);
		//pneumatic compressor is an issue.
	}
	
	public static enum FrogAchievement {
		
		EVT("EVT", 0, 0, new ItemStack(unfinished)),
		RAILGUN("railgun", 0, -2, new ItemStack(FrogItems.railgun)),
		POTASSIUM("potassiumExplosion", -2, 0, new ItemStack(FrogItems.itemIngot, 1, 0)),
		GAS_PUMP("gasPump", 2, 0, new ItemStack(FrogBlocks.machines, 1, 1)),
		PNEUMATIC_COMPRESSOR("pneumaticCompressor", 2, 2, new ItemStack(unfinished), FrogAchievement.valueOf("GAS_PUMP")),
		LIQUIFER("liquifier", 2, -2, new ItemStack(FrogBlocks.machines), FrogAchievement.valueOf("GAS_PUMP")),
		HSU("HSU", 0, 2, new ItemStack(FrogBlocks.hybridStorageUnit), FrogAchievement.valueOf("EVT")),
		UHSU("UHSU", 0, 4, new ItemStack(FrogBlocks.hybridStorageUnit, 1, 1), FrogAchievement.valueOf("HSU")),
		ADV_CHEM_REACTOR("advChemReactor", 4, 0, new ItemStack(FrogBlocks.machines, 1, 0), FrogAchievement.valueOf("GAS_PUMP")),
		JINKELA("jinkela", 4, -2, new ItemStack(FrogItems.jinkela), FrogAchievement.valueOf("ADV_CHEM_REACTOR")),
		CONDENSE_TOWER_CORE("condenseTowerCore", 2, -4, new ItemStack(FrogBlocks.condenseTowerPart), FrogAchievement.valueOf("LIQUIFIER")),
		CONDENSE_TOWER("condenseTowerWall", 4, -4, new ItemStack(FrogBlocks.condenseTowerPart), FrogAchievement.valueOf("CONDENSE_TOWER_CORE")),
		NITRIC_ACID("nitricAcidExplodsion", -2, 2, new ItemStack(FrogBlocks.fluidNitricAcid), FrogAchievement.valueOf("POTASSIUM"));
		
		final String internal;
		final int posX, posY;
		final ItemStack icon;
		final FrogAchievement required;
		final boolean special;
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon) {
			this(internal, x, y, icon, null, false);
		}
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon, boolean isSpecial) {
			this(internal, x, y, icon, null, isSpecial);
		}
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon, FrogAchievement required) {
			this(internal, x, y, icon, required, false);
		}
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon, FrogAchievement required, boolean isSpecial) {
			this.internal = internal;
			this.posX = x;
			this.posY = y;
			this.icon = icon;
			this.required = required;
			this.special = isSpecial;
		}
		
		public Achievement export() {
			Achievement result = new Achievement(this.name(), internal, posX, posY, icon, this.required != null ? this.required.export() : null);
			return special ? result.setSpecial() : result;
		}
		
		public static Achievement[] exportAll() {
			FrogAchievement[] rawList = FrogAchievement.values();
			Achievement[] list = new Achievement[rawList.length];
			for (int n=0;n<list.length;n++) {
				list[n] = rawList[n].export();
			}
			return list;
		}
	}

}

package frogcraftrewrite.common.registry;

import java.util.LinkedHashMap;

import frogcraftrewrite.common.lib.FrogBlocks;
import frogcraftrewrite.common.lib.FrogItems;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class RegFrogAchievements {
	
	//Temporary block, mark a achievement has an icon from which block does not exist yet.
	private final static net.minecraft.block.Block unfinished = null;
	
	public static AchievementPage frogPage;
	
	static LinkedHashMap<String, Achievement> map = new LinkedHashMap<>();
	
	public static void init() {
		for (FrogAchievement achievement : FrogAchievement.values()) {
			map.put(achievement.name(), achievement.export());
		}
		//not sure if it works.
	}
	
	public static enum FrogAchievement {
		EVT("EVT", 0, 0, new ItemStack(unfinished)),
		RAILGUN("railgun", 0, -2, new ItemStack(FrogItems.railgun)),
		POTASSIUM("potassiumExplosion", -2, 0, new ItemStack(FrogItems.itemIngot, 1, 0)),
		GAS_PUMP("gasPump", 2, 0, new ItemStack(FrogBlocks.machines, 1, 1)),
		PNEUMATIC_COMPRESSOR("pneumaticCompressor", 2, 2, new ItemStack(unfinished), "GAS_PUMP"),
		LIQUIFER("liquifier", 2, -2, new ItemStack(FrogBlocks.machines), "GAS_PUMP"),
		HSU("HSU", 0, 2, new ItemStack(FrogBlocks.hybridStorageUnit), "EVT"),
		UHSU("UHSU", 0, 4, new ItemStack(FrogBlocks.hybridStorageUnit, 1, 1), "HSU"),
		ADV_CHEM_REACTOR("advChemReactor", 4, 0, new ItemStack(FrogBlocks.machines, 1, 0), "GAS_PUMP"),
		JINKELA("jinkela", 4, -2, new ItemStack(FrogItems.jinkela), "ADV_CHEM_REACTOR"),
		CONDENSE_TOWER_CORE("condenseTowerCore", 2, -4, new ItemStack(FrogBlocks.condenseTowerPart), "LIQUIFIER"),
		CONDENSE_TOWER("condenseTowerWall", 4, -4, new ItemStack(FrogBlocks.condenseTowerPart), "CONDENSE_TOWER_CORE"),
		NITRIC_ACID("nitricAcidExplodsion", -2, 2, new ItemStack(FrogBlocks.fluidNitricAcid), "POTASSIUM");
		
		protected final String internal;
		protected final int posX, posY;
		protected final ItemStack icon;
		protected final String required;
		protected final boolean special;
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon) {
			this(internal, x, y, icon, (String)null, false);
		}
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon, boolean isSpecial) {
			this(internal, x, y, icon, (String)null, isSpecial);
		}
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon, String required) {
			this(internal, x, y, icon, required, false);
		}
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon, String required, boolean isSpecial) {
			this.internal = internal;
			this.posX = x;
			this.posY = y;
			this.icon = icon;
			this.required = required;
			this.special = isSpecial;
		}
		
		public Achievement export() {
			Achievement creating = new Achievement(this.name(), internal, posX, posY, icon, map.get(required));
			return special ? creating.setSpecial() : creating;
		}
		
	}

}

package frogcraftrewrite.common.registry;

import frogcraftrewrite.common.lib.FrogItems;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class RegFrogAchievements {
	
	public static AchievementPage frogPage;
	
	public static void init() {
		frogPage = new AchievementPage("FrogCraftRebirth", FrogAchievement.exportAll());
		AchievementPage.registerAchievementPage(frogPage);
	}
	
	public static enum FrogAchievement {
		
		EVT("acquireEVT", 0, 0, null, null),
		RAILGUN("railgun", 0, -2, new ItemStack(FrogItems.railgun), null),
		POTASSIUM("potassiumExplosion", -2, 0, new ItemStack(FrogItems.itemIngot, 1, 0), null),
		GAS_PUMP("gasPump", 2, 0, null, null),
		PNEUMATIC_COMPRESSOR,
		LIQUIFER,
		HSU,
		UHSU,
		ADV_CHEM_REACTOR,
		JINKELA,
		CONDENSE_TOWER_CORE,
		CONDENSE_TOWER,
		NITRIC_ACID;//todo
		
		String internal;
		int posX, posY;
		ItemStack icon;
		FrogAchievement required;
		
		private FrogAchievement() {
			
		}
		
		private FrogAchievement(String internal, int x, int y, ItemStack icon, FrogAchievement required) {
			this.internal = internal;
			this.posX = x;
			this.posY = y;
			this.icon = icon;
			this.required = required;
		}
		
		public Achievement export() {
			return new Achievement(this.name(), internal, posX, posY, icon, this.required != null ? this.required.export() : null);
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

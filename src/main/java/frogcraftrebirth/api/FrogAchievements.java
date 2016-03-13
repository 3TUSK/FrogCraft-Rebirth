/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 3:28:45 PM, Mar 13, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api;

import java.lang.reflect.Field;

import frogcraftrebirth.common.registry.RegFrogAchievements;
import net.minecraft.stats.Achievement;

public enum FrogAchievements {
	
	EVT,
	RAILGUN,
	POTASSIUM,
	GAS_PUMP,
	PNEUMATIC_COMPRESSOR,
	LIQUIFER,
	HSU,
	UHSU,
	ADV_CHEM_REACTOR,
	JINKELA,
	CONDENSE_TOWER_CORE,
	CONDENSE_TOWER,
	NITRIC_ACID;
	
	public Achievement get() {
		Field achievement;
		try {
			achievement = RegFrogAchievements.class.getDeclaredField(this.name());
			return (Achievement)achievement.get(null);
		} catch (Exception e) {
			return null;
		}
		
	}

}

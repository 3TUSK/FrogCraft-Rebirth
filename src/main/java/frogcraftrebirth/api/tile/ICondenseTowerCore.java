/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 9:39:20 AM, Jul 28, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.tile;

public interface ICondenseTowerCore extends ICondenseTowerPart {
	
	boolean checkStructure();
	
	boolean isCompleted();
	
	@Override
	default boolean isFunctional() {
		return true;
	}

}

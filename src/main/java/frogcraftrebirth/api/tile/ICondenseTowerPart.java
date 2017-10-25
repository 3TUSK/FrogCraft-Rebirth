/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 12:41:00 PM, Apr 3, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.tile;

import javax.annotation.Nullable;

public interface ICondenseTowerPart {

	/**
	 * Callback at certain game ticks. The exact time of this method
	 * being called is undefined.
	 */
	void behave();
	
	@Nullable
	ICondenseTowerCore getMainBlock();
	
	void setMainBlock(@Nullable ICondenseTowerCore core);
	
	boolean isFunctional();

}

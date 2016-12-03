/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 12:41:00 PM, Apr 3, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.tile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ICondenseTowerPart {

	/**
	 * A slave method that may be called when a {@link CondensationEvent} is fired.
	 */
	void behave();
	
	void onConstruct(@Nonnull ICondenseTowerCore core);
	
	void onDestruct(@Nonnull ICondenseTowerCore core);
	
	@Nullable
	ICondenseTowerCore getMainBlock();
	
	boolean isFunctional();

}

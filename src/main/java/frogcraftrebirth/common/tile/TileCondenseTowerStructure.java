/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 3:25:33 PM, Apr 3, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.tile;

import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerPart;
import frogcraftrebirth.common.lib.tile.TileFrog;

public class TileCondenseTowerStructure extends TileFrog implements ICondenseTowerPart {

	private ICondenseTowerCore mainBlock;
	
	@Override
	public void behave() {
		
	}

	@Override
	public void onConstruct(ICondenseTowerCore core) {
		this.mainBlock = core;
	}

	@Override
	public void onDestruct(ICondenseTowerCore core) {
		this.mainBlock = null;
	}
	
	@Override
	public ICondenseTowerCore getMainBlock() {
		return mainBlock;
	}
	
	@Override
	public boolean isFunctional() {
		return false;
	}

}

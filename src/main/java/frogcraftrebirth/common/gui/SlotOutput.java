/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 4:14:51 PM, Nov 29, 2015, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information
 */
package frogcraftrebirth.common.gui;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class SlotOutput extends SlotFrog {

	public SlotOutput(IItemHandler inv, int index, int x, int y) {
		super(inv, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}

}

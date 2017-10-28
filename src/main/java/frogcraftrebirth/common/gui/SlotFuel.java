/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 6:26:12 PM, Dec 2, 2015, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.gui;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.IItemHandler;

class SlotFuel extends SlotFrog {

	public SlotFuel(IItemHandler inv, int index, int x, int y) {
		super(inv, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack) > 0;
	}

}

/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:21:32 PM, Jul 27, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.gui;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

class SlotDischarger extends SlotFrog {

	public SlotDischarger(IItemHandler inv, int index, int x, int y) {
		super(inv, index, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack != null && stack.getItem() instanceof IElectricItem && ElectricItem.manager.discharge(stack, Double.MAX_VALUE, Integer.MAX_VALUE, true, true, true) > 0;
	}

}

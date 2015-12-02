/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 6:26:12 PM, Dec 2, 2015, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrewrite.common.gui;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotFuel extends SlotFrog {

	public SlotFuel(IInventory inv, int index, int x, int y) {
		super(inv, index, x, y);
	}
	
	public boolean isItemValid(ItemStack stack) {
		return GameRegistry.getFuelValue(stack) > 0;
	}

}

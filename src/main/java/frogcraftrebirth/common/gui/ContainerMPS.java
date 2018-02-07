/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.gui;

import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMPS extends ContainerTileFrog<TileMobilePowerStation> {

	public ContainerMPS(InventoryPlayer playerInv, TileMobilePowerStation tile) {
		super(playerInv, tile);
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 0, 20, 20));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 1, 38, 20));
		this.addSlotToContainer(new SlotItemHandler(tile.inv, 2, 56, 20));
		this.addSlotToContainer(new SlotCharger(tile.inv, 3, 113, 24));
		this.addSlotToContainer(new SlotDischarger(tile.inv, 4, 113, 42));
		this.registerPlayerInventory(playerInv);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}

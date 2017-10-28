/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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

import frogcraftrebirth.common.tile.TileAdvBlastFurnace;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAdvBlastFurnace extends ContainerTileFrog<TileAdvBlastFurnace> {

	public ContainerAdvBlastFurnace(InventoryPlayer playerInv, TileAdvBlastFurnace tile) {
		super(playerInv, tile);
		this.registerPlayerInventory(playerInv);
		this.addSlotToContainer(new SlotFrog(tile.input, 0, 33, 26));
		this.addSlotToContainer(new SlotFrog(tile.input, 1, 51, 26));
		this.addSlotToContainer(new SlotOutput(tile.output, 0, 109, 26));
		this.addSlotToContainer(new SlotOutput(tile.output, 1, 127, 26));
	}
}

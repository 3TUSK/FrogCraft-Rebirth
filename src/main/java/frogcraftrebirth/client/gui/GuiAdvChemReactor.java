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

package frogcraftrebirth.client.gui;

import java.util.Collections;

import frogcraftrebirth.common.gui.ContainerAdvChemReactor;
import frogcraftrebirth.common.lib.util.MathUtil;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdvChemReactor extends GuiTileFrog<TileAdvChemReactor, ContainerAdvChemReactor> {

	public GuiAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		super(new ContainerAdvChemReactor(playerInv, tile), tile, "gui_adv_chem_reactor.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		if (mouseX > 148 + guiLeft && mouseX < 162 + guiLeft && mouseY > 23 + guiTop && mouseY < 37 + guiTop) {
			this.drawHoveringText(Collections.singletonList(String.format("%s/%s EU", MathUtil.toFancyString(tile.charge), MathUtil.toFancyString(tile.maxCharge))), mouseX - guiLeft, mouseY - guiTop);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		if (tile.isWorking() && tile.processMax != 0) {
			int progress = 10 * tile.process / tile.processMax;
			this.drawTexturedModalRect(guiLeft + 73, guiTop + 40, 176, 0, 30, progress);
		}
		
		int chargePercent = 14 * tile.charge / tile.maxCharge;
		this.drawTexturedModalRect(guiLeft + 148, guiTop + 23 + 14 - chargePercent, 176, 17 + 14 - chargePercent, 14, chargePercent);
	}

}

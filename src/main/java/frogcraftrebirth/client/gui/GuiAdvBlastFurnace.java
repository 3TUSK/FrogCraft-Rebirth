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

package frogcraftrebirth.client.gui;

import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.tile.TileAdvBlastFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdvBlastFurnace extends GuiTileFrog<TileAdvBlastFurnace> {

	public GuiAdvBlastFurnace(ContainerTileFrog container, TileAdvBlastFurnace tile) {
		super(container, tile, "gui_adv_blast_furnace.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(guiBackground);
		this.drawTexturedModalRect(8, 21, 176, 0, 16, 47);
		this.drawTexturedModalRect(152, 21, 176, 0, 16, 47);
		if (mouseX > 9 + guiLeft && mouseX < 25 + guiLeft && mouseY > 22 + guiTop && mouseY < 69 + guiTop) {
			this.renderFluidTankTooltip(tile.inputFluid, mouseX, mouseY);
		}
		if (mouseX > 153 + guiLeft && mouseX < 169 + guiLeft && mouseY > 22 + guiTop && mouseY < 69 + guiTop) {
			this.renderFluidTankTooltip(tile.shieldGas, mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int heatPercent = (int)(24 * tile.getHeatFillingRatio());
		this.drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 51, 176, 53, heatPercent, 6);
		if (tile.isWorking()) {
			int progressPercent = (int)(24 * tile.getProgressRatio());
			this.drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 27, 176, 59, progressPercent, 18);
		}
		this.renderFluidTank(tile.inputFluid, guiLeft + 8, guiTop + 21, 16, 47);
		this.renderFluidTank(tile.shieldGas,guiLeft + 152, guiTop + 21, 16, 47);
	}
}

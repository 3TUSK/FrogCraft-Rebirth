/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
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

import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.util.MathUtil;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCombustionFurnace extends GuiTileFrog<TileCombustionFurnace> {

	public GuiCombustionFurnace(ContainerTileFrog container, TileCombustionFurnace tile) {
		super(container, tile, "gui_combustion_furnace.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(guiBackground);
		this.drawTexturedModalRect(143, 23, 176, 0, 16, 47);
		
		if (mouseX > 143 + guiLeft && mouseX < 159 + guiLeft && mouseY > 23 + guiTop && mouseY < 70 + guiTop) {
			this.renderFluidTankTooltip(tile.tank, mouseX, mouseY);
		}
		
		if (mouseX > 72 + guiLeft && mouseX < 96 + guiLeft && mouseY > 55 + guiTop && mouseY < 72 + guiTop) {
			this.drawHoveringText(Collections.singletonList(String.format("%s/%s EU", MathUtil.toFancyString(tile.charge), MathUtil.toFancyString(5000))), mouseX - guiLeft, mouseY - guiTop);
		}
		
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, GRAY_40);
		this.fontRenderer.drawString(I18n.format("gui.combustionFurnace.title"), 8, ySize - 155, GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int chargePercent = 24 * tile.charge / 5000;
		this.drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 55, 176, 97, chargePercent, 17);
		
		if (tile.working) {
			float progress = (float)tile.time / (float)tile.timeMax;
			float flame = 14 * progress, arrow = 24 * progress;
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 50 + 14 - (int)flame, 176, 66 + 14 - (int)flame, 14, (int)flame);
			this.drawTexturedModalRect(this.guiLeft + 46, this.guiTop + 29, 176, 80, 24 - (int)arrow, 17);
		}
		
		this.renderFluidTank(tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}
	
}

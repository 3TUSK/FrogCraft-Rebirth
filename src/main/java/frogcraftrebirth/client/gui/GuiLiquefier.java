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

import frogcraftrebirth.common.gui.ContainerLiquefier;
import frogcraftrebirth.common.lib.util.FrogMath;
import frogcraftrebirth.common.tile.TileLiquefier;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLiquefier extends GuiTileFrog<TileLiquefier, ContainerLiquefier> {

	public GuiLiquefier(InventoryPlayer playerInv, TileLiquefier tile) {
		super(new ContainerLiquefier(playerInv, tile), tile, "GUI_Liquefier.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(guiBackground);
		this.drawTexturedModalRect(143, 23, 176, 0, 16, 47);
		
		if (mouseX > 143 + guiLeft && mouseX < 159 + guiLeft && mouseY > 23 + guiTop && mouseY < 70 + guiTop) {
			this.renderFluidTankTooltip(tile.tank, mouseX, mouseY);
		}
		
		if (mouseX > 81 + guiLeft && mouseX < 95 + guiLeft && mouseY > 27 + guiTop && mouseY < 41 + guiTop) {
			this.drawHoveringText(Collections.singletonList(String.format("%s/%s EU", FrogMath.toFancyString(tile.charge), FrogMath.toFancyString(tile.maxCharge))), mouseX - guiLeft, mouseY - guiTop);
		}
		
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, GRAY_40);
		this.fontRenderer.drawString(I18n.format("gui.liquefier.title"), 8, ySize - 155, GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int chargeIcon = 14 * tile.charge / 10000; // TileLiquefier has max charge of 10000;
		this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 27 + 14 - chargeIcon, 176, 52 + 14 - chargeIcon, 14, chargeIcon);
	
		int progress = 24 * tile.process / 200;
		this.drawTexturedModalRect(this.guiLeft + 77, this.guiTop + 56, 176, 70, progress, 17);
	
		this.renderFluidTank(tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}
	
}

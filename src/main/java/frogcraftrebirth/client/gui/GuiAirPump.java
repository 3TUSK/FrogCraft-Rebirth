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

import frogcraftrebirth.common.gui.ContainerAirPump;
import frogcraftrebirth.common.lib.util.MathUtil;
import frogcraftrebirth.common.tile.TileAirPump;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAirPump extends GuiTileFrog<TileAirPump, ContainerAirPump> {

	public GuiAirPump(InventoryPlayer playerInv, TileAirPump tile) {
		super(new ContainerAirPump(playerInv, tile), tile, "gui_air_pump.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRenderer.drawString("Industrial Air Pump", 8, ySize - 155, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
		
		if (mouseX > 118 + guiLeft && mouseX < 132 + guiLeft && mouseY > 33 + guiTop && mouseY < 47 + guiTop) {
			this.drawHoveringText(Collections.singletonList(String.format("%s/%s EU", MathUtil.toFancyString(tile.charge), MathUtil.toFancyString(10000))), mouseX - guiLeft, mouseY - guiTop);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int chargeIcon = 14 * tile.charge / 10000; // TileAirPump.MAX_CHARGE = 10000;
		this.drawTexturedModalRect(this.guiLeft + 118, this.guiTop + 33 + 14 - chargeIcon, 188, 14 - chargeIcon, 10, chargeIcon);
		
		int airPercentage = 40 * tile.airAmount() / 1000; //TileAirPump.MAX_AIR = 1000;
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 63 - airPercentage, 176, 0, 12, airPercentage);
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 60 - airPercentage, 176, 41, 12, 4);
	}

}

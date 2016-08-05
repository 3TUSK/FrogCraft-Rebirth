/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:44:36 AM, Aug 5, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.techreborn;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiPneumaticCompressor extends GuiContainer {

	TilePneumaticCompressor tile;
	
	public GuiPneumaticCompressor(InventoryPlayer playerInv, TilePneumaticCompressor tile) {
		super(new ContainerPneumaticCompressor(playerInv, tile));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("PneumaticCompressor"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

}

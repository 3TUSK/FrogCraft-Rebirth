package frogcraftrebirth.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import frogcraftrebirth.common.gui.ContainerPyrolyzer;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiPyrolyzer extends GuiContainer {

	TilePyrolyzer tile;

	public GuiPyrolyzer(InventoryPlayer playerInv, TilePyrolyzer tile) {
		super(new ContainerPyrolyzer(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.pyrolyzer.title"), 8, ySize - 155, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("Pyrolyzer"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int chargeIcon = (int) (15 * tile.charge / tile.maxCharge);
		this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 57 + 14 - chargeIcon, 176, 52 + 14 - chargeIcon, 10, chargeIcon);
		
		if (tile.working) {
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 49, 176, 66, 14, 14);
			int progressPercent = (int) (24 * tile.process / tile.processMax);
			this.drawTexturedModalRect(this.guiLeft + 45, this.guiTop + 29, 176, 80, progressPercent, 17);
		}
		
		if (tile.tank.getFluid() != null) {
			GuiUtil.renderFluidTank(this, tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
		}
	}

}

package frogcraftrebirth.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import frogcraftrebirth.common.gui.ContainerMPS;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiMPS extends GuiContainer {

	protected TileMobilePowerStation tile;

	public GuiMPS(InventoryPlayer playerInv, TileMobilePowerStation tile) {
		super(new ContainerMPS(playerInv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRendererObj.drawString(I18n.format("gui.MPS.title"), 8, ySize - 170, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, GuiUtil.GRAY_40);
	}

	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("MobilePS"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int fillRatio = (int) (40 * tile.getCurrentEnergy() / tile.getCurrentEnergyCapacity());
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 63 - fillRatio, 176, 0, 12, fillRatio);
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 59 - fillRatio, 176, 42, 12, 4);
	}

}

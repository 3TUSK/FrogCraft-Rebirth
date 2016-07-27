package frogcraftrebirth.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import frogcraftrebirth.common.gui.ContainerHybridEStorage;
import frogcraftrebirth.common.tile.TileHSU;
import frogcraftrebirth.common.tile.TileHSUUltra;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiHybridEStorage extends GuiContainer {

	TileHSU tile;

	public GuiHybridEStorage(InventoryPlayer playerInv, TileHSU tile) {
		super(new ContainerHybridEStorage(playerInv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);		
		final int store = tile.getStored(), max = tile.getCapacity(), output = tile.output;
		this.fontRendererObj.drawString(I18n.format(tile instanceof TileHSUUltra ? "gui.HSU.ultra.title" : "gui.HSU.normal.title"), 8, 6, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.HSU.percent", (float)(100 * store / max)), 8, 24, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.HSU.store", store), 8, 34, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.HSU.max", max), 8, 44, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.HSU.output", output), 8, 54, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("HSU"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int e = (int) (40 * tile.getStored() / tile.getCapacity());
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 63 - e, 176, 0, 12, e);
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 59 - e, 176, 42, 12, 4);
	}

}

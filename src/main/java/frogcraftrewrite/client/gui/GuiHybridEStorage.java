package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.client.GuiUtil;
import frogcraftrewrite.common.gui.ContainerHybridEStorage;
import frogcraftrewrite.common.tile.TileHSU;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiHybridEStorage extends GuiContainer {

	TileHSU tile;

	public GuiHybridEStorage(InventoryPlayer playerInv, TileHSU tile) {
		super(new ContainerHybridEStorage(playerInv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.hybridEStorage.current"), 8, ySize - 150, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(Integer.toString((int) tile.getStored()), 8, ySize - 140, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.hybridEStorage.max"), 8, ySize - 130, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(Integer.toString(tile.getCapacity()), 8, ySize - 120, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("HSU"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int charge = this.tile.getStored(), max = this.tile.getCapacity();
		int e = (int) (40 * charge / max);
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 63 - e, 176, 0, 12, e);
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 59 - e, 176, 42, 12, 4);
	}

}

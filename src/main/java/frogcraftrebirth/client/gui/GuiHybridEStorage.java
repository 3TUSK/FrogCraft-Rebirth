package frogcraftrebirth.client.gui;

import frogcraftrebirth.common.gui.ContainerHybridEStorage;
import frogcraftrebirth.common.tile.TileHSU;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHybridEStorage extends GuiTileFrog<TileHSU, ContainerHybridEStorage> {

	final boolean isUHSU;

	public GuiHybridEStorage(InventoryPlayer playerInv, TileHSU tile, boolean isUHSU) {
		super(new ContainerHybridEStorage(playerInv, tile), tile, "GUI_HSU.png");
		this.isUHSU = isUHSU;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);		
		final int store = tile.getStored(), max = tile.getCapacity(), output = tile.output;
		this.fontRenderer.drawString(I18n.format(isUHSU ? "gui.HSU.ultra.title" : "gui.HSU.normal.title"), 8, 6, GRAY_40);
		this.fontRenderer.drawString(I18n.format("gui.HSU.percent", (float)(100 * store / max)), 8, 24, GRAY_40);
		this.fontRenderer.drawString(I18n.format("gui.HSU.store", store), 8, 34, GRAY_40);
		this.fontRenderer.drawString(I18n.format("gui.HSU.max", max), 8, 44, GRAY_40);
		this.fontRenderer.drawString(I18n.format("gui.HSU.output", output), 8, 54, GRAY_40);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96, GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int e = (int) (40 * tile.getStored() / (isUHSU ? 1E8 : 1E9));
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 63 - e, 176, 0, 12, e);
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 59 - e, 176, 42, 12, 4);
	}

}

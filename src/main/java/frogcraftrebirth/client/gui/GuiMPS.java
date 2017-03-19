package frogcraftrebirth.client.gui;

import frogcraftrebirth.common.gui.ContainerMPS;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMPS extends GuiTileFrog<TileMobilePowerStation, ContainerMPS> {

	public GuiMPS(InventoryPlayer playerInv, TileMobilePowerStation tile) {
		super(new ContainerMPS(playerInv, tile), tile, "GUI_MobilePS.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRendererObj.drawString(I18n.format("gui.MPS.title"), 8, ySize - 160, GRAY_40);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, GRAY_40);
	}

	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int fillRatio = 40 * tile.getCurrentEnergy() / tile.getCurrentEnergyCapacity();
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 63 - fillRatio, 176, 0, 12, fillRatio);
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 59 - fillRatio, 176, 42, 12, 4);
	}

}

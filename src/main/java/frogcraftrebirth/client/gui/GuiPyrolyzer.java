package frogcraftrebirth.client.gui;

import java.util.Arrays;

import frogcraftrebirth.common.gui.ContainerPyrolyzer;
import frogcraftrebirth.common.lib.util.FrogMath;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPyrolyzer extends GuiTileFrog<TilePyrolyzer, ContainerPyrolyzer> {

	public GuiPyrolyzer(InventoryPlayer playerInv, TilePyrolyzer tile) {
		super(new ContainerPyrolyzer(playerInv, tile), tile, "GUI_Pyrolyzer.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(guiBackground);
		this.drawTexturedModalRect(143, 23, 176, 0, 16, 47);
		
		if (mouseX > 143 + guiLeft && mouseX < 159 + guiLeft && mouseY > 23 + guiTop && mouseY < 70 + guiTop) {
			this.renderFluidTankTooltip(tile.tank, mouseX, mouseY);
		}
		
		if (mouseX > 81 + guiLeft && mouseX < 95 + guiLeft && mouseY > 57 + guiTop && mouseY < 71 + guiTop) {
			this.drawHoveringText(Arrays.asList(String.format("%s/%s EU", FrogMath.toFancyString(tile.charge), FrogMath.toFancyString(tile.maxCharge))), mouseX - guiLeft, mouseY - guiTop);
		}
		
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.pyrolyzer.title"), 8, ySize - 155, GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int chargeIcon = (int) (14 * tile.charge / tile.maxCharge);
		this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 57 + 14 - chargeIcon, 176, 52 + 14 - chargeIcon, 10, chargeIcon);
		if (tile.isWorking()) {
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 49, 176, 66, 14, 14);
			int progressPercent = tile.processMax == 0 ? 0 : (int) (24 * tile.process / tile.processMax);
			this.drawTexturedModalRect(this.guiLeft + 45, this.guiTop + 29, 176, 80, progressPercent, 17);
		}
		this.renderFluidTank(tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}

}

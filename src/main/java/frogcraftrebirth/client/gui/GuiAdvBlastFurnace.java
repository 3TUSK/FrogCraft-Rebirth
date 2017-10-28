package frogcraftrebirth.client.gui;

import frogcraftrebirth.common.gui.ContainerAdvBlastFurnace;
import frogcraftrebirth.common.tile.TileAdvBlastFurnace;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdvBlastFurnace extends GuiTileFrog<TileAdvBlastFurnace, ContainerAdvBlastFurnace> {

	public GuiAdvBlastFurnace(InventoryPlayer playerInv, TileAdvBlastFurnace tile) {
		super(new ContainerAdvBlastFurnace(playerInv, tile), tile, "gui_adv_blast_furnace.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(guiBackground);
		this.drawTexturedModalRect(8, 21, 176, 0, 16, 47);
		this.drawTexturedModalRect(152, 21, 176, 0, 16, 47);
		if (mouseX > 9 + guiLeft && mouseX < 25 + guiLeft && mouseY > 22 + guiTop && mouseY < 69 + guiTop) {
			this.renderFluidTankTooltip(tile.inputFluid, mouseX, mouseY);
		}
		if (mouseX > 153 + guiLeft && mouseX < 169 + guiLeft && mouseY > 22 + guiTop && mouseY < 69 + guiTop) {
			this.renderFluidTankTooltip(tile.shieldGas, mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int heatPercet = (int)(24 * tile.getHeatFillingRatio());
		this.drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 51, 176, 53, heatPercet, 6);
		if (tile.isWorking()) {
			int progressPercent = (int)(24 * tile.getProgressRatio());
			this.drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 27, 176, 59, progressPercent, 18);
		}
		this.renderFluidTank(tile.inputFluid, guiLeft + 8, guiTop + 21, 16, 47);
		this.renderFluidTank(tile.shieldGas,guiLeft + 152, guiTop + 21, 16, 47);
	}
}

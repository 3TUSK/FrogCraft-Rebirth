package frogcraftrebirth.client.gui;

import java.util.Collections;

import frogcraftrebirth.common.gui.ContainerCombustionFurnace;
import frogcraftrebirth.common.lib.util.FrogMath;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCombustionFurnace extends GuiTileFrog<TileCombustionFurnace, ContainerCombustionFurnace> {

	public GuiCombustionFurnace(InventoryPlayer playerInv, TileCombustionFurnace tile) {
		super(new ContainerCombustionFurnace(playerInv, tile), tile, "GUI_CombustionFurnace.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(guiBackground);
		this.drawTexturedModalRect(143, 23, 176, 0, 16, 47);
		
		if (mouseX > 143 + guiLeft && mouseX < 159 + guiLeft && mouseY > 23 + guiTop && mouseY < 70 + guiTop) {
			this.renderFluidTankTooltip(tile.tank, mouseX, mouseY);
		}
		
		if (mouseX > 72 + guiLeft && mouseX < 96 + guiLeft && mouseY > 55 + guiTop && mouseY < 72 + guiTop) {
			this.drawHoveringText(Collections.singletonList(String.format("%s/%s EU", FrogMath.toFancyString(tile.charge), FrogMath.toFancyString(5000))), mouseX - guiLeft, mouseY - guiTop);
		}
		
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.combustionFurnace.title"), 8, ySize - 155, GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int chargePercent = (int) (24 * tile.charge / 5000);
		this.drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 55, 176, 97, chargePercent, 17);
		
		if (tile.working) {
			float progress = (float)tile.time / (float)tile.timeMax;
			float flame = 14 * progress, arrow = 24 * progress;
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 50 + 14 - (int)flame, 176, 66 + 14 - (int)flame, 14, (int)flame);
			this.drawTexturedModalRect(this.guiLeft + 46, this.guiTop + 29, 176, 80, 24 - (int)arrow, 17);
		}
		
		this.renderFluidTank(tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}
	
}

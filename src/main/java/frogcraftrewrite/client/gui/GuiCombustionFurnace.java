package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.client.GuiUtil;
import frogcraftrewrite.common.gui.ContainerCombustionFurnace;
import frogcraftrewrite.common.tile.TileCombustionFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiCombustionFurnace extends GuiContainer {

	TileCombustionFurnace tile;

	public GuiCombustionFurnace(InventoryPlayer playerInv, TileCombustionFurnace tile) {
		super(new ContainerCombustionFurnace(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1int, int par2int) {
		super.drawGuiContainerForegroundLayer(par1int, par2int);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString("Combustion Furnace", 8, ySize - 155, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("CombustionFurnace"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int chargePercent = (int) (24 * tile.charge / tile.maxCharge);
		this.drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 55, 176, 97, chargePercent, 17);
		
		if (tile.timeMax > 0) {
			int progressPercent = (int) ((tile.timeMax - tile.time) / tile.timeMax);
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 49 + 14 - progressPercent * 14, 176, 66 + progressPercent * 14, 14, progressPercent * 14);
			this.drawTexturedModalRect(this.guiLeft + 45, this.guiTop + 29, 176, 80, progressPercent * 24, 17);
		}
	}
	
}

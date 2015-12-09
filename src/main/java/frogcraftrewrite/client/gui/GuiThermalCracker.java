package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.client.GuiUtil;
import frogcraftrewrite.common.gui.ContainerThermalCracker;
import frogcraftrewrite.common.tile.TileThermalCracker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class GuiThermalCracker extends GuiContainer {

	TileThermalCracker tile;

	public GuiThermalCracker(InventoryPlayer playerInv, TileThermalCracker tile) {
		super(new ContainerThermalCracker(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.thermalCracker.title"), 8, ySize - 155, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("ThermalCracker"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int chargeIcon = (int) (15 * tile.charge / tile.maxCharge);
		this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 57 + 14 - chargeIcon, 176, 52 + 14 - chargeIcon, 10, chargeIcon);
		
		if (tile.working) {
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 49, 176, 66, 14, 14);
			int progressPercent = (int) (24 * tile.process / tile.processMax);
			this.drawTexturedModalRect(this.guiLeft + 45, this.guiTop + 29, 176, 80, progressPercent, 17);
		}

		//Not working yet.
		if (tile.getTankInfo().fluid != null) {
			IIcon fluidIcon = tile.getTankInfo().fluid.getFluid().getIcon();
			this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			this.drawTexturedModalRect(this.guiLeft + 143, this.guiTop + 23, (int)fluidIcon.getMaxU(), (int)fluidIcon.getMaxV(), 16, 16);
		}
	}

}

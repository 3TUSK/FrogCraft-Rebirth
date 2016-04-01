package frogcraftrebirth.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import frogcraftrebirth.common.gui.ContainerCombustionFurnace;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
//import net.minecraftforge.common.util.ForgeDirection;

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
	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("CombustionFurnace"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int chargePercent = (int) (24 * tile.charge / 5000);
		this.drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 55, 176, 97, chargePercent, 17);
		
		if (tile.timeMax > 0) {
			int progressPercent = (int) ((tile.timeMax - tile.time) / tile.timeMax);
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 49 + 14 - progressPercent * 14, 176, 66 + progressPercent * 14, 14, progressPercent * 14);
			this.drawTexturedModalRect(this.guiLeft + 45, this.guiTop + 29, 176, 80, progressPercent * 24, 17);
		}
		
		/*if (this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid != null) {
			GuiUtil.renderFluidTank(this, 144, 24, 16, 42, this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid(), this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount / this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].capacity);
		}*/
	}
	
}

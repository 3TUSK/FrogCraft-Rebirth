package frogcraftrebirth.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import frogcraftrebirth.common.gui.ContainerCondenseTower;
import frogcraftrebirth.common.tile.TileCondenseTower;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCondenseTower extends GuiContainer {

	TileCondenseTower tile;

	public GuiCondenseTower(InventoryPlayer playerInv, TileCondenseTower tile) {
		super(new ContainerCondenseTower(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("CondenseTower_Core"));
		this.drawTexturedModalRect(143, 23, 176, 0, 16, 47);
		
		if (!tile.isCompleted()) //This string does support localization due to a legacy reason.
			this.fontRendererObj.drawString("Incomplete Machine Casing!", 8, ySize - 96, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("CondenseTower_Core"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		GuiUtil.renderFluidTank(this, tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}
	
}

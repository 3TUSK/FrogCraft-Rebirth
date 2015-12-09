package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.client.GuiUtil;
import frogcraftrewrite.common.gui.ContainerCondenseTower;
import frogcraftrewrite.common.tile.TileCondenseTower;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCondenseTower extends GuiContainer {

	TileCondenseTower tile;

	public GuiCondenseTower(InventoryPlayer playerInv, TileCondenseTower tile) {
		super(new ContainerCondenseTower(playerInv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("CondenseTower_Core"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
}

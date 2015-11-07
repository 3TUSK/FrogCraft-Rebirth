package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.common.gui.ContainerThermalCracker;
import frogcraftrewrite.common.tile.TileThermalCracker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiThermalCracker extends GuiContainer {
	
	TileThermalCracker tile;
	
	public GuiThermalCracker(InventoryPlayer playerInv, TileThermalCracker tile) {
		super(new ContainerThermalCracker(playerInv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture_ThermalCracker);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
	private static ResourceLocation texture_ThermalCracker = new ResourceLocation("frogcraftrewrite:texture/gui/GUI_ThermalCracker.png");

}

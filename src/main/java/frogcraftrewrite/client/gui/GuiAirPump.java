package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.common.gui.ContainerAirPump;
import frogcraftrewrite.common.tile.TileAirPump;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAirPump extends GuiContainer {
	
	TileAirPump tile;

	public GuiAirPump(InventoryPlayer playerInv, TileAirPump tile) {
		super(new ContainerAirPump(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1int, int par2int) {
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture_AirPump);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int airPercentage = (int)(tile.airAmount()/1000);
        this.drawTexturedModalRect(k+145, l+63-airPercentage, 176, 0, 12, airPercentage);
        this.drawTexturedModalRect(k+145, l+59-airPercentage, 176, 42, 12, 4);
	}
	
	private static ResourceLocation texture_AirPump = new ResourceLocation("frogcraftrewrite:GUI_AirPump");
}

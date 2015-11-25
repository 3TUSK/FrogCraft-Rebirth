package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.client.GuiUtil;
import frogcraftrewrite.common.gui.ContainerAirPump;
import frogcraftrewrite.common.tile.TileAirPump;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiAirPump extends GuiContainer {

	TileAirPump tile;

	public GuiAirPump(InventoryPlayer playerInv, TileAirPump tile) {
		super(new ContainerAirPump(playerInv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1int, int par2int) {
		super.drawGuiContainerForegroundLayer(par1int, par2int);
		this.fontRendererObj.drawString("Industrial Air Pump", 8, ySize - 155, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("AirPump"));
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		
		int chargeIcon = (int) (15 * tile.charge / tile.maxCharge);
		this.drawTexturedModalRect(k + 118, l + 33 + 14 - chargeIcon, 188, 14 - chargeIcon, 10, chargeIcon);
		
		int airPercentage = (int) (40 * tile.airAmount() / 1000);
		this.drawTexturedModalRect(k + 145, l + 63 - airPercentage, 176, 0, 12, airPercentage);
		this.drawTexturedModalRect(k + 145, l + 60 - airPercentage, 176, 41, 12, 4);
	}

}

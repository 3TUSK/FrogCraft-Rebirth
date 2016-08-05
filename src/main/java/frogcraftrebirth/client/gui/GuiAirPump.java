package frogcraftrebirth.client.gui;

import frogcraftrebirth.common.gui.ContainerAirPump;
import frogcraftrebirth.common.tile.TileAirPump;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAirPump extends GuiTileFrog<TileAirPump, ContainerAirPump> {

	public GuiAirPump(InventoryPlayer playerInv, TileAirPump tile) {
		super(new ContainerAirPump(playerInv, tile), tile, "GUI_AirPump.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRendererObj.drawString("Industrial Air Pump", 8, ySize - 155, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int chargeIcon = (int) (15 * tile.charge / 10000); // TileAirPump.MAX_CHARGE = 10000;
		this.drawTexturedModalRect(this.guiLeft + 118, this.guiTop + 33 + 14 - chargeIcon, 188, 14 - chargeIcon, 10, chargeIcon);
		
		int airPercentage = (int) (40 * tile.airAmount() / 1000); //TileAirPump.MAX_AIR = 1000;
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 63 - airPercentage, 176, 0, 12, airPercentage);
		this.drawTexturedModalRect(this.guiLeft + 145, this.guiTop + 60 - airPercentage, 176, 41, 12, 4);
	}

}

package frogcraftrebirth.client.gui;

import java.util.Arrays;

import frogcraftrebirth.common.gui.ContainerAdvChemReactor;
import frogcraftrebirth.common.lib.util.FrogMath;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdvChemReactor extends GuiTileFrog<TileAdvChemReactor, ContainerAdvChemReactor> {

	public GuiAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		super(new ContainerAdvChemReactor(playerInv, tile), tile, "GUI_AdvanceChemicalReactor.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		if (mouseX > 148 + guiLeft && mouseX < 162 + guiLeft && mouseY > 23 + guiTop && mouseY < 37 + guiTop) {
			this.drawHoveringText(Arrays.asList(String.format("%s/%s EU", FrogMath.toFancyString(tile.charge), FrogMath.toFancyString(tile.maxCharge))), mouseX - guiLeft, mouseY - guiTop);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		if (tile.isWorking() && tile.processMax != 0) {
			int progress = 10 * tile.process / tile.processMax;
			this.drawTexturedModalRect(guiLeft + 73, guiTop + 40, 176, 0, 30, progress);
		}
		
		int chargePercent = 14 * tile.charge / tile.maxCharge;
		this.drawTexturedModalRect(guiLeft + 148, guiTop + 23 + 14 - chargePercent, 176, 17 + 14 - chargePercent, 14, chargePercent);
	}

}

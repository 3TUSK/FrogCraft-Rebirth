package frogcraftrebirth.client.gui;

import frogcraftrebirth.common.gui.ContainerAdvChemReactor;
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
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
	}

}

package frogcraftrewrite.client.gui;

import frogcraftrewrite.common.gui.ContainerAdvChemReactor;
import frogcraftrewrite.common.tile.TileAdvChemReactor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiAdvChemReactor extends GuiContainer {

	TileAdvChemReactor tile;
	
	public GuiAdvChemReactor(InventoryPlayer playerInv, TileAdvChemReactor tile) {
		super(new ContainerAdvChemReactor(playerInv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		
	}

}

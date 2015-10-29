package frogcraftrewrite.client.gui;

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
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		
	}

}

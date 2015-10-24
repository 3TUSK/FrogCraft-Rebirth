package frogcraftrewrite.client.gui;

import frogcraftrewrite.common.gui.ContainerFluidOutputHatch;
import frogcraftrewrite.common.tile.TileFluidOutputHatch;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiFluidOutputHatch extends GuiContainer {

	public GuiFluidOutputHatch(InventoryPlayer playerInv, TileFluidOutputHatch tile) {
		super(new ContainerFluidOutputHatch(playerInv, tile));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		
	}

}

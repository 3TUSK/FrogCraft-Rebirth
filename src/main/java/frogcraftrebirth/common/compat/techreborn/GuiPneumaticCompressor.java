/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:44:36 AM, Aug 5, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.techreborn;

import frogcraftrebirth.client.gui.GuiTileFrog;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiPneumaticCompressor extends GuiTileFrog<TilePneumaticCompressor, ContainerPneumaticCompressor> {

	TilePneumaticCompressor tile;
	
	public GuiPneumaticCompressor(InventoryPlayer playerInv, TilePneumaticCompressor tile) {
		super(new ContainerPneumaticCompressor(playerInv, tile), tile, "GUI_PneumaticCompressor.png");
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
	}

}

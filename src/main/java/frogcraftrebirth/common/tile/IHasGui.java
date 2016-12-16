/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 12:37:47 PM, Dec 16, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.tile;

import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.tile.TileFrog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IHasGui {
	
	ContainerTileFrog<? extends TileFrog> getGuiContainer(World world, EntityPlayer player);
	
	GuiTileFrog<? extends TileFrog, ? extends ContainerTileFrog<? extends TileFrog>> getGui(World world, EntityPlayer player);

}

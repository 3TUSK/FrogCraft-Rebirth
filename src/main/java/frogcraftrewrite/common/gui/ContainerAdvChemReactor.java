package frogcraftrewrite.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerAdvChemReactor extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}

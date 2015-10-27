package frogcraftrewrite.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.tileentity.TileEntity;

public class MultiBlockEvent extends Event {
	
	public final TileEntity main;

	//public final Block[] subs;
	
	public MultiBlockEvent(TileEntity main) {
		this.main = main;
	}

}

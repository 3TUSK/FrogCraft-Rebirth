package frogcraftrewrite.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;

public class MultiBlockEvent extends Event {
	
	public final Block main;

	public final Block[] subs;
	
	public MultiBlockEvent(Block main, Block[] subs) {
		this.main = main;
		this.subs = subs;
	}

}

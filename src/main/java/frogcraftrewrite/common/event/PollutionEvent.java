package frogcraftrewrite.common.event;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.Event.HasResult;
import frogcraftrewrite.api.tileentity.IPollutable;
@Cancelable
@HasResult
public class PollutionEvent extends Event{
	
	public PollutionEvent(IPollutable source, Entity[] affectedEntity, Block[] affectedBlock) {
		
	}

}

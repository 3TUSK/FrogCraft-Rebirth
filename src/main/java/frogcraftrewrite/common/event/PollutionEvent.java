package frogcraftrewrite.common.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.Event.HasResult;
import frogcraftrewrite.api.tile.IPollutable;
/**
 * PollutionEvent will be fired when a machine produce pollution 
 * and affect the environment nearby. You can use <code>source</code>
 * reference to access the tile entity involved. <p>
 * This event can fire on <code>MinecraftForge.EVENT_BUS</code>.
 */
@Cancelable
@HasResult
public class PollutionEvent extends Event{
	
	public final IPollutable source;
	
	public PollutionEvent(IPollutable source) {
		this.source = source;
	}

}

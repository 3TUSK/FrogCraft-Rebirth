package frogcraftrewrite.common.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.Event.HasResult;
import frogcraftrewrite.api.tile.IPersonal;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
@HasResult
public class PersonalizationEvent extends Event {
	
	public final IPersonal tile;
	public final EntityPlayer player;
	
	public PersonalizationEvent(IPersonal tile, EntityPlayer player) {
		this.tile = tile;
		this.player = player;
	}
	
	@HasResult
	public class DispersonalizationEvent extends PersonalizationEvent {

		public DispersonalizationEvent(IPersonal tile, EntityPlayer player) {
			super(tile, player);
		}
		
	}

}

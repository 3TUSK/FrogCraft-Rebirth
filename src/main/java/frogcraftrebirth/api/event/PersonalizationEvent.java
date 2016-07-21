package frogcraftrebirth.api.event;

import frogcraftrebirth.api.tile.IPersonal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.Event.HasResult;

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

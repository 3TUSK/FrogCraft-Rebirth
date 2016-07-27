package frogcraftrebirth.api.event;

import frogcraftrebirth.api.tile.IPersonal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.Event.HasResult;

@Cancelable
@HasResult
public class AccessControlEvent extends Event {

	public final IPersonal tile;
	public final EntityPlayer player;

	public AccessControlEvent(IPersonal tile, EntityPlayer player) {
		this.tile = tile;
		this.player = player;
	}
	
	@HasResult
	public static class Activate extends AccessControlEvent {
		public Activate(IPersonal tile, EntityPlayer player) {
			super(tile, player);
		}	
	}

	@HasResult
	public static class Deactivate extends AccessControlEvent {
		public Deactivate(IPersonal tile, EntityPlayer player) {
			super(tile, player);
		}
	}

}

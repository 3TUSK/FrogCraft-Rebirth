package frogcraftrebirth.api.tile;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Usage is still unclear. Implementation of this interface will mark a
 * tileEntity can be personalized so that permission control will be applied.
 * Method may be called in the future.
 */
public interface IPersonal {

	UUID getOwnerUUID();

	IPersonal setOwner(UUID owner);

	boolean match(UUID beingChecked);

	@Deprecated
	String getWaringInfo(EntityPlayer player);

}

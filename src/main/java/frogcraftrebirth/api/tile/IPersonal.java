package frogcraftrebirth.api.tile;

import net.minecraft.entity.player.EntityPlayer;

import com.mojang.authlib.GameProfile;

/**
 * Usage is still unclear. Implementation of this interface will mark a 
 * tileEntity can be personalized so that permission control will be 
 * applied. Method may be called in the future.
 * */
public interface IPersonal {
	
	GameProfile getOwnerProfile();
	
	String getOwnerName();
	
	IPersonal setOwnerProfile(GameProfile profile);
	
	boolean match(GameProfile beingChecked);
	
	String getWaringInfo(EntityPlayer player);
	
}

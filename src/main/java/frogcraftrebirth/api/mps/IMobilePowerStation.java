/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 12:54:07 PM, Apr 2, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.mps;

import frogcraftrebirth.api.tile.IPersonal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public interface IMobilePowerStation extends IPersonal {
	
	void loadDataFrom(NBTTagCompound tag);
	
	void saveDataTo(NBTTagCompound tag);
	
	int getCurrentEnergy();
	
	int getCurrentEnergyCapacity();

	@Override
	default UUID getOwnerUUID() {
		throw new UnsupportedOperationException("Unsupported");
	}

	@Override
	default IPersonal setOwner(UUID owner) {
		return this;
	}

	@Override
	default boolean match(UUID beingChecked) {
		return false;
	}

	@Override
	default String getWaringInfo(EntityPlayer player) {
		return "Method has been deprecated, this output is legacy support";
	}
}

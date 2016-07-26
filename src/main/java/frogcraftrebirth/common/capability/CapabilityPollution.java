/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 11:48:17 AM, Jul 26, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.capability;

import frogcraftrebirth.api.pollution.IAntiPollutionObject;
import frogcraftrebirth.api.pollution.IPollutionSource;
import frogcraftrebirth.api.pollution.IPollutionVictim;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityPollution {
	
	@CapabilityInject(IPollutionSource.class)
	public static Capability<IPollutionSource> SOURCE = null;
	
	@CapabilityInject(IPollutionVictim.class)
	public static Capability<IPollutionVictim> VICTIM = null;
	
	@CapabilityInject(IAntiPollutionObject.class)
	public static Capability<IAntiPollutionObject> ANTI = null;

	private static class PollutionSourceStorage implements IStorage<IPollutionSource> {

		@Override
		public NBTBase writeNBT(Capability<IPollutionSource> capability, IPollutionSource instance, EnumFacing side) {
			return null;
		}

		@Override
		public void readNBT(Capability<IPollutionSource> capability, IPollutionSource instance, EnumFacing side, NBTBase nbt) {
			
		}
		
	}
	
	private static class PollutionVictimStorage implements IStorage<IPollutionVictim> {

		@Override
		public NBTBase writeNBT(Capability<IPollutionVictim> capability, IPollutionVictim instance, EnumFacing side) {
			return null;
		}

		@Override
		public void readNBT(Capability<IPollutionVictim> capability, IPollutionVictim instance, EnumFacing side, NBTBase nbt) {
			
		}
		
	}
	
	private static class AntiPollutionObjStorage implements IStorage<IAntiPollutionObject> {

		@Override
		public NBTBase writeNBT(Capability<IAntiPollutionObject> capability, IAntiPollutionObject instance, EnumFacing side) {
			return null;
		}

		@Override
		public void readNBT(Capability<IAntiPollutionObject> capability, IAntiPollutionObject instance, EnumFacing side, NBTBase nbt) {
			
		}
		
	}
}

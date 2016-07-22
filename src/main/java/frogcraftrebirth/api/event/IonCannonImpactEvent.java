/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 10:10:33 PM, Apr 8, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.event;

import frogcraftrebirth.common.entity.EntityIonCannonBeam;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.Event;

public class IonCannonImpactEvent extends Event {

	public final EntityIonCannonBeam ionCannonBeam;
	public final RayTraceResult impactPosition;

	public IonCannonImpactEvent(EntityIonCannonBeam entity, RayTraceResult position) {
		this.ionCannonBeam = entity;
		this.impactPosition = position;
	}

}

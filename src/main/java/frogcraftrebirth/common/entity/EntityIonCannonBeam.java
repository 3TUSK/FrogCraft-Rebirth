/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 10:06:12 PM, Apr 8, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.entity;

import frogcraftrebirth.api.event.IonCannonImpactEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class EntityIonCannonBeam extends EntityThrowable {

	public EntityIonCannonBeam(World world) {
		super(world); //Client-side constructor
		setSize(0.5F, 0.5F);
	}
	
	public EntityIonCannonBeam(World world, EntityLivingBase someone) {
		super(world, someone); //Server-side constructor
		setSize(0.5F, 0.5F);
		Vec3d looking = someone.getLookVec();
		this.setThrowableHeading(looking.xCoord, looking.yCoord, looking.zCoord, this.getGravityVelocity(), 1.0F);
	}

	@Override
	protected void onImpact(RayTraceResult objPos) {
		if (!this.world.isRemote)
			MinecraftForge.EVENT_BUS.post(new IonCannonImpactEvent(this, objPos));
	}

}

package frogcraftrebirth.common.entity;

import static frogcraftrebirth.common.item.ItemRailgun.railgun;
import static frogcraftrebirth.common.lib.config.ConfigMain.railgunDamageScale;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@Deprecated
public class EntityRailgunCoin extends EntityThrowable implements IProjectile {

	private double damageCollision, damageExplosion;
	private int timer = 0;
	private Vec3d startPoint;

	public EntityRailgunCoin(World world) {
		super(world);
		setSize(0.5F, 0.5F);
	}

	public EntityRailgunCoin(World world, EntityLivingBase someone) {
		super(world, someone);
		setSize(0.5F, 0.5F);
		this.damageCollision = 20F * railgunDamageScale;
		this.damageExplosion = 20F * railgunDamageScale;
		Vec3d looking = someone.getLookVec();
		startPoint = looking;
		this.setThrowableHeading(looking.xCoord, looking.yCoord, looking.zCoord, this.getGravityVelocity(), 1.0F);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.2F;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (timer % 40 == 0) {
			Vec3d currentPos = new Vec3d(this.lastTickPosX, this.lastTickPosY, this.lastTickPosZ);
			if (startPoint.distanceTo(currentPos) > 50)
				this.setDead();
		} else
			++timer;	
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		this.damageCollision = tag.getDouble("DamageCollision");
		this.damageExplosion = tag.getDouble("DamageExplosion");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setDouble("DamageCollision", damageCollision);
		tag.setDouble("DamageExplosion", damageExplosion);
	}

	@Override
	protected void onImpact(RayTraceResult objPos) {
		Vec3d aVec3 = objPos.hitVec;
		double hitX = aVec3.xCoord, hitY = aVec3.yCoord, hitZ = aVec3.zCoord;

		if (objPos.typeOfHit == RayTraceResult.Type.ENTITY) {
			objPos.entityHit.attackEntityFrom(railgun, (float) damageCollision);
			setDead();
		}
		if (objPos.typeOfHit == RayTraceResult.Type.BLOCK) {
			worldObj.createExplosion(this, hitX, hitY, hitZ, (float) this.damageExplosion, false);
			setDead();
		}
	}

	public double getDamageCollision() {
		return damageCollision;
	}

	public double getDamageExplosion() {
		return damageExplosion;
	}

	public EntityRailgunCoin setDamageCollision(double damage) {
		this.damageCollision = damage;
		return this;
	}

	public EntityRailgunCoin setDamageExplosion(double damage) {
		this.damageExplosion = damage;
		return this;
	}

}

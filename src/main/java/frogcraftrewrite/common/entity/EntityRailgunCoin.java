package frogcraftrewrite.common.entity;
import static frogcraftrewrite.common.Config.railgunDamageScale;
import static frogcraftrewrite.common.item.ItemRailgun.railgun;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityRailgunCoin extends EntityThrowable implements IProjectile{

	private double damageCollision, damageExplosion;
	
	//Client usage only!
	public EntityRailgunCoin(World world) {
		super(world);
		setSize(0.5F, 0.5F);
	}
	
	public EntityRailgunCoin(World world, EntityLivingBase someone) {
		super(world, someone);
		setSize(0.5F, 0.5F);
		this.damageCollision = 20F *railgunDamageScale;
		this.damageExplosion = 20F *railgunDamageScale;
		Vec3 looking = someone.getLookVec();
		this.setThrowableHeading(looking.xCoord, looking.yCoord, looking.zCoord,this.func_70182_d(), 1.0F);
	}

	protected float getGravityVelocity() {
        return 0.0001F;//Electromagentic power
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
	protected void onImpact(MovingObjectPosition objPos) {

		Vec3 aVec3 = objPos.hitVec;
		double hitX = aVec3.xCoord, hitY = aVec3.yCoord, hitZ = aVec3.zCoord;
			
		if (objPos.typeOfHit == MovingObjectType.ENTITY) {
			objPos.entityHit.attackEntityFrom(railgun, (float)damageCollision);
		}
		if (objPos.typeOfHit == MovingObjectType.BLOCK) {
			worldObj.createExplosion((Entity)null, hitX, hitY, hitZ, (float)this.damageExplosion, false);
			System.out.println("Bham!");
		}
			
		setDead();
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

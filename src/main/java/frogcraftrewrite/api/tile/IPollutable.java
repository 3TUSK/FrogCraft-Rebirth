package frogcraftrewrite.api.tile;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public interface IPollutable {
	
	PotionEffect effects(EntityLiving entity);
	
	void pollute(World world, EntityPlayer operator, int originX, int originY, int originZ);

}

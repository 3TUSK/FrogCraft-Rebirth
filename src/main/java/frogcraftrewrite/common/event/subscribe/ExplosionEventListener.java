package frogcraftrewrite.common.event.subscribe;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.ExplosionEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import frogcraftrewrite.common.item.ItemIngot;

public class ExplosionEventListener {
	
	@SubscribeEvent
	public void onPotassiumExplosion(ExplosionEvent event) {
		Entity item = event.explosion.exploder;
		Block block = event.world.getBlock(
				(int)event.explosion.explosionX, 
				(int)event.explosion.explosionY, 
				(int)event.explosion.explosionZ);
		
		if (item instanceof EntityItem){
			if (((EntityItem)item).getEntityItem().getItem() instanceof ItemIngot
					&& ((EntityItem)item).getEntityItem().getItemDamage() == 5){
				if (block == Blocks.water)
					event.world.setBlock(
						(int)event.explosion.explosionX, 
						(int)event.explosion.explosionY, 
						(int)event.explosion.explosionZ, 
						Blocks.bedrock);//TODO Shall create some potassium hydroxide liquid.
			}
		}
	}
}

package frogcraftrewrite.common.event;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.ExplosionEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import frogcraftrewrite.common.item.ItemIngot;
import frogcraftrewrite.common.lib.FrogBlocks;

public class FrogEventListener {
	
	@SubscribeEvent
	public void onPotassiumExplosion(ExplosionEvent event) {
		if (event.world.isRemote) return;
		Entity item = event.explosion.exploder;
		Block block = event.world.getBlock(
				(int)event.explosion.explosionX, 
				(int)event.explosion.explosionY, 
				(int)event.explosion.explosionZ);
		
		if (item instanceof EntityItem){
			if (((EntityItem)item).getEntityItem().getItem() instanceof ItemIngot&& ((EntityItem)item).getEntityItem().getItemDamage() == 0){
				if (block == FrogBlocks.fluidNitricAcid)
					item.entityDropItem(new ItemStack(Items.blaze_rod), 0.5F);
			}
		}
	}
	
	//TODO: achievement system.
}

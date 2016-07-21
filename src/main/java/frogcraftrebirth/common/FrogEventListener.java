package frogcraftrebirth.common;

import frogcraftrebirth.common.item.ItemIngot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FrogEventListener {
	
	//TODO: achievement system.
	@SubscribeEvent
	public void onExplosion(ExplosionEvent event) {
		if (event.getWorld().isRemote) 
			return;
		Entity item = event.getExplosion().getExplosivePlacedBy();	
		IBlockState block = event.getWorld().getBlockState(new BlockPos(event.getExplosion().getPosition()));
		
		if (item instanceof EntityItem){
			if (((EntityItem)item).getEntityItem().getItem() instanceof ItemIngot&& ((EntityItem)item).getEntityItem().getItemDamage() == 0){
				if (block == FrogBlocks.fluidNitricAcid);
					item.entityDropItem(new ItemStack(Items.BLAZE_ROD), 0.5F);
					//TODO
			}			
		}
	}
	
	@SubscribeEvent
	public void onFillingBucket(FillBucketEvent event) {
		//TODO
	}

}

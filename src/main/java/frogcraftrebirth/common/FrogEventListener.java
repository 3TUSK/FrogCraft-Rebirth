package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAchievements;
import frogcraftrebirth.api.event.PersonalizationEvent;
import frogcraftrebirth.common.item.ItemIngot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class FrogEventListener {
	
	@SubscribeEvent
	public void onExplosion(ExplosionEvent event) {
		if (event.getWorld().isRemote) 
			return;
		Entity item = event.getExplosion().getExplosivePlacedBy();	
		IBlockState block = event.getWorld().getBlockState(new BlockPos(event.getExplosion().getPosition()));
		
		if (item instanceof EntityItem){
			if (((EntityItem)item).getEntityItem().getItem() instanceof ItemIngot && ((EntityItem)item).getEntityItem().getItemDamage() == 0){
				if (block == FrogBlocks.fluidNitricAcid) {
					EntityPlayer player = event.getWorld().getClosestPlayerToEntity(item, 10.0D);
					player.addStat(FrogAchievements.POTASSIUM.get());
				}
			}			
		}
	}
	
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event) {
		if (event.crafting.getItem() instanceof ItemBlock) {
			//TODO
		}
	}
	
	@SubscribeEvent
	public void onPersonalize(PersonalizationEvent event) {
		if (event.tile.getOwnerUUID() == null)
			event.tile.setOwner(event.player.getUniqueID());
	}

}

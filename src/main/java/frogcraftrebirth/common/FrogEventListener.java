package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAchievements;
import frogcraftrebirth.api.event.AccessControlEvent;
import frogcraftrebirth.common.item.ItemIngot;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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
		ItemStack crafted = event.crafting;
		if (crafted.getItem() instanceof ItemBlock) {
			Block block = ((ItemBlock)crafted.getItem()).block;
			
			if (block == FrogBlocks.machines)
				switch (block.getMetaFromState(block.getBlockState().getBaseState()) & 0b11) {
					case 0: {
						event.player.addStat(FrogAchievements.ADV_CHEM_REACTOR.get());
						return;
					}
					case 1: {
						event.player.addStat(FrogAchievements.GAS_PUMP.get());
						return;
					}
					case 3: {
						event.player.addStat(FrogAchievements.LIQUEFIER.get());
						return;
					}
				}
			
			if (block == FrogBlocks.condenseTowerPart)
				if ((block.getMetaFromState(block.getBlockState().getBaseState()) & 0b11) == 0) {
					event.player.addStat(FrogAchievements.CONDENSE_TOWER_CORE.get());
					return;
				}
			if (block == FrogBlocks.hybridStorageUnit)
				switch (block.getMetaFromState(block.getBlockState().getBaseState()) % 2) {
					case 0: {
						event.player.addStat(FrogAchievements.HSU.get());
						return;
					}
					case 1: {
						event.player.addStat(FrogAchievements.UHSU.get());
						return;
					}
				}
		} else {
			if (crafted.getItem() == FrogItems.jinkela) {
				event.player.addStat(FrogAchievements.JINKELA.get());
				return;
			}
		}
	}
	
	@SubscribeEvent
	public void onPersonalize(AccessControlEvent event) {
		if (event.tile.getOwnerUUID() == null)
			event.tile.setOwner(event.player.getUniqueID());
	}

}

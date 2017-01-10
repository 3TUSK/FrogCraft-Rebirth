package frogcraftrebirth.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogAchievements;
import frogcraftrebirth.api.FrogBlocks;
import frogcraftrebirth.api.FrogItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class FrogEventListener {
	
	public static final Random RAND = new Random();
	
	@SubscribeEvent
	public void onExplosion(ExplosionEvent event) {
		if (event.getWorld().isRemote) 
			return;
		Entity item = event.getExplosion().getExplosivePlacedBy();	
		IBlockState block = event.getWorld().getBlockState(new BlockPos(event.getExplosion().getPosition()));
		
		if (item instanceof EntityItem){
			if (((EntityItem)item).getEntityItem().getItem() == FrogItems.INGOT && ((EntityItem)item).getEntityItem().getItemDamage() == 0){
				if (block == FrogBlocks.FLUID_NITRIC_ACID) {
					EntityPlayer player = event.getWorld().getClosestPlayerToEntity(item, 5.0D);
					player.addStat(FrogAchievements.POTASSIUM.get());
				}
			}			
		}
	}
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (event.getSource() == FrogAPI.TIBERIUM) {
			event.getEntity().entityDropItem(new ItemStack(FrogItems.TIBERIUM, RAND.nextInt(3), RAND.nextInt(10)), 0.50001F);
		}
	}
	
	@SubscribeEvent
	public void onPlayerDrop(PlayerDropsEvent event) {
		if (event.getSource() == FrogAPI.TIBERIUM) {
			Iterator<EntityItem> iterator = event.getDrops().iterator();
			ArrayList<EntityItem> newDrops = new ArrayList<EntityItem>();
			while (iterator.hasNext()) {
				EntityItem item = iterator.next();
				if (RAND.nextInt(10) == 0) {
					EntityItem replacement = new EntityItem(item.getEntityWorld(), item.posX, item.posY, item.posZ, new ItemStack(FrogItems.TIBERIUM, RAND.nextInt(3), item.getEntityItem().stackSize));
					replacement.motionX = item.motionX;
					replacement.motionY = item.motionY;
					replacement.motionZ = item.motionZ;
					newDrops.add(replacement);
					iterator.remove();
				}
			}
			event.getDrops().addAll(newDrops);
		}
	}
	
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event) {
		ItemStack crafted = event.crafting;
		int meta = crafted.getMetadata();
		if (crafted.getItem() instanceof ItemBlock) {
			Block block = ((ItemBlock)crafted.getItem()).block;
			
			if (block == FrogBlocks.MACHINE)
				switch (meta & 0b11) {
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
			
			if (block == FrogBlocks.CONDENSE_TOWER)
				if ((meta & 0b11) == 0) {
					event.player.addStat(FrogAchievements.CONDENSE_TOWER_CORE.get());
					return;
				}
			if (block == FrogBlocks.HSU)
				switch (meta % 2) {
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
			if (crafted.getItem() == FrogItems.JINKELA) {
				event.player.addStat(FrogAchievements.JINKELA.get());
				return;
			}
		}
	}

}

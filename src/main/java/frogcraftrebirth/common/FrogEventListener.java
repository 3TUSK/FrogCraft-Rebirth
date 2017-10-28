package frogcraftrebirth.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class FrogEventListener {
	
	/*
	@SubscribeEvent
	public void onExplosion(ExplosionEvent event) {
		// TODO rewrite the logic
	}*/

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onFuelValueQueried(FurnaceFuelBurnTimeEvent event) {
		if (OreDictionary.doesOreNameExist("dustSulfur")) {
			for (ItemStack stack : OreDictionary.getOres("dustSulfur")) {
				if (stack.isItemEqual(event.getItemStack())) {
					event.setBurnTime(1600);
					return;
				}
			}
		}
	}

}

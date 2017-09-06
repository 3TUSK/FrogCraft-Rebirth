package frogcraftrebirth.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class FrogEventListener {
	
	@SubscribeEvent
	public void onExplosion(ExplosionEvent event) {
		// TODO rewrite the logic
	}

	@SubscribeEvent
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

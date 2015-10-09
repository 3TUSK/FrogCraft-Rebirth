package frogcraftrewrite.common.item;

import java.util.List;

import frogcraftrewrite.api.frogmatter.Catalyst;
import frogcraftrewrite.api.item.ICatalystModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemCatalystModule extends ItemFrogCraft implements ICatalystModule {
	
	public ItemCatalystModule() {
		super();
	}

	@Override
	public Catalyst getCatalyst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean match() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double accelerate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		// TODO Auto-generated method stub
		return null;
	}

}

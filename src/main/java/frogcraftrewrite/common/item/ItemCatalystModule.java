package frogcraftrewrite.common.item;

import java.util.List;

import frogcraftrewrite.api.item.ICatalystModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tritusk.trichemistry.matter.Catalyst;

public class ItemCatalystModule extends ItemFrogCraft implements ICatalystModule {
	
	String[] nameArray = new String[] {"Heating, Electrolize, Ammonia, V2O5"};
	IIcon[] icons;
	
	public ItemCatalystModule() {
		super(true);
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//The following stuff belongs to the ChemAPI.
	@Override
	public Catalyst getCatalyst() { return null; }
	@Override
	public boolean match() { return false; }
	@Override
	public double accelerate() { return 0; }

	@Override
	public int getSubItemNumber() {
		return nameArray.length;
	}

	

}

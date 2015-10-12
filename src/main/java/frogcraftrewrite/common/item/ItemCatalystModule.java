package frogcraftrewrite.common.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.api.item.ICatalystModule;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import tritusk.trichemistry.matter.Catalyst;

public class ItemCatalystModule extends ItemFrogCraft implements ICatalystModule {
	
	String[] nameArray = new String[] {"Heating", "Electrolize", "Ammonia", "V2O5"};
	IIcon[] icons;
	
	public ItemCatalystModule() {
		super(true);
		icons = new IIcon[nameArray.length];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
		List<String> list = new ArrayList<String>();
		list.add(StatCollector.translateToLocal("item.Item_Miscs."+nameArray[stack.getItemDamage()]+"Module.info"));
		return list;
	}
	
	@Override
	public int getSubItemNumber() {
		return nameArray.length;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item.Item_Miscs."+nameArray[stack.getItemDamage()]+"Module";
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
		for (int i=0;i<icons.length;i++) {
			icons[i] = reg.registerIcon("frogcraftrewrite:reactionModule/"+nameArray[i]+"Module");
		}
	}
	
	//The following stuff belongs to the ChemAPI. Not used, and should be deprecated in the future
	@Override
	public Catalyst getCatalyst() { return null; }
	@Override
	public boolean match() { return false; }
	@Override
	public double accelerate() { return 0; }
	
}

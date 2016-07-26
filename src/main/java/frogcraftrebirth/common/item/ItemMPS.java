package frogcraftrebirth.common.item;

import java.util.List;

import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMPS extends ItemFrogBlock implements IElectricItem {

	public ItemMPS(BlockMPS block) {
		super(block, (ItemStack aStack) -> {
			return "normal";
		});
		setHasSubtypes(false);
		setMaxStackSize(1);
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return true;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		normalize(itemStack);
		return itemStack.getTagCompound().getInteger("maxCharge");
	}

	@Override
	public int getTier(ItemStack itemStack) {
		normalize(itemStack);
		return itemStack.getTagCompound().getInteger("tier");
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		normalize(itemStack);
		return itemStack.getTagCompound().getInteger("tier") * 32;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
		// Add necessary NBT data so that we won't get NPE.
		ItemStack discharged = normalize(new ItemStack(item, 1, 0));
		list.add(discharged.copy());
		ElectricItem.manager.charge(discharged, 60000, 1, true, false);
		list.add(discharged.copy());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> aList, boolean adv) {
		aList.add(I18n.format("tile.mobilePowerStation.info"));
	}
	
	public static ItemStack normalize(ItemStack stack) {
		if (stack.getItem() instanceof ItemMPS && !stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger("charge", 0);
			stack.getTagCompound().setInteger("maxCharge", 60000);
			stack.getTagCompound().setInteger("tier", 1);
			return stack;
		} else 
			return stack; // Prevent attaching unecessary data to other item
	}

}

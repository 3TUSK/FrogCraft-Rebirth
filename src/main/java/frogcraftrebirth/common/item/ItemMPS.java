package frogcraftrebirth.common.item;

import java.util.List;

import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMPS extends ItemFrogBlock implements IElectricItem {

	public ItemMPS(BlockMPS block) {
		super(block, aStack -> "normal");
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
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (this.isInCreativeTab(tab)) {
			// Add necessary NBT data so that we won't get NPE.
			ItemStack discharged = normalize(new ItemStack(this, 1, 0));
			list.add(discharged.copy());
			ElectricItem.manager.charge(discharged, 60000, 1, true, false);
			list.add(discharged);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> aList, ITooltipFlag flag) {
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
			return stack; // Prevent attaching unnecessary data to other item
	}

}

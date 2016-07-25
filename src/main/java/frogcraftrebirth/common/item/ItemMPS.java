package frogcraftrebirth.common.item;

import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;

public class ItemMPS extends ItemFrogBlock implements IElectricItem {

	public ItemMPS(BlockMPS block) {
		super(block, (ItemStack aStack) -> {
			return "normal";
		});
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return true;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		return itemStack.getTagCompound().getInteger("energyMax");
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return itemStack.getTagCompound().getInteger("tier");
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		return itemStack.getTagCompound().getInteger("tier") * 32;
	}

}

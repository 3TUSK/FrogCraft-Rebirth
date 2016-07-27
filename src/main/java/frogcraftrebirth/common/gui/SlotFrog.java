package frogcraftrebirth.common.gui;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public abstract class SlotFrog extends SlotItemHandler {

	public SlotFrog(IItemHandler inv, int index, int x, int y) {
		super(inv, index, x, y);
	}
	
	@Override
	public abstract boolean isItemValid(ItemStack stack);

	
	@Deprecated // the super.putStack(ItemStack stack) is assuming that IItemHandler is IItemHandlerModifiable, so we must rewrite it to fit vanilla style
	@Override
	public void putStack(ItemStack stack) {
		super.putStack(stack);
	}
}

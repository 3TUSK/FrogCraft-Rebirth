package frogcraftrebirth.common.gui;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFrog extends SlotItemHandler {
	
	protected final int index;

	public SlotFrog(IItemHandler inv, int index, int x, int y) {
		super(inv, index, x, y);
		this.index = index;
	}
}

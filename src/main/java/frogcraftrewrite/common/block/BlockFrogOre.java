package frogcraftrewrite.common.block;

import frogcraftrewrite.FrogCraftRebirth;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockFrogOre extends Block {

	public BlockFrogOre() {
		super(Material.rock);
		setCreativeTab(FrogCraftRebirth.TAB_FC);
		setHardness(10.0F);
		setResistance(15.0f);
	}

}

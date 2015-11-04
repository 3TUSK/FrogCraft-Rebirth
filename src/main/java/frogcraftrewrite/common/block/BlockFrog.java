package frogcraftrewrite.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockFrog extends Block {
	
	public String[] nameArray;

	protected BlockFrog(Material material) {
		super(material);
	}
	
	protected BlockFrog setSubNameArray(String... subNames) {
		this.nameArray = subNames;
		return this;
	}

}

package frogcraftrewrite.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockFrog extends BlockContainer {
	
	public String[] nameArray;

	protected BlockFrog(Material material) {
		super(material);
	}
	
	public String[] getSubNamesArray() {
		return this.nameArray;
	}
	
	protected BlockFrog setSubNameArray(String... subNames) {
		this.nameArray = subNames;
		return this;
	}
	
	protected BlockFrog setTileProperty(boolean value) {
		this.isBlockContainer = value;
		return this;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

}

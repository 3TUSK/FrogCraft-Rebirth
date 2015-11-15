package frogcraftrewrite.common.block;

import frogcraftrewrite.api.FrogAPI;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class BlockFrog extends BlockContainer {
	
	public static final String TEXTURE_MAIN = "frogcraftrewrite:";
	
	protected String[] nameArray;
	
	protected IIcon[][] iconArray;
	
	protected BlockFrog(Material material) {
		this(material, 0);
	}

	protected BlockFrog(Material material, int damageValueUpperBound) {
		super(material);
		setCreativeTab(FrogAPI.frogTab);
		this.iconArray = new IIcon[damageValueUpperBound + 1][6];
	}
	
	public String[] getSubNamesArray() {
		return this.nameArray;
	}
	
	protected BlockFrog setSubNameArray(String... subNames) {
		this.nameArray = subNames;
		return this;
	}
	
	protected BlockFrog setNoTile() {
		this.isBlockContainer = false;
		return this;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		if (!isBlockContainer)
			return null;
		
		return null;
	}

}

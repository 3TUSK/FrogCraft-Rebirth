package frogcraftrewrite.common.lib.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
/**
 * Identical to BlockContainer, but inherit from BlockFrog.
 */
public abstract class BlockFrogContainer extends BlockFrog implements ITileEntityProvider {

	protected BlockFrogContainer(Material material) {
		this(material, 0);
	}
	
	protected BlockFrogContainer(Material material, int damageValueUpperBound) {
		super(material, damageValueUpperBound);
		this.isBlockContainer = true;
	}
	
	public abstract TileEntity createNewTileEntity(World world, int meta);
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6Int) {
		super.breakBlock(world, x, y, z, block, par6Int);
		world.removeTileEntity(x, y, z);
	}
	
	public boolean onBlockEventReceived(World world, int x, int y, int z, int eventID, int eventParameter) {
		super.onBlockEventReceived(world, x, y, z, eventID, eventParameter);
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null ? tile.receiveClientEvent(eventID, eventParameter) : false;
	}

}

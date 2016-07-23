package frogcraftrebirth.common.lib.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
/**
 * Identical to BlockContainer, but inherit from BlockFrog.
 */
public abstract class BlockFrogContainer extends BlockFrog implements ITileEntityProvider {
	
	protected BlockFrogContainer(Material material, String registryName) {
		super(material, registryName);
		this.isBlockContainer = true;
	}
	
	public abstract TileEntity createNewTileEntity(World world, int meta);
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}
	
	@Deprecated
	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
		super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
	}

}

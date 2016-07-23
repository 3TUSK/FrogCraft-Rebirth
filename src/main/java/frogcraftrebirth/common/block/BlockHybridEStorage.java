package frogcraftrebirth.common.block;

import javax.annotation.Nullable;

import frogcraftrebirth.FrogCraftRebirth;
import frogcraftrebirth.common.lib.block.BlockFrogContainer;
import frogcraftrebirth.common.lib.tile.TileFrogEStorage;
import frogcraftrebirth.common.tile.TileHSU;
import frogcraftrebirth.common.tile.TileHSUUltra;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHybridEStorage extends BlockFrogContainer {
	
	public BlockHybridEStorage() {
		super(MACHINE);
		setUnlocalizedName("hybridStorageUnit");
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof TileFrogEStorage) {
				if (playerIn.isSneaking()) {
					playerIn.addChatComponentMessage(new net.minecraft.util.text.TextComponentString(((TileFrogEStorage)tile).storedE+";"+((TileFrogEStorage)tile).maxE));
				} else if (tile instanceof TileFrogEStorage) {
					playerIn.openGui(FrogCraftRebirth.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
			case 0: return new TileHSU();
			case 1: return new TileHSUUltra();
			default: return null;
		}
	}
	
	public static enum Level {
		NORMAL, ULTRA;
	}

}

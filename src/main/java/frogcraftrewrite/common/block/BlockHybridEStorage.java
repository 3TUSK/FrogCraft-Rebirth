package frogcraftrewrite.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import frogcraftrewrite.common.lib.block.BlockFrogContainer;
import frogcraftrewrite.common.lib.tile.TileFrogEStorage;
import frogcraftrewrite.common.tile.TileHSU;
import frogcraftrewrite.common.tile.TileHSUUltra;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHybridEStorage extends BlockFrogContainer {
	
	public BlockHybridEStorage() {
		super(Material.iron, 1);
		setSubNameArray("HSU", "UHSU");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		iconArray[0][0] = r.registerIcon("frogcraftrewrite:HSU_Front");
		for (int n=1;n<6;n++) {
			iconArray[0][n] = r.registerIcon("frogcraftrewrite:HSU_Side");
		}
		iconArray[1][0] = r.registerIcon("frogcraftrewrite:UHSU_Front");
		for (int n=1;n<6;n++) {
			iconArray[1][n] = r.registerIcon("frogcraftrewrite:UHSU_Side");
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
		return iconArray[world.getBlockMetadata(x, y, z)][blockSide];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return iconArray[meta][side];
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileFrogEStorage) {
				if (player.isSneaking()) {
					player.addChatComponentMessage(new net.minecraft.util.ChatComponentText(((TileFrogEStorage)tile).storedE+";"+((TileFrogEStorage)tile).maxE));
				} else if (tile instanceof TileFrogEStorage) {
					player.openGui(FrogCraftRebirth.instance, 1, world, x, y, z);
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

}

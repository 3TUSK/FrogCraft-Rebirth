package frogcraftrewrite.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import frogcraftrewrite.common.tile.TileAbstractEStorage;
import frogcraftrewrite.common.tile.TileHSU;
import frogcraftrewrite.common.tile.TileUHSU;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHybridEStorage extends BlockContainer {

	protected String name;
	IIcon[] icons;
	
	public BlockHybridEStorage(String internal) {
		super(Material.iron);
		this.icons = new IIcon[6];
		this.name = internal;
		setBlockName("Machines."+internal);
		setCreativeTab(FrogCraftRebirth.TAB_FC);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		icons[0] = r.registerIcon("frogcraftrewrite:"+name+"_Front");
		for (int n=1;n<6;n++) {
			icons[n] = r.registerIcon("frogcraftrewrite:"+name+"_Side");
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
		return icons[blockSide];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[side];
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileAbstractEStorage) {
				if (player.isSneaking()) {
					player.addChatComponentMessage(new net.minecraft.util.ChatComponentText(((TileAbstractEStorage)tile).storedE+";"+((TileAbstractEStorage)tile).maxE));
				} else if (tile instanceof TileAbstractEStorage) {
					//What is modGuiId?
					player.openGui(FrogCraftRebirth.instance, 1, world, x, y, z);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		if ("HSU".equals(name))
			return new TileHSU();
		if ("UHSU".equals(name))
			return new TileUHSU();
		return null;
		//TODO
	}

	public String getInternalName() {
		return name;
	}
}

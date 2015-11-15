package frogcraftrewrite.common.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import frogcraftrewrite.common.lib.tile.TileFrog;
import frogcraftrewrite.common.tile.TileCondenseTower;
import frogcraftrewrite.common.tile.TileFluidOutputHatch;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCondenseTower extends BlockFrog {
	
	IIcon[][] iconArray;
	
	public static String[] nameArray = new String[] {"Core", "Cylinder", "FluidOutput"};

	public BlockCondenseTower() {
		super(Material.iron);
		this.iconArray = new IIcon[3][6];
		setBlockName("Machine.CondenseTower");
		setHardness(15.0F);
		setResistance(20.0f);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			return new TileCondenseTower();
		case 2:
			return new TileFluidOutputHatch();
		}
		
		return null;
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
		return iconArray[world.getBlockMetadata(x, y, z)][blockSide];
	}
	    
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int blockSide, int blockMeta) {
		return iconArray[blockMeta][blockSide];
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return false;
		else {
			if (world.getTileEntity(x, y, z) instanceof TileFrog) {
				player.openGui(FrogCraftRebirth.instance, 2, world, x, y, z);
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes")List list) {
		for (int i=0;i<nameArray.length;i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		iconArray[0][0] = r.registerIcon("frogcraftrewrite:CondenseTower_Front");
		iconArray[0][1] = r.registerIcon("frogcraftrewrite:CondenseTower_Front");
		iconArray[0][2] = r.registerIcon("frogcraftrewrite:CondenseTower_Front");
		iconArray[0][3] = r.registerIcon("frogcraftrewrite:CondenseTower_Back");
		iconArray[0][4] = r.registerIcon("frogcraftrewrite:CondenseTower_Side");
		iconArray[0][5] = r.registerIcon("frogcraftrewrite:CondenseTower_Side");
		
		iconArray[1][0] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Top");
		iconArray[1][1] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Top");
		iconArray[1][2] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Side");
		iconArray[1][3] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Side");
		iconArray[1][4] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Side");
		iconArray[1][5] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Side");
		
		iconArray[2][0] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Top");
		iconArray[2][1] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Top");
		iconArray[2][2] = r.registerIcon("frogcraftrewrite:CondenseTowerOutput_Front");
		iconArray[2][3] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Side");
		iconArray[2][4] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Side");
		iconArray[2][5] = r.registerIcon("frogcraftrewrite:CondenseTowerCylinder_Side");
	}

}

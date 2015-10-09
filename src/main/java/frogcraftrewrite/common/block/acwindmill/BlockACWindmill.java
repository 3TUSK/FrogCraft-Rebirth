package frogcraftrewrite.common.block.acwindmill;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.FrogCraftRebirth;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
/**
 * ACWindmill stands for "Academy Windmill", the major generator 
 * in the story settings of animation A Certain Scientific Railgun.
 * */
public class BlockACWindmill extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	IIcon[][] iconBuffer;
	
	String[] nameBuffer = {"ACWindMillBase", "ACWindMillTurbine"};
	
	public BlockACWindmill() {
		super(Material.iron);
		setCreativeTab(FrogCraftRebirth.TAB_FC);
		setHardness(10.0F);
		setResistance(10.0F);
		//setBlockName("Machines2.ACWindMillBase");
		iconBuffer = new IIcon[6][6];
	}
	
	@Override
	public int damageDropped(int damage) {
        return damage;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		iconBuffer[0][0] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Bottom");
		iconBuffer[0][1] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Top");
		iconBuffer[0][2] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Side");
		iconBuffer[0][3] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Side");
		iconBuffer[0][4] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Side");
		iconBuffer[0][5] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Side");
		//Register base End

		iconBuffer[1][0] = r.registerIcon("frogcraftrewrite:ACWindMill_Side");
    	iconBuffer[1][1] = r.registerIcon("frogcraftrewrite:ACWindMill_Side");
    	iconBuffer[1][2] = r.registerIcon("frogcraftrewrite:ACWindMill_Back");
    	iconBuffer[1][3] = r.registerIcon("frogcraftrewrite:ACWindMill_Front");    	
    	iconBuffer[1][4] = r.registerIcon("frogcraftrewrite:ACWindMill_Side");
    	iconBuffer[1][5] = r.registerIcon("frogcraftrewrite:ACWindMill_Side");
    	//Register turbine End
	}
	 
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
		return iconBuffer[world.getBlockMetadata(x, y, z)][blockSide];
	}
	    
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int blockSide, int blockMeta) {
		return iconBuffer[blockMeta][blockSide];
	}
	
	//This method will tell minecraft that this block (or item) has multiple types.
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs aTab, @SuppressWarnings("rawtypes")List sub) {
		for (int i=0;i<nameBuffer.length;i++)
			sub.add(new ItemStack(item, 1, i));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta){
			case 0:
				return new TileACWindmillBase();
			case 1:
				return new TileACWindmillTurbine();
			default:
				return null;
		}
	}
}

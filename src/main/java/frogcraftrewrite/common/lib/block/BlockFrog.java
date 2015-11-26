package frogcraftrewrite.common.lib.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.api.FrogAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public abstract class BlockFrog extends Block {
	
	public static final String TEXTURE_MAIN = "frogcraftrewrite:";
	
	protected String[] nameArray;
	
	@SideOnly(Side.CLIENT)
	protected IIcon[][] iconArray;
	
	protected BlockFrog(Material material) {
		this(material, 0);
	}

	protected BlockFrog(Material material, int damageValueUpperBound) {
		super(material);
		setCreativeTab(FrogAPI.frogTab);
		this.iconArray = new IIcon[damageValueUpperBound + 1][6];
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return iconArray[world.getBlockMetadata(x, y, z)][side];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return iconArray[meta][side];
	}
	
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes")List list) {
		for (int i=0;i<nameArray.length;i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	public String[] getSubNamesArray() {
		return this.nameArray;
	}
	
	protected BlockFrog setSubNameArray(String... subNames) {
		this.nameArray = subNames;
		return this;
	}

}

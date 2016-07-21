package frogcraftrebirth.common.lib.block;

import java.util.List;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.api.FrogAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class BlockFrog extends Block {
	
	public static final String TEXTURE_MAIN = "frogcraftrebirth:";
	
	protected String[] nameArray;
	
	protected BlockFrog(Material material) {
		this(material, 0);
	}

	protected BlockFrog(Material material, int damageValueUpperBound) {
		super(material);
		setCreativeTab(FrogAPI.frogTab);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock().getMetaFromState(state);
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

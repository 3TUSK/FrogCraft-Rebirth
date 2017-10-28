package frogcraftrebirth.common.lib.block;

import frogcraftrebirth.api.FrogAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockFrog extends Block {
	
	public static final PropertyBool WORKING = PropertyBool.create("working");
	
	protected final int[] metaArrayForCreativeTab;

	/**
	 * @param material The block {@link Material}
	 * @param registryName The unique identifier for registry.
	 * @param metaForDisplay An array of integer, used for determining which blocks will show in creative tab
	 */
	protected BlockFrog(Material material, String registryName, int... metaForDisplay) {
		super(material);
		setRegistryName(registryName);
		setCreativeTab(FrogAPI.TAB);
		this.metaArrayForCreativeTab = metaForDisplay;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, getPropertyArray());
	}
	
	protected abstract IProperty<?>[] getPropertyArray();
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock().getMetaFromState(state);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (int i : metaArrayForCreativeTab) {
			list.add(new ItemStack(this, 1, i));
		}
	}

}

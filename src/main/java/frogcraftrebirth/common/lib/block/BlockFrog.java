package frogcraftrebirth.common.lib.block;

import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockFrog extends Block {
		
	public static final Material ORE = new Material(MapColor.STONE).setRequiresTool();
	public static final Material MACHINE = new Material(MapColor.SILVER).setBurning().setImmovableMobility();
	public static final Material TIBERIUM = new Material(MapColor.BLUE).setNoPushMobility();
	
	protected int[] metaArrayForCreativeTab;

	protected BlockFrog(Material material, String registryName) {
		super(material);
		setRegistryName(registryName);
		setCreativeTab(FrogAPI.frogTab);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock().getMetaFromState(state);
	}
	
	protected Block setMetaArrayForCreativeTab(int... array) {
		this.metaArrayForCreativeTab = array;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (int i : metaArrayForCreativeTab) {
			list.add(new ItemStack(item, 1, i));
		}
	}

}

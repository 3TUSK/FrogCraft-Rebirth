package frogcraftrebirth.common.lib.block;

import frogcraftrebirth.api.FrogAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public abstract class BlockFrog extends Block {
		
	public static final Material ORE = new Material(MapColor.STONE).setRequiresTool();
	public static final Material MACHINE = new Material(MapColor.SILVER).setBurning().setImmovableMobility();
	public static final Material TIBERIUM = new Material(MapColor.BLUE).setNoPushMobility();

	protected BlockFrog(Material material, String registryName) {
		super(material);
		setRegistryName(registryName);
		setCreativeTab(FrogAPI.frogTab);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock().getMetaFromState(state);
	}

}

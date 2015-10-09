package frogcraftrewrite.common.block;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockFluid extends BlockFluidClassic{

	public BlockFluid(String fluid) {
		super(FluidRegistry.getFluid(fluid), Material.water);
	}
	
	public BlockFluid(Fluid fluid) {
		super(fluid, Material.water);
	}

}

package frogcraftrewrite.common.tile;

import frogcraftrewrite.api.tileentity.FrogFluidTank;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileFluidOutputHatch extends TileFrogInventory implements IFluidTank {

	private FrogFluidTank tank = new FrogFluidTank(8000);
	
	public TileFluidOutputHatch() {
		super(2, "TileEntityFluidOutput");
		//0==input slot, 1==output slot
	}

	int tick = 0;
	@Override
	public void updateEntity() {
		super.updateEntity();
		tick++;
		if (getFluid() == null)
			return;
		if (inv[0] == null)
			return;
		
		//todo: fluid check
		
		if (FluidContainerRegistry.isEmptyContainer(inv[0]) && tick >= 20) {
			if (inv[1] == null) {
				inv[1] = FluidContainerRegistry.fillFluidContainer(new FluidStack(this.getFluid().getFluid(), 1000), new ItemStack(inv[0].getItem(), 1, inv[0].getItemDamage()));
				this.drain(1000, true);
			}
			if (FluidContainerRegistry.isContainer(inv[1]) && FluidContainerRegistry.getFluidForFilledItem(inv[1]).isFluidEqual(inv[1])) {
				inv[1].stackSize += 1;
				this.drain(1000, true);
			}
		}
	}
	
	@Override
	public FluidStack getFluid() {
		return tank.getFluid();
	}

	@Override
	public int getFluidAmount() {
		return tank.getFluidAmount();
	}

	@Override
	public int getCapacity() {
		return tank.getCapacity();
	}

	@Override
	public FluidTankInfo getInfo() {
		return tank.getInfo();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

}

package frogcraftrewrite.common.tile;

import frogcraftrewrite.api.tile.FrogFluidTank;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileFluidOutputHatch extends TileFrogInventory implements IFluidHandler {

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
		if (getTankInfo(ForgeDirection.UNKNOWN)[0].fluid == null)
			return;
		if (inv[0] == null)
			return;
		
		//todo: fluid check
		
		if (FluidContainerRegistry.isEmptyContainer(inv[0]) && tick >= 20) {
			if (inv[1] == null) {
				inv[1] = FluidContainerRegistry.fillFluidContainer(new FluidStack(getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid(), 1000), new ItemStack(inv[0].getItem(), 1, inv[0].getItemDamage()));
				this.drain(ForgeDirection.UNKNOWN, 1000, true);
			}
			if (FluidContainerRegistry.isContainer(inv[1]) && FluidContainerRegistry.getFluidForFilledItem(inv[1]).isFluidEqual(inv[1])) {
				inv[1].stackSize += 1;
				this.drain(ForgeDirection.UNKNOWN, 1000, true);
			}
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		return this.tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {this.tank.getInfo()};
	}
	
	public FluidTankInfo[] getTankInfo() {
		return this.getTankInfo(ForgeDirection.UNKNOWN);
	}

}

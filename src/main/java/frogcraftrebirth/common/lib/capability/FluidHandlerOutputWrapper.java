/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 9:18:25 PM, Aug 29, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib.capability;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class FluidHandlerOutputWrapper implements IFluidHandler {
	
	private final IFluidHandler handler;
	
	public FluidHandlerOutputWrapper(IFluidHandler handler) {
		this.handler = handler;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return handler.getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return handler.drain(resource, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return handler.drain(maxDrain, doDrain);
	}
}

package frogcraftrewrite.common.compat.minetweaker;

import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.recipes.CondenseTowerRecipe;
import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.frogcraft.CondenseTower")
public class CondenseTower {
	
	@ZenMethod
	public static void addRecipe(ILiquidStack[] output, ILiquidStack input, int time) {
		MineTweakerAPI.apply(new AdditionAction(input, output, time));
	}
	
	@ZenMethod
	public static void removeRecipe(ILiquidStack input) {
		MineTweakerAPI.apply(new RemovalAction(input, null, 0));
	}
	
	public static class AdditionAction extends OneWayAction {
		FluidStack input;
		FluidStack[] outputs;
		int time;
		
		public AdditionAction(ILiquidStack input, ILiquidStack[] outputs, int time) {
			FluidStack[] temp = new FluidStack[outputs.length];
			for (int i=0;i<outputs.length;i++) {
				temp[i] = MineTweakerMC.getLiquidStack(outputs[i]);
			}
			this.outputs = temp;
			this.input = MineTweakerMC.getLiquidStack(input);
		}

		@Override
		public void apply() {
			FrogAPI.managerCT.add(new CondenseTowerRecipe(time, input, outputs));
		}

		@Override
		public String describe() {
			return "";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
	}
	
	public static class RemovalAction extends OneWayAction {
		FluidStack input;
		FluidStack[] outputs;
		int time;
		//cost too much, we need a internal constructor
		public RemovalAction(ILiquidStack input, ILiquidStack[] outputs, int time) {
			FluidStack[] temp = new FluidStack[outputs.length];
			for (int i=0;i<outputs.length;i++) {
				temp[i] = MineTweakerMC.getLiquidStack(outputs[i]);
			}
			this.outputs = temp;
			this.input = MineTweakerMC.getLiquidStack(input);
		}
		@Override
		public void apply() {
			FrogAPI.managerCT.remove(new CondenseTowerRecipe(time, input, outputs));
		}

		@Override
		public String describe() {
			return "";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
		
	}

}

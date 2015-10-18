package frogcraftrewrite.api.recipes;

import java.util.HashSet;
import java.util.Set;

import frogcraftrewrite.api.item.ICatalystModule;
import java.util.Arrays;
/**
 * Created at 3:41 P.M. (UTC+8) June 22nd 2015<p>
 * Aim to improve recipe system of FrogCraft, preparing for MineTweaker support.
 * @author 3TUSK
 */
@FrogRecipe(machine = "Advanced Chemical Reactor")
public class AdvChemReactorRecipe{
	
	/**The input and output. Support upon 6 itemstacks (support oredict) and two fluidstack.*/
	private Set<Object> input, output;
	/**The catalyst. Can be null.*/
	private ICatalystModule catalyst;
	/**Time which will spend on processing*/
	private int time, energyRate;
	
	/**Constructor method without catalyst. By default the <code>accelerate</code> parameter will be 1 if no catalyst.*/
	public AdvChemReactorRecipe(Object[] input, Object[] output, int time, int energyRate) {
		this(new HashSet<Object>(Arrays.asList(input)), new HashSet<Object>(Arrays.asList(output)), null, time, energyRate);
	}
	
	/**Constructor method.*/
	public AdvChemReactorRecipe(Set<Object> input, Set<Object> output, ICatalystModule catalyst, int time, int energyRate) {
		this.input = input;
		this.output = output;
		this.catalyst = catalyst;
		this.time = time;
	}
	
	/**Internal usage only, for checking whether a input combination matches a recipe.*/
	public AdvChemReactorRecipe(Set<Object> input, ICatalystModule catalyst) {
		this.input = input;
		this.catalyst = catalyst;
	}
	
	public Set<Object> getInput() {
		return input;
	}
	
	public Set<Object> getOutput() {
		return output;
	}
	
	public ICatalystModule getCatalyst() {
		return catalyst;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getEnergyPerTick() {
		return energyRate;
	}

	
	public boolean hasCatalyst() {
		return catalyst != null;
	}
	
	public AdvChemReactorRecipe setCatalyst(ICatalystModule catalyst, float accelerate) {
		this.catalyst = catalyst;
		return this;
	}
	
	public AdvChemReactorRecipe cancelCatalyst(){
		this.catalyst = null;
		return this;
	}

}

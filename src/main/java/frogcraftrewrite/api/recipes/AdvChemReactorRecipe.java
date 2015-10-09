package frogcraftrewrite.api.recipes;

import java.util.HashSet;
import java.util.Set;

import scala.actors.threadpool.Arrays;
import net.minecraft.item.ItemStack;
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
	private ItemStack catalyst;
	/**Time which will spend on processing*/
	private int time;
	/**Acceleration constant.*/
	private float accelerate;
	
	/**Constructor method without catalyst. By default the <code>accelerate</code> parameter will be 1 if no catalyst.*/
	@SuppressWarnings("unchecked")
	public AdvChemReactorRecipe(Object[] input, Object[] output, int time) {
		this(new HashSet<Object>(Arrays.asList(input)), new HashSet<Object>(Arrays.asList(output)), null, time, 1F);
	}
	
	/**Constructor method.*/
	public AdvChemReactorRecipe(Set<Object> input, Set<Object> output, ItemStack catalyst, int time, float accelerate) {
		this.input = input;
		this.output = output;
		this.catalyst = catalyst;
		this.time = time;
		this.accelerate = accelerate;
	}
	
	/**Internal usage only, for checking whether a input combination matches a recipe.*/
	public AdvChemReactorRecipe(Set<Object> input, ItemStack catalyst) {
		this.input = input;
		this.catalyst = catalyst;
	}
	
	public Set<Object> getInput() {
		return input;
	}
	
	public Set<Object> getOutput() {
		return output;
	}
	
	public ItemStack getCatalyst() {
		return catalyst;
	}
	
	public int getTime() {
		return time;
	}
	
	public float getAccelerate() {
		return accelerate;
	}
	
	public boolean hasCatalyst() {
		return catalyst != null;
	}
	
	public AdvChemReactorRecipe setCatalyst(ItemStack catalyst, float accelerate) {
		this.catalyst = catalyst;
		this.accelerate = accelerate;
		return this;
	}
	
	public AdvChemReactorRecipe cancelCatalyst(){
		this.catalyst = null;
		this.accelerate = 1F;
		return this;
	}

}

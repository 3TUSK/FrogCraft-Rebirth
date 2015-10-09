package frogcraftrewrite.common.compat;

import java.util.List;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.IUsageHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
@SideOnly(Side.CLIENT)
public class DescHandler implements IUsageHandler {

	@Override
	public void drawBackground(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawForeground(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PositionedStack> getIngredientStacks(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PositionedStack> getOtherStacks(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOverlayHandler getOverlayHandler(GuiContainer arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRecipeOverlayRenderer getOverlayRenderer(GuiContainer arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecipeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PositionedStack getResultStack(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> handleItemTooltip(GuiRecipe arg0, ItemStack arg1, List<String> arg2, int arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> handleTooltip(GuiRecipe arg0, List<String> arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasOverlay(GuiContainer arg0, Container arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(GuiRecipe arg0, char arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseClicked(GuiRecipe arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int numRecipes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int recipiesPerPage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IUsageHandler getUsageHandler(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}

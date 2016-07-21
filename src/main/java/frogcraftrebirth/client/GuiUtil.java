package frogcraftrebirth.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiUtil {
	
	public static final int GRAY_40 = 0x404040;
	public static final int GREEN_3E = 0x20EB3E;
	
	public static ResourceLocation getGuiBackground(final String machineName) {
		return new ResourceLocation("frogcraftrebirth:textures/gui/GUI_" + machineName + ".png");
	}

	public static void renderFluidTank(final GuiContainer gui, int x, int y, int tankWidth, int tankHeight, FluidStack fluid, int capacity) {
		//No, this is not working yet
	}
}

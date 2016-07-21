package frogcraftrebirth.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

@SideOnly(Side.CLIENT)
public class GuiUtil {
	
	public static final int GRAY_40 = 0x404040;
	public static final int GREEN_3E = 0x20EB3E;
	
	public static ResourceLocation getGuiBackground(final String machineName) {
		return new ResourceLocation("frogcraftrebirth:textures/gui/GUI_" + machineName + ".png");
	}

	public static void renderFluidTank(final GuiContainer gui, int x, int y, int tankWidth, int tankHeight, FluidStack fluid, int capacity) {
		if (fluid == null) 
			return;

		IIcon fluidIcon = fluid.getFluid().getIcon();
		if (fluidIcon == null) 
			return;
		
		gui.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		int scaledHeight = fluid.amount / capacity * tankHeight;
		gui.drawTexturedModelRectFromIcon(x, y, fluidIcon, tankWidth, scaledHeight);
	}
}

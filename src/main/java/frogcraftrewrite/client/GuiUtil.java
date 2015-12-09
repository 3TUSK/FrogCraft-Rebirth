package frogcraftrewrite.client;

//import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class GuiUtil {
	
	public static final int GRAY_40 = 0x404040;
	
	public static ResourceLocation getGuiBackground(final String machineName) {
		return new ResourceLocation("frogcraftrewrite:textures/gui/GUI_" + machineName + ".png");
	}

	public static void renderFluidTank(GuiContainer gui, int x, int y, int w, int h, Fluid fluid, int percentage) {
		if (fluid == null) return;
		
		IIcon fluidIcon = fluid.getIcon();
		
		if (fluidIcon == null) return;
		
		gui.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		// to be continued.
	}
}

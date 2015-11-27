package frogcraftrewrite.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
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
		
		gui.mc.renderEngine.bindTexture(gui.mc.renderEngine.getResourceLocation(0));
		
		double u = fluidIcon.getInterpolatedU(3.0D);
		double u2 = fluidIcon.getInterpolatedU(13.0D);
		double v = fluidIcon.getInterpolatedV(1.0D);
		double v2 = fluidIcon.getInterpolatedV(15.0D);

		int z = h * percentage/100;

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);

		GL11.glBegin(7);
		GL11.glTexCoord2d(u, v);
		GL11.glVertex2i(x, y + h - z);

		GL11.glTexCoord2d(u, v2);
		GL11.glVertex2i(x, y + h);

		GL11.glTexCoord2d(u2, v2);
		GL11.glVertex2i(x + w, y + h);

		GL11.glTexCoord2d(u2, v);
		GL11.glVertex2i(x + w, y + h -z);
		GL11.glEnd();
	}
}

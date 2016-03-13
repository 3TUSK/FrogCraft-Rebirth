package frogcraftrebirth.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

@SideOnly(Side.CLIENT)
public class GuiUtil {
	
	public static final int GRAY_40 = 0x404040;
	public static final int GREEN_3E = 0x20EB3E;
	
	public static ResourceLocation getGuiBackground(final String machineName) {
		return new ResourceLocation("frogcraftrebirth:textures/gui/GUI_" + machineName + ".png");
	}

	public static void renderFluidTank(final GuiContainer gui, int x, int y, int w, int h, Fluid fluid, int percentage) {
		if (fluid == null) 
			return;
		
		IIcon fluidIcon = fluid.getIcon();
		if (fluidIcon == null) 
			return;
		
		gui.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		gui.drawTexturedModelRectFromIcon(x, y, fluidIcon, w, h);
	}
}

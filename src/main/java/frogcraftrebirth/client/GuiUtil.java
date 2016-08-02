package frogcraftrebirth.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiUtil {
	
	public static final int GRAY_40 = 0x404040;
	public static final int GREEN_3E = 0x20EB3E;
	
	public static ResourceLocation getGuiBackground(final String machineName) {
		return new ResourceLocation("frogcraftrebirth:textures/gui/GUI_" + machineName + ".png");
	}

	/**
	 * Draw a fluid tank with given parameters. If the given tank is null, it will does nothing.
	 * @param gui The {@link GuiContainer} instance, will keep unmodified
	 * @param tank The {@link IFluidTank} instance, will keep unmodified
	 * @param x The starting x-coordinate of tank
	 * @param y The starting y-coordinate of tank
	 * @param tankWidth The fluid tank full width in the GUI texture
	 * @param tankHeight The fluid tank full height in the GUI texture
	 * @throws IllegalArgumentException only if the GuiContainer instance is null
	 */
	public static void renderFluidTank(final GuiContainer gui, final IFluidTank tank, int x, int y, int tankWidth, int tankHeight) {
		if (gui == null)
			throw new IllegalArgumentException("Someone is trying to draw GUI with null GuiContainer reference!!!");
		if (tank == null || tank.getFluid() == null)
			return;
		
		TextureAtlasSprite fluidSprite = gui.mc.getTextureMapBlocks().getAtlasSprite(tank.getFluid().getFluid().getStill().toString());
		if (fluidSprite == null) { //Fall back to purple-black
			fluidSprite = gui.mc.getTextureMapBlocks().getMissingSprite();
		}
		
		gui.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		
		int scaledHeight = tankHeight * tank.getFluidAmount() / tank.getCapacity();
		gui.drawTexturedModalRect(x, y + tankHeight - scaledHeight, fluidSprite, tankWidth, scaledHeight);
	}
}

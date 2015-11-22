package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.common.gui.ContainerMPS;
import frogcraftrewrite.common.tile.TileMobilePowerStation;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiMPS extends GuiContainer {

	protected TileMobilePowerStation tile;

	public GuiMPS(TileMobilePowerStation tile) {
		super(new ContainerMPS(tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		this.fontRendererObj.drawString("What", 0, 0, 0);
	}

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("frogcraft", "Gui_MobilePS"));// Todo:
																									// re-do
																									// the
																									// texture!!!
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		// TODO the charge-meter.
	}

}

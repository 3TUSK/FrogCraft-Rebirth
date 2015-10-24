package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.client.GuiUtil;
import frogcraftrewrite.common.gui.ContainerFluidOutputHatch;
import frogcraftrewrite.common.tile.TileFluidOutputHatch;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFluidOutputHatch extends GuiContainer {
	
	TileFluidOutputHatch tile;

	public GuiFluidOutputHatch(InventoryPlayer playerInv, TileFluidOutputHatch tile) {
		super(new ContainerFluidOutputHatch(playerInv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE_FLUID_OUTPUT);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		//TODO
		GuiUtil.renderFluidTank(0, 0, 0, 0, tile.getFluid().getFluid(), 100*tile.getFluidAmount()/tile.getCapacity());
	}
	
	private static final ResourceLocation TEXTURE_FLUID_OUTPUT = new ResourceLocation("frogcraftrewrite:gui/Gui_LiquidOutput");
}

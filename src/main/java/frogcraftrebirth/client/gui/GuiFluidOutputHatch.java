package frogcraftrebirth.client.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import frogcraftrebirth.common.gui.ContainerFluidOutputHatch;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fluids.FluidStack;

public class GuiFluidOutputHatch extends GuiContainer {

	TileFluidOutputHatch tile;

	public GuiFluidOutputHatch(InventoryPlayer playerInv, TileFluidOutputHatch tile) {
		super(new ContainerFluidOutputHatch(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("LiquidOutput"));
		this.drawTexturedModalRect(143, 23, 176, 0, 16, 47);
		
		if (mouseX > 143 + guiLeft && mouseX < 159 + guiLeft && mouseY > 23 + guiTop && mouseY < 70 + guiTop) {
			FluidStack stack = tile.tank.getFluid();
			if (stack != null) {
				String name = stack.getLocalizedName();
				int amount = stack.amount;
				String[] info = new String[] {I18n.format("gui.fluid.name", name), I18n.format("gui.fluid.amount", amount)};
				this.drawHoveringText(Arrays.asList(info), mouseX - guiLeft, mouseY - guiTop);
			} else {
				this.drawHoveringText(Arrays.asList(I18n.format("gui.fluid.null")), mouseX - guiLeft, mouseY - guiTop);
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1Float, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("LiquidOutput"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		GuiUtil.renderFluidTank(this, tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}

}

package frogcraftrebirth.client.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import frogcraftrebirth.common.gui.ContainerPyrolyzer;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fluids.FluidStack;

public class GuiPyrolyzer extends GuiContainer {

	TilePyrolyzer tile;

	public GuiPyrolyzer(InventoryPlayer playerInv, TilePyrolyzer tile) {
		super(new ContainerPyrolyzer(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("Pyrolyzer"));
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
		
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.pyrolyzer.title"), 8, ySize - 155, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float particalTick, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("Pyrolyzer"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int chargeIcon = (int) (14 * tile.charge / tile.maxCharge);
		this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 57 + 14 - chargeIcon, 176, 52 + 14 - chargeIcon, 10, chargeIcon);
		
		if (tile.working) {
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 49, 176, 66, 14, 14);
			int progressPercent = (int) (24 * tile.process / tile.processMax);
			this.drawTexturedModalRect(this.guiLeft + 45, this.guiTop + 29, 176, 80, progressPercent, 17);
		}
		
		GuiUtil.renderFluidTank(this, tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}

}

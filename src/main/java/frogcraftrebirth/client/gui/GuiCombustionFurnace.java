package frogcraftrebirth.client.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.client.GuiUtil;
import frogcraftrebirth.common.gui.ContainerCombustionFurnace;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fluids.FluidStack;

public class GuiCombustionFurnace extends GuiContainer {

	TileCombustionFurnace tile;

	public GuiCombustionFurnace(InventoryPlayer playerInv, TileCombustionFurnace tile) {
		super(new ContainerCombustionFurnace(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("CombustionFurnace"));
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
		
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString(I18n.format("gui.combustionFurnace.title"), 8, ySize - 155, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float particalTick, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("CombustionFurnace"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int chargePercent = (int) (24 * tile.charge / 5000);
		this.drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 55, 176, 97, chargePercent, 17);
		
		if (tile.working) {
			float progress = (float)tile.time / (float)tile.timeMax;
			float flame = 14 * progress, arrow = 24 * progress;
			this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 50 + 14 - (int)flame, 176, 66 + 14 - (int)flame, 14, (int)flame);
			this.drawTexturedModalRect(this.guiLeft + 46, this.guiTop + 29, 176, 80, 24 - (int)arrow, 17);
		}
		
		GuiUtil.renderFluidTank(this, tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}
	
}

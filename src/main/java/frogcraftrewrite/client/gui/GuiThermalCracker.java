package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.client.GuiUtil;
import frogcraftrewrite.common.gui.ContainerThermalCracker;
import frogcraftrewrite.common.tile.TileThermalCracker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class GuiThermalCracker extends GuiContainer {

	TileThermalCracker tile;

	public GuiThermalCracker(InventoryPlayer playerInv, TileThermalCracker tile) {
		super(new ContainerThermalCracker(playerInv, tile));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1int, int par2int) {
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString("Thermal Cracker", 8, ySize - 155, GuiUtil.GRAY_40);
		this.fontRendererObj.drawString("Charge: "+tile.charge, 8, ySize - 145, GuiUtil.GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiUtil.getGuiBackground("ThermalCracker"));
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		
		int chargeIcon = (int) (15 * tile.charge / tile.maxCharge);
		this.drawTexturedModalRect(k + 81, l + 57 + 14 - chargeIcon, 176, 52 + 14 - chargeIcon, 10, chargeIcon);
		
		if (tile.working) {
			this.drawTexturedModalRect(k + 25, l + 49, 176, 66, 14, 14);
			int progressPercent = (int) (24 * tile.process / tile.processMax);
			this.drawTexturedModalRect(k + 45, l + 29, 176, 80, progressPercent, 17);
		}
		
		//GuiUtil.renderFluidTank(this, 143, 23, 16, 47, FluidRegistry.getFluid(((ContainerThermalCracker)inventorySlots).fluidID), (int)(((ContainerThermalCracker)inventorySlots).fluidAmount / 10));
		if (tile.getTankInfo().fluid != null) {
			IIcon fluidIcon = tile.getTankInfo().fluid.getFluid().getIcon();
			this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			this.drawTexturedModalRect(k + 143, l + 23, (int)fluidIcon.getMaxU(), (int)fluidIcon.getMaxV(), 16, 16);
		}
	}

}

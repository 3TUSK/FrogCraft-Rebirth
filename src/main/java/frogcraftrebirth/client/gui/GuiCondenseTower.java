package frogcraftrebirth.client.gui;

import java.util.Arrays;

import frogcraftrebirth.common.gui.ContainerCondenseTower;
import frogcraftrebirth.common.lib.util.FrogMath;
import frogcraftrebirth.common.tile.TileCondenseTower;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCondenseTower extends GuiTileFrog<TileCondenseTower, ContainerCondenseTower> {

	public GuiCondenseTower(InventoryPlayer playerInv, TileCondenseTower tile) {
		super(new ContainerCondenseTower(playerInv, tile), tile, "GUI_CondenseTower_Core.png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(guiBackground);
		this.drawTexturedModalRect(143, 23, 176, 0, 16, 47);
		
		if (mouseX > 143 + guiLeft && mouseX < 159 + guiLeft && mouseY > 23 + guiTop && mouseY < 70 + guiTop) {
			this.renderFluidTankTooltip(tile.tank, mouseX, mouseY);
		}
		
		if (mouseX > 81 + guiLeft && mouseX < 95 + guiLeft && mouseY > 27 + guiTop && mouseY < 41 + guiTop) {
			this.drawHoveringText(Arrays.asList(String.format("%s/%s EU", FrogMath.toFancyString(tile.charge), FrogMath.toFancyString(tile.maxCharge))), mouseX - guiLeft, mouseY - guiTop);
		}
		
		if (!tile.isCompleted()) //This string doesn't support localization due to a legacy reason.
			this.fontRendererObj.drawString("Incomplete Machine Casing!", 8, ySize - 96, GRAY_40);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		
		int chargeIcon = (int) (14 * tile.charge / 10000); //Max charge of Condense Tower
		this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 27 + 14 - chargeIcon, 176, 52 + 14 - chargeIcon, 14, chargeIcon);
		
		this.renderFluidTank(tile.tank, this.guiLeft + 143, this.guiTop + 23, 16, 47);
	}
	
}

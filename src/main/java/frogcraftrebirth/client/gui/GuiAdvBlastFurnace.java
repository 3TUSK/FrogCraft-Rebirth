package frogcraftrebirth.client.gui;

import frogcraftrebirth.common.gui.ContainerAdvBlastFurnace;
import frogcraftrebirth.common.tile.TileAdvBlastFurnace;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdvBlastFurnace extends GuiTileFrog<TileAdvBlastFurnace, ContainerAdvBlastFurnace> {

	public GuiAdvBlastFurnace(InventoryPlayer playerInv, TileAdvBlastFurnace tile) {
		super(new ContainerAdvBlastFurnace(playerInv, tile), tile, "gui_adv_blast_furnace.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.mc.getTextureManager().bindTexture(guiBackground);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		this.renderFluidTank(tile.inputFluid, 9, 22, 16, 47);
		this.renderFluidTank(tile.shieldGas,153, 22, 16, 47);
	}
}

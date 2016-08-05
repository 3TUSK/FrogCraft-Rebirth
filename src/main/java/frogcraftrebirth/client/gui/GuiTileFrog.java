/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 3:13:40 PM, Aug 5, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.client.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.tile.TileFrog;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiTileFrog<T extends TileFrog, C extends ContainerTileFrog<T>> extends GuiContainer {

	public static final int GRAY_40 = 0x404040;
	public static final int GREEN_3E = 0x20EB3E;
	
	protected final T tile;
	protected final ResourceLocation guiBackground;
	
	protected GuiTileFrog(C inventorySlotsIn, T tile, String textureName) {
		super(inventorySlotsIn);
		this.tile = tile;
		this.guiBackground = new ResourceLocation(FrogAPI.MODID, "textures/gui/" + textureName);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiBackground);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	/**
	 * Draw a fluid tank with given parameters. If the given tank is null, it will does nothing.
	 * @param tank The {@link IFluidTank} instance, will keep unmodified
	 * @param x The starting x-coordinate of tank
	 * @param y The starting y-coordinate of tank
	 * @param tankWidth The fluid tank full width in the GUI texture
	 * @param tankHeight The fluid tank full height in the GUI texture
	 */
	public void renderFluidTank(final IFluidTank tank, final int x, final int y, final int tankWidth, final int tankHeight) {
		if (tank == null || tank.getFluid() == null)
			return;
		
		TextureAtlasSprite fluidSprite = this.mc.getTextureMapBlocks().getAtlasSprite(tank.getFluid().getFluid().getStill().toString());
		if (fluidSprite == null) { //Fall back to purple-black
			fluidSprite = this.mc.getTextureMapBlocks().getMissingSprite();
		}
		
		this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		
		int scaledHeight = tankHeight * tank.getFluidAmount() / tank.getCapacity();
		this.drawTexturedModalRect(x, y + tankHeight - scaledHeight, fluidSprite, tankWidth, scaledHeight);
	}
	
	/**
	 * Draw fluid tooltip on given coordinate
	 * @param tank The {@link IFluidTank} instance, will keep unmodified
	 * @param x The {@link GuiContainer#drawGuiContainerForegroundLayer mouseX}
	 * @param y The {@link GuiContainer#drawGuiContainerForegroundLayer mouseY}
	 */
	public void renderFluidTankTooltip(final IFluidTank tank, final int x, final int y) {
		FluidStack stack = tank.getFluid();
		if (stack != null) {
			String name = stack.getLocalizedName();
			int amount = stack.amount;
			String[] info = new String[] {I18n.format("gui.fluid.name", name), I18n.format("gui.fluid.amount", amount)};
			this.drawHoveringText(Arrays.asList(info), x - guiLeft, y - guiTop);
		} else {
			this.drawHoveringText(Arrays.asList(I18n.format("gui.fluid.null")), x - guiLeft, y - guiTop);
		}
	}

}

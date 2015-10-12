package frogcraftrewrite.client.render;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.common.tile.TileACWindmillTurbine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderACWindmillRotor extends TileEntitySpecialRenderer{
	
	TileACWindmillTurbine tile;

	@Override
	public void renderTileEntityAt(TileEntity tile, double relativeX, double relativeY, double relativeZ, float partialTick) {
		this.tile = (TileACWindmillTurbine)tile;
		this.bindTexture(new ResourceLocation("frogcraftrewrite:model/acwindmillrotor"));
		
		@SuppressWarnings("unused") //Will be used later.
		Tessellator t = Tessellator.instance;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)relativeX, (float)relativeY, (float)relativeZ);
		
		//TODO: Actual render. Basically binding the texture to proper side, also wrenching is a big deal
		
		GL11.glPopMatrix();
	}

}

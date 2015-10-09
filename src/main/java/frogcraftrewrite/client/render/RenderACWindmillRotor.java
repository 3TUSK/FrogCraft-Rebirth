package frogcraftrewrite.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderACWindmillRotor extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tile, double d1, double d2, double d3, float f) {
		// TODO Auto-generated method stub
		this.bindTexture(new ResourceLocation("frogcraftrewrite:model/acwindmillrotor"));
		// TODO What a heck on GL11!
		// Actually i don't know the deal...
	}

}

package frogcraftrebirth.client.render;

import org.lwjgl.opengl.GL11;

import frogcraftrebirth.common.tile.TileAcademyWindmillTurbine;
//import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderTileAcademyWindmillRotor extends TileEntitySpecialRenderer{
	@SuppressWarnings("unused")
	private TileAcademyWindmillTurbine tile;
	
	IModelCustom renderRotor = AdvancedModelLoader.loadModel(new ResourceLocation("frogcraftrebirth:model/acwindmillrotor.obj"));

	@Override
	public void renderTileEntityAt(TileEntity tile, double relativeX, double relativeY, double relativeZ, float partialTick) {
		this.tile = (TileAcademyWindmillTurbine)tile;
		this.bindTexture(new ResourceLocation("frogcraftrebirth:model/acwindmillrotor.png"));
		
		//@SuppressWarnings("unused") //Will be used later.
		//Tessellator t = Tessellator.instance;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)relativeX, (float)relativeY, (float)relativeZ);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		//wtf?
		renderRotor.renderAll();
		GL11.glPopMatrix();
	}

}

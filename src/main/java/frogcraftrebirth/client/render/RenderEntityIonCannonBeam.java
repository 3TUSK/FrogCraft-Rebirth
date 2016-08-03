package frogcraftrebirth.client.render;

import frogcraftrebirth.common.entity.EntityIonCannonBeam;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEntityIonCannonBeam extends Render<EntityIonCannonBeam> {

	protected RenderEntityIonCannonBeam(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityIonCannonBeam entity, double d1, double d2, double d3, float f1, float f2) {
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityIonCannonBeam entity) {
		return null;
	}

}

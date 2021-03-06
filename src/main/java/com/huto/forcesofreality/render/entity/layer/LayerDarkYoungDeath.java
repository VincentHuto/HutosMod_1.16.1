package com.huto.forcesofreality.render.entity.layer;

import com.huto.forcesofreality.ForcesOfReality;
import com.huto.forcesofreality.entity.guardian.EntityDarkYoung;
import com.huto.forcesofreality.model.entity.guardian.ModelDarkYoung;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class LayerDarkYoungDeath extends LayerRenderer<EntityDarkYoung, ModelDarkYoung> {

	public static final ResourceLocation GLASSTEXTURE = new ResourceLocation(ForcesOfReality.MOD_ID,
			"textures/blocks/end_portal_circle.png");

	public LayerDarkYoungDeath(IEntityRenderer<EntityDarkYoung, ModelDarkYoung> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrix, IRenderTypeBuffer buf, int packedLight, EntityDarkYoung entity,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {

		// Chest Panel
		float d = entity.deathTicks;
		float scale = d * 0.005f + 0.8f;
		if (d > 0) {
			matrix.push();
			// matrix.scale(scale, scale, scale);
			matrix.translate(0, 1.5, 0);
			matrix.translate(0, -d * 0.01, 0);
			matrix.scale(1, 1, 1);
			if (scale < 5) {
				scale += 0.08f;
				matrix.scale(scale, scale, scale);
			}
			matrix.rotate(Vector3f.YP.rotation(d * scale * 0.3f));
			IVertexBuilder builder = buf.getBuffer(RenderType.getEntityTranslucent(GLASSTEXTURE));
			int color = 0xB6B900;
			int r = color >> 16 & 255, g = color & 255, b = color >> 16 & 255;
			builder.pos(matrix.getLast().getMatrix(), 1f, 0f, 1).color(r, g, b, 255).tex(1, 1)
					.overlay(OverlayTexture.NO_OVERLAY).lightmap(packedLight)
					.normal(matrix.getLast().getNormal(), 0, 1, 0).endVertex();
			builder.pos(matrix.getLast().getMatrix(), 1f, 0, -1).color(r, g, b, 255).tex(1, 0)
					.overlay(OverlayTexture.NO_OVERLAY).lightmap(packedLight)
					.normal(matrix.getLast().getNormal(), 0, 0, 0).endVertex();
			builder.pos(matrix.getLast().getMatrix(), -1f, 0, -1).color(r, g, b, 255).tex(0, 0)
					.overlay(OverlayTexture.NO_OVERLAY).lightmap(packedLight)
					.normal(matrix.getLast().getNormal(), 0, 0, 0).endVertex();
			builder.pos(matrix.getLast().getMatrix(), -1f, 0, 1).color(r, g, b, 255).tex(0, 1)
					.overlay(OverlayTexture.NO_OVERLAY).lightmap(packedLight)
					.normal(matrix.getLast().getNormal(), 0, 0, 0).endVertex();

			matrix.pop();

			/*
			 * matrix.push(); matrix.translate(0, d * 0.01, 0); matrix.pop();
			 */

		}
	}

}
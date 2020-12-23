package com.huto.forces_of_reality.render.entity.projectile;

import javax.annotation.Nonnull;

import com.huto.forces_of_reality.ForcesOfReality;
import com.huto.forces_of_reality.entities.projectiles.EntityDreadRocket;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderDreadRocket extends EntityRenderer<EntityDreadRocket> {

	public RenderDreadRocket(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(EntityDreadRocket entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(@Nonnull EntityDreadRocket entity) {
		return new ResourceLocation(ForcesOfReality.MOD_ID + "textures/entity/tracker.png");
	}

}
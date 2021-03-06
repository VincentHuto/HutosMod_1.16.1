package com.huto.forcesofreality.render.entity.layer;

import com.huto.forcesofreality.ForcesOfReality;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class LayerPlayerAura extends LayerRenderer<PlayerEntity, PlayerModel<PlayerEntity>> {
	private static final ResourceLocation COLIN_ARMOR = new ResourceLocation(
			ForcesOfReality.MOD_ID + ":textures/entity/colin/colin_armor2.png");
	private final PlayerModel<PlayerEntity> colinModel = new PlayerModel<PlayerEntity>(1.0f, false);

	public LayerPlayerAura(IEntityRenderer<PlayerEntity, PlayerModel<PlayerEntity>> entityRendererIn) {
		super(entityRendererIn);
	}

	
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			PlayerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
			float f = (float) entitylivingbaseIn.ticksExisted + partialTicks;
			colinModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
			this.getEntityModel().copyModelAttributesTo(colinModel);
			float swirlSpeedMod = 0.01f;
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEnergySwirl(COLIN_ARMOR, f * swirlSpeedMod, f * 0.01F));
			colinModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
					headPitch);
			colinModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F,
					0.5F);
	}

}

package com.huto.hutosmod.init;

import com.huto.hutosmod.HutosMod;
import com.huto.hutosmod.entities.EntityColin;
import com.huto.hutosmod.entities.EntityDenizen;
import com.huto.hutosmod.entities.EntityDenizenSage;
import com.huto.hutosmod.entities.EntityDreamWalker;
import com.huto.hutosmod.entities.EntityHastur;
import com.huto.hutosmod.entities.EntityHasturSpawn;
import com.huto.hutosmod.entities.EntityIbis;
import com.huto.hutosmod.entities.EntityTentacle;
import com.huto.hutosmod.entities.projectiles.EntityStarStrike;
import com.huto.hutosmod.entities.projectiles.EntityTrackingOrb;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HutosMod.MOD_ID, bus = Bus.MOD)
public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			HutosMod.MOD_ID);

	public static final RegistryObject<EntityType<EntityIbis>> ibis = ENTITY_TYPES.register("ibis",
			() -> EntityType.Builder.<EntityIbis>create(EntityIbis::new, EntityClassification.MONSTER).size(0.4F, 0.7F)
					.build(new ResourceLocation(HutosMod.MOD_ID, "ibis").toString()));

	public static final RegistryObject<EntityType<EntityDreamWalker>> dream_walker = ENTITY_TYPES
			.register("dream_walker",
					() -> EntityType.Builder
							.<EntityDreamWalker>create(EntityDreamWalker::new, EntityClassification.CREATURE)
							.size(0.9f, 1.3f).build(new ResourceLocation(HutosMod.MOD_ID, "dream_walker").toString()));

	public static final RegistryObject<EntityType<EntityColin>> colin = ENTITY_TYPES.register("colin",
			() -> EntityType.Builder.<EntityColin>create(EntityColin::new, EntityClassification.CREATURE)
					.size(0.9f, 1.3f).build(new ResourceLocation(HutosMod.MOD_ID, "colin").toString()));

	public static final RegistryObject<EntityType<EntityDenizen>> denizen = ENTITY_TYPES.register("denizen",
			() -> EntityType.Builder.<EntityDenizen>create(EntityDenizen::new, EntityClassification.MONSTER)
					.size(0.9f, 1.3f).build(new ResourceLocation(HutosMod.MOD_ID, "denizen").toString()));

	public static final RegistryObject<EntityType<EntityDenizenSage>> denizen_sage = ENTITY_TYPES
			.register("denizen_sage",
					() -> EntityType.Builder
							.<EntityDenizenSage>create(EntityDenizenSage::new, EntityClassification.MONSTER)
							.size(0.9f, 1.3f).build(new ResourceLocation(HutosMod.MOD_ID, "denizen_sage").toString()));

	public static final RegistryObject<EntityType<EntityHastur>> hastur = ENTITY_TYPES.register("hastur",
			() -> EntityType.Builder.<EntityHastur>create(EntityHastur::new, EntityClassification.MONSTER)
					.size(0.9f, 1.3f).build(new ResourceLocation(HutosMod.MOD_ID, "hastur").toString()));

	public static final RegistryObject<EntityType<EntityTentacle>> tentacle = ENTITY_TYPES.register("tentacle",
			() -> EntityType.Builder.<EntityTentacle>create(EntityTentacle::new, EntityClassification.MONSTER)
					.size(0.4F, 0.7F).build(new ResourceLocation(HutosMod.MOD_ID, "tentacle").toString()));

	public static final RegistryObject<EntityType<EntityTrackingOrb>> tracking_orb = ENTITY_TYPES.register(
			"tracking_orb",
			() -> EntityType.Builder.<EntityTrackingOrb>create(EntityTrackingOrb::new, EntityClassification.MISC)
					.setTrackingRange(64).setUpdateInterval(12).setShouldReceiveVelocityUpdates(true).size(0.1F, 0.1F)
					.build(new ResourceLocation(HutosMod.MOD_ID, "tracking_orb").toString()));

	public static final RegistryObject<EntityType<EntityStarStrike>> star_strike = ENTITY_TYPES.register("star_strike",
			() -> EntityType.Builder.<EntityStarStrike>create(EntityStarStrike::new, EntityClassification.MISC)
					.setTrackingRange(64).setUpdateInterval(12).setShouldReceiveVelocityUpdates(true).size(0.1F, 0.1F)
					.build(new ResourceLocation(HutosMod.MOD_ID, "star_strike").toString()));

	public static final RegistryObject<EntityType<EntityHasturSpawn>> hastur_spawn = ENTITY_TYPES.register("hastur_spawn",
			() -> EntityType.Builder.<EntityHasturSpawn>create(EntityHasturSpawn::new, EntityClassification.MONSTER)
					.size(0.4F, 0.7F).build(new ResourceLocation(HutosMod.MOD_ID, "hastur_spawn").toString()));
	
	@SubscribeEvent
	public static void registerAttributes(final FMLCommonSetupEvent event) {
		GlobalEntityTypeAttributes.put(EntityInit.dream_walker.get(), EntityDreamWalker.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.colin.get(), EntityColin.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.hastur.get(), EntityHastur.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.denizen.get(), EntityDenizen.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.denizen_sage.get(), EntityDenizenSage.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.ibis.get(), EntityIbis.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.tentacle.get(), EntityTentacle.setAttributes().create());
		GlobalEntityTypeAttributes.put(EntityInit.hastur_spawn.get(), EntityHasturSpawn.setAttributes().create());

	}
}

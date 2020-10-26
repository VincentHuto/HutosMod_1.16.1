package com.huto.hutosmod.events;

import org.lwjgl.glfw.GLFW;

import com.huto.hutosmod.HutosMod;
import com.huto.hutosmod.containers.ContainerRuneBinder;
import com.huto.hutosmod.gui.GuiChiselStation;
import com.huto.hutosmod.gui.GuiRuneBinder;
import com.huto.hutosmod.gui.GuiVirtuousEnchanter;
import com.huto.hutosmod.gui.mindrunes.PlayerExpandedScreen;
import com.huto.hutosmod.init.ContainerInit;
import com.huto.hutosmod.init.EntityInit;
import com.huto.hutosmod.init.TileEntityInit;
import com.huto.hutosmod.render.entity.RenderBeastFromBeyond;
import com.huto.hutosmod.render.entity.RenderColin;
import com.huto.hutosmod.render.entity.RenderCorruptNote;
import com.huto.hutosmod.render.entity.RenderDenizen;
import com.huto.hutosmod.render.entity.RenderDenizenSage;
import com.huto.hutosmod.render.entity.RenderDreamWalker;
import com.huto.hutosmod.render.entity.RenderEldritchGrip;
import com.huto.hutosmod.render.entity.RenderHastur;
import com.huto.hutosmod.render.entity.RenderHasturClone;
import com.huto.hutosmod.render.entity.RenderHasturSpawn;
import com.huto.hutosmod.render.entity.RenderHolyFlare;
import com.huto.hutosmod.render.entity.RenderIbis;
import com.huto.hutosmod.render.entity.RenderJudgement;
import com.huto.hutosmod.render.entity.RenderPlayerTentacle;
import com.huto.hutosmod.render.entity.RenderSeraphim;
import com.huto.hutosmod.render.entity.RenderSlug;
import com.huto.hutosmod.render.entity.RenderStarStrike;
import com.huto.hutosmod.render.entity.RenderSummonedBeast;
import com.huto.hutosmod.render.entity.RenderTentacle;
import com.huto.hutosmod.render.entity.RenderThrone;
import com.huto.hutosmod.render.entity.RenderTrackingOrb;
import com.huto.hutosmod.render.entity.layer.ThermalLayerRender;
import com.huto.hutosmod.render.tile.RenderAbsorber;
import com.huto.hutosmod.render.tile.RenderCapacitor;
import com.huto.hutosmod.render.tile.RenderChiselStation;
import com.huto.hutosmod.render.tile.RenderHasturPylon;
import com.huto.hutosmod.render.tile.RenderIcoSphere;
import com.huto.hutosmod.render.tile.RenderKarmicAltar;
import com.huto.hutosmod.render.tile.RenderKarmicExtractor;
import com.huto.hutosmod.render.tile.RenderResonator;
import com.huto.hutosmod.render.tile.RenderRuneModStation;
import com.huto.hutosmod.render.tile.RenderStorageDrum;
import com.huto.hutosmod.render.tile.RenderThermalInfluxer;
import com.huto.hutosmod.render.tile.RenderVibeFuser;
import com.huto.hutosmod.render.tile.RenderVirtuousEnchanter;
import com.huto.hutosmod.render.tile.RenderWandMaker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HutosMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {

	public static NonNullList<KeyBinding> keyBinds = NonNullList.create();

	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {

		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		forgeBus.addListener(ThermalLayerRender::renderWorld);
		forgeBus.addListener(ThermalLayerRender::renderEntities);

		ClientRegistry.bindTileEntityRenderer(TileEntityInit.wand_maker.get(), RenderWandMaker::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibe_resonator.get(), RenderResonator::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.karmic_altar.get(), RenderKarmicAltar::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.karmic_extractor.get(), RenderKarmicExtractor::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.virtuous_enchanter.get(), RenderVirtuousEnchanter::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibratory_storage_drum.get(), RenderStorageDrum::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibratory_capacitor.get(), RenderCapacitor::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibratory_fuser.get(), RenderVibeFuser::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.vibe_absorber.get(), RenderAbsorber::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.hastur_pylon.get(), RenderHasturPylon::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.obj_icosahedron.get(), RenderIcoSphere::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.thermal_influxer.get(), RenderThermalInfluxer::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.runic_chisel_station.get(), RenderChiselStation::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.rune_mod_station.get(), RenderRuneModStation::new);

		ScreenManager.registerFactory(ContainerInit.virtuous_enchanter.get(), GuiVirtuousEnchanter::new);
		ScreenManager.registerFactory(ContainerInit.runic_chisel_station.get(), GuiChiselStation::new);
		ScreenManager.registerFactory(ContainerInit.PLAYER_RUNES, PlayerExpandedScreen::new);
		ScreenManager.registerFactory(ContainerRuneBinder.type, GuiRuneBinder::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.dream_walker.get(), RenderDreamWalker::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.colin.get(), RenderColin::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.hastur.get(), RenderHastur::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.denizen.get(), RenderDenizen::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.denizen_sage.get(), RenderDenizenSage::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.ibis.get(), RenderIbis::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.tentacle.get(), RenderTentacle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.player_tentacle.get(), RenderPlayerTentacle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.tracking_orb.get(), RenderTrackingOrb::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.star_strike.get(), RenderStarStrike::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.corrupt_note.get(), RenderCorruptNote::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.hastur_spawn.get(), RenderHasturSpawn::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.slug.get(), RenderSlug::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.seraphim.get(), RenderSeraphim::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.throne.get(), RenderThrone::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.judgement.get(), RenderJudgement::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.holy_flare.get(), RenderHolyFlare::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.eldritch_grip.get(), RenderEldritchGrip::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.hastur_clone.get(), RenderHasturClone::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.beast_from_beyond.get(),
				RenderBeastFromBeyond::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.summoned_beast.get(), RenderSummonedBeast::new);

		keyBinds.add(0, new KeyBinding("key.hutosmod.runebinderpickup.desc", GLFW.GLFW_KEY_B, "key.hutosmod.category"));
		ClientRegistry.registerKeyBinding(keyBinds.get(0));

	}



	public static PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}

	/*
	 * public void lightningFX(Vector3 vectorStart, Vector3 vectorEnd, float
	 * ticksPerMeter, long seed, int colorOuter, int colorInner) {
	 * Minecraft.getInstance().particles.addEffect(new
	 * FXLightning(Minecraft.getInstance().world, vectorStart, vectorEnd,
	 * ticksPerMeter, seed, colorOuter, colorInner)); }
	 * 
	 * public void lightningFX(Vector3 vectorStart, Vector3 vectorEnd, float
	 * ticksPerMeter, int colorOuter, int colorInner) { lightningFX(vectorStart,
	 * vectorEnd, ticksPerMeter, System.nanoTime(), colorOuter, colorInner); }
	 */

}

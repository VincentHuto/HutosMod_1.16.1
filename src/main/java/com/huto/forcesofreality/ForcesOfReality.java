package com.huto.forcesofreality;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huto.forcesofreality.capabilitie.covenant.CovenantEvents;
import com.huto.forcesofreality.capabilitie.tiledevotion.DevotionEvents;
import com.huto.forcesofreality.events.MechanGloveEvents;
import com.huto.forcesofreality.events.RenderLaserEvent;
import com.huto.forcesofreality.events.SparkDirectorModEvents;
import com.huto.forcesofreality.gui.pages.coven.CovenPageLib;
import com.huto.forcesofreality.init.BlockInit;
import com.huto.forcesofreality.init.CapabilityInit;
import com.huto.forcesofreality.init.ContainerInit;
import com.huto.forcesofreality.init.EnchantmentInit;
import com.huto.forcesofreality.init.EntityInit;
import com.huto.forcesofreality.init.FeatureInit;
import com.huto.forcesofreality.init.ItemInit;
import com.huto.forcesofreality.init.ParticleInit;
import com.huto.forcesofreality.init.TileEntityInit;
import com.huto.forcesofreality.item.coven.tool.ItemMechanGlove;
import com.huto.forcesofreality.network.PacketHandler;
import com.huto.forcesofreality.recipe.CopyMechanGloveDataRecipe;
import com.huto.forcesofreality.recipe.ModRafflesiaRecipies;
import com.huto.forcesofreality.recipe.UpgradeMachinaLampDataRecipe;
import com.huto.forcesofreality.render.entity.layer.AdornmentsRenderLayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod("forcesofreality")
@Mod.EventBusSubscriber(modid = ForcesOfReality.MOD_ID, bus = Bus.MOD)
public class ForcesOfReality {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "forcesofreality";
	public static ForcesOfReality instance;
	public static IProxy proxy = new IProxy() {
	};
	public static boolean hemosLoaded = false;

	@SuppressWarnings("deprecation")
	public ForcesOfReality() {
		DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> proxy = new ClientProxy());
		proxy.registerHandlers();
		hemosLoaded = ModList.get().isLoaded("hemomancy");
		instance = this;
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(this::clientSetup);
		ParticleInit.PARTICLE_TYPES.register(modEventBus);
		ItemInit.ITEMS.register(modEventBus);
		ItemInit.MODELEDITEMS.register(modEventBus);
		ItemInit.ADVITEMS.register(modEventBus);
		ItemInit.HANDHELDITEMS.register(modEventBus);
		ItemInit.SPAWNEGGS.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		BlockInit.SPECIALBLOCKS.register(modEventBus);
		TileEntityInit.TILES.register(modEventBus);
		ContainerInit.CONTAINERS.register(modEventBus);
		FeatureInit.FEATURES.register(modEventBus);
		EntityInit.ENTITY_TYPES.register(modEventBus);
		EnchantmentInit.ENCHANTS.register(modEventBus);
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.addListener(MechanGloveEvents::pickupEvent);
		MinecraftForge.EVENT_BUS.addListener(MechanGloveEvents::onClientTick);
		MinecraftForge.EVENT_BUS.addListener(SparkDirectorModEvents::onClientTick);

		// Register Capability Events
		MinecraftForge.EVENT_BUS.register(DevotionEvents.class);
		MinecraftForge.EVENT_BUS.register(CovenantEvents.class);

	}

	// Creative Tab
	public static class ForcesOfRealityItemGroup extends ItemGroup {
		public static final ForcesOfRealityItemGroup instance = new ForcesOfRealityItemGroup(ItemGroup.GROUPS.length,
				"forcesofrealitytab");

		public ForcesOfRealityItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockInit.untold_easel.get());
		}
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		// Automatically Registers BlockItems
		final IForgeRegistry<Item> registry = event.getRegistry();
		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			if (block == BlockInit.obj_icosahedron.get()) {
				final Item.Properties properties = new Item.Properties().group(ForcesOfRealityItemGroup.instance)
						.rarity(Rarity.EPIC);
				final BlockItem blockItem = new BlockItem(block, properties);
				blockItem.setRegistryName(block.getRegistryName());
				registry.register(blockItem);
			} else {
				final Item.Properties properties = new Item.Properties().group(ForcesOfRealityItemGroup.instance);
				final BlockItem blockItem = new BlockItem(block, properties);
				blockItem.setRegistryName(block.getRegistryName());
				registry.register(blockItem);
			}
		});
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		CapabilityInit.init();
		ModRafflesiaRecipies.init();
		PacketHandler.registerChannels();
		PacketHandler.registerMechanGloveChannels();

	}

	private void clientSetup(final FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(RenderLaserEvent.class);
		CovenPageLib.registerPages();
		this.addLayers();
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {

	}

	public void setupOnLoaded(FMLLoadCompleteEvent event) {

	}

	@SuppressWarnings("unused")
	private void enqueueIMC(final InterModEnqueueEvent event) {
	}

	@SuppressWarnings("unused")
	private void processIMC(final InterModProcessEvent event) {

	}

	// Adornment Layers
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@OnlyIn(Dist.CLIENT)
	private void addLayers() {
		Map<String, PlayerRenderer> skinMap = Minecraft.getInstance().getRenderManager().getSkinMap();
		PlayerRenderer render;
		render = skinMap.get("default");
		render.addLayer(new AdornmentsRenderLayer(render));
		render = skinMap.get("slim");
		render.addLayer(new AdornmentsRenderLayer(render));
	}

	public static ItemStack findMechanGlove(PlayerEntity player) {
		if (player.getHeldItemMainhand().getItem() instanceof ItemMechanGlove)
			return player.getHeldItemMainhand();
		if (player.getHeldItemOffhand().getItem() instanceof ItemMechanGlove)
			return player.getHeldItemOffhand();
		PlayerInventory inventory = player.inventory;
		for (int i = 0; i <= 35; i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if (stack.getItem() instanceof ItemMechanGlove)
				return stack;
		}
		return ItemStack.EMPTY;
	}

	public static ItemStack findMechanGloveInHand(PlayerEntity player) {
		ItemStack heldItem = player.getHeldItemMainhand();
		if (!(heldItem.getItem() instanceof ItemMechanGlove)) {
			heldItem = player.getHeldItemOffhand();
			if (!(heldItem.getItem() instanceof ItemMechanGlove)) {
				return ItemStack.EMPTY;
			}
		}
		return heldItem;
	}

	public static boolean isArmed(PlayerEntity entity) {
		return findMechanGloveInHand(entity).getItem() instanceof ItemMechanGlove;
	}

	@SubscribeEvent
	public static void onRecipeRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
		event.getRegistry().register(new CopyMechanGloveDataRecipe.Serializer()
				.setRegistryName(new ResourceLocation(MOD_ID, "mechan_glove_upgrade")));
		event.getRegistry().register(new UpgradeMachinaLampDataRecipe.Serializer()
				.setRegistryName(new ResourceLocation(MOD_ID, "machina_cage")));
	}

}

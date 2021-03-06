package com.huto.forcesofreality.init;

import com.huto.forcesofreality.ForcesOfReality;
import com.huto.forcesofreality.block.BlockAbyssalSilt;
import com.huto.forcesofreality.block.BlockAdornmentModStation;
import com.huto.forcesofreality.block.BlockBeyondFlame;
import com.huto.forcesofreality.block.BlockHasturPylon;
import com.huto.forcesofreality.block.BlockIcoSphere;
import com.huto.forcesofreality.block.BlockRafflesiaFlower;
import com.huto.forcesofreality.block.altar.BlockAscendantAltar;
import com.huto.forcesofreality.block.altar.BlockAuspiciousBundle;
import com.huto.forcesofreality.block.altar.BlockHunterEffigy;
import com.huto.forcesofreality.block.altar.BlockMachinaImperfecta;
import com.huto.forcesofreality.block.altar.BlockOccularHeap;
import com.huto.forcesofreality.block.altar.BlockSacrificePyre;
import com.huto.forcesofreality.block.altar.BlockUntoldEasel;
import com.huto.forcesofreality.block.bonsai.BlockBonsaiPlanter;
import com.huto.forcesofreality.block.bonsai.BlockJungleBonsai;
import com.huto.forcesofreality.block.bonsai.BlockMushroomBonsai;
import com.huto.forcesofreality.block.bonsai.BlockOakBonsai;
import com.huto.forcesofreality.block.bonsai.BlockSpruceBonsai;
import com.huto.forcesofreality.block.util.EnumBonsaiTypes;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ForcesOfReality.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			ForcesOfReality.MOD_ID);
	public static final DeferredRegister<Block> SPECIALBLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			ForcesOfReality.MOD_ID);

	// Random
	public static final RegistryObject<Block> anointed_iron_block = BLOCKS.register("anointed_iron_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5f, 15f).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.ANVIL)));
	// Hastur
	public static final RegistryObject<Block> hastur_pylon = BLOCKS.register("hastur_pylon",
			() -> new BlockHasturPylon(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.7f, 15f)
					.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).notSolid()));
	public static final RegistryObject<Block> hastur_stone_core = BLOCKS.register("hastur_stone_core",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.7f, 15f).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
	public static final RegistryObject<Block> hastur_stone = BLOCKS.register("hastur_stone",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.7f, 15f).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));

	public static final RegistryObject<Block> hastur_stone_smooth = BLOCKS.register("hastur_stone_smooth",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.7f, 15f).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));

	public static final RegistryObject<Block> untold_easel = BLOCKS.register("untold_easel",
			() -> new BlockUntoldEasel(Block.Properties.create(Material.WOOD).hardnessAndResistance(0.7f, 15f)
					.harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.WOOD).notSolid()));
	// Seraph
	public static final RegistryObject<Block> gilded_wool = BLOCKS.register("gilded_wool",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.7f, 15f).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ascendant_altar = BLOCKS.register("ascendant_altar",
			() -> new BlockAscendantAltar(
					Block.Properties.create(Material.ROCK).hardnessAndResistance(50f, 1500f).sound(SoundType.STONE)));
	public static final RegistryObject<Block> anointing_focus = BLOCKS.register("anointing_focus",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.7f, 15f).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));

	// Eldritch
	public static final RegistryObject<Block> occular_heap = BLOCKS.register("occular_heap", () -> new BlockOccularHeap(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(50f, 1500f).sound(SoundType.STONE)));
	public static final RegistryObject<Block> abyssal_silt = BLOCKS.register("abyssal_silt",
			() -> new BlockAbyssalSilt(Block.Properties.from(Blocks.SAND)));
	public static final RegistryObject<Block> beyond_flames = BLOCKS.register("beyond_flames",
			() -> new BlockBeyondFlame(Block.Properties.from(Blocks.FIRE), 1.5f));

	// Beast
	public static final RegistryObject<Block> auspicious_bundle = BLOCKS.register("auspicious_bundle",
			() -> new BlockAuspiciousBundle(Block.Properties.create(Material.WOOD).hardnessAndResistance(50f, 1500f)
					.sound(SoundType.WOOD).notSolid()));
	public static final RegistryObject<Block> sacrificial_pyre = BLOCKS.register("sacrificial_pyre",
			() -> new BlockSacrificePyre(Block.Properties.create(Material.WOOD).hardnessAndResistance(50f, 1500f)
					.sound(SoundType.WOOD).notSolid()));

	public static final RegistryObject<Block> hunter_effigy = BLOCKS.register("hunter_effigy",
			() -> new BlockHunterEffigy(Block.Properties.create(Material.ROCK).hardnessAndResistance(50f, 1500f)
					.sound(SoundType.STONE).notSolid()));

	// Machine
	public static final RegistryObject<Block> auric_block = BLOCKS.register("auric_block", () -> new Block(
			Block.Properties.create(Material.IRON).hardnessAndResistance(50f, 2000f).sound(SoundType.STONE)));
	public static final RegistryObject<Block> machine_glass = BLOCKS.register("machine_glass", () -> new GlassBlock(
			Block.Properties.create(Material.GLASS).hardnessAndResistance(0.1f, 1f).sound(SoundType.GLASS).notSolid()));
	public static final RegistryObject<Block> machina_imperfecta = BLOCKS.register("machina_imperfecta",
			() -> new BlockMachinaImperfecta(Block.Properties.create(Material.IRON).hardnessAndResistance(50f, 1500f)
					.sound(SoundType.ANVIL).notSolid()));

	// Bonsais
	public static final RegistryObject<Block> bonsai_planter = BLOCKS.register("bonsai_planter",
			() -> new BlockBonsaiPlanter(Block.Properties.create(Material.ROCK).hardnessAndResistance(50f, 1500f)
					.sound(SoundType.STONE).notSolid()));
	/*
	 * // Somnolent public static final RegistryObject<Block>
	 * somnolent_bonsai_stage_1 = BLOCKS .register("somnolent_bonsai_stage_1", () ->
	 * new BlockSomnolentBonsai(Block.Properties.create(Material.ROCK)
	 * .hardnessAndResistance(10f, 1500f).sound(SoundType.STONE).notSolid(),
	 * EnumBonsaiTypes.MYSTIC, 1)); public static final RegistryObject<Block>
	 * somnolent_bonsai_stage_2 = BLOCKS .register("somnolent_bonsai_stage_2", () ->
	 * new BlockSomnolentBonsai(Block.Properties.create(Material.ROCK)
	 * .hardnessAndResistance(10f, 1500f).sound(SoundType.STONE).notSolid(),
	 * EnumBonsaiTypes.MYSTIC, 2)); public static final RegistryObject<Block>
	 * somnolent_bonsai_stage_3 = BLOCKS .register("somnolent_bonsai_stage_3", () ->
	 * new BlockSomnolentBonsai(Block.Properties.create(Material.ROCK)
	 * .hardnessAndResistance(10f, 1500f).sound(SoundType.STONE).notSolid(),
	 * EnumBonsaiTypes.MYSTIC, 3)); // Anti public static final
	 * RegistryObject<Block> anti_bonsai_stage_1 =
	 * BLOCKS.register("anti_bonsai_stage_1", () -> new
	 * BlockAntiBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(
	 * 10f, 1500f) .sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.ANTI, 1));
	 * public static final RegistryObject<Block> anti_bonsai_stage_2 =
	 * BLOCKS.register("anti_bonsai_stage_2", () -> new
	 * BlockAntiBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(
	 * 10f, 1500f) .sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.ANTI, 2));
	 * public static final RegistryObject<Block> anti_bonsai_stage_3 =
	 * BLOCKS.register("anti_bonsai_stage_3", () -> new
	 * BlockAntiBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(
	 * 10f, 1500f) .sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.ANTI, 3));
	 */
	// Mushroom
	public static final RegistryObject<Block> mushroom_bonsai_stage_1 = BLOCKS
			.register("mushroom_bonsai_stage_1",
					() -> new BlockMushroomBonsai(Block.Properties.create(Material.ROCK)
							.hardnessAndResistance(50f, 1500f).sound(SoundType.STONE).notSolid(),
							EnumBonsaiTypes.MUSHROOM, 1));
	public static final RegistryObject<Block> mushroom_bonsai_stage_2 = BLOCKS
			.register("mushroom_bonsai_stage_2",
					() -> new BlockMushroomBonsai(Block.Properties.create(Material.ROCK)
							.hardnessAndResistance(50f, 1500f).sound(SoundType.STONE).notSolid(),
							EnumBonsaiTypes.MUSHROOM, 2));
	public static final RegistryObject<Block> mushroom_bonsai_stage_3 = BLOCKS
			.register("mushroom_bonsai_stage_3",
					() -> new BlockMushroomBonsai(Block.Properties.create(Material.ROCK)
							.hardnessAndResistance(50f, 1500f).sound(SoundType.STONE).notSolid(),
							EnumBonsaiTypes.MUSHROOM, 3));
	// Oak
	public static final RegistryObject<Block> oak_bonsai_stage_1 = BLOCKS.register("oak_bonsai_stage_1",
			() -> new BlockOakBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.OAK, 1));
	public static final RegistryObject<Block> oak_bonsai_stage_2 = BLOCKS.register("oak_bonsai_stage_2",
			() -> new BlockOakBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.OAK, 2));
	public static final RegistryObject<Block> oak_bonsai_stage_3 = BLOCKS.register("oak_bonsai_stage_3",
			() -> new BlockOakBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.OAK, 3));
	// Spruce
	public static final RegistryObject<Block> spruce_bonsai_stage_1 = BLOCKS.register("spruce_bonsai_stage_1",
			() -> new BlockSpruceBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.SPRUCE, 1));
	public static final RegistryObject<Block> spruce_bonsai_stage_2 = BLOCKS.register("spruce_bonsai_stage_2",
			() -> new BlockSpruceBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.SPRUCE, 2));
	public static final RegistryObject<Block> spruce_bonsai_stage_3 = BLOCKS.register("spruce_bonsai_stage_3",
			() -> new BlockSpruceBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.SPRUCE, 3));
	// Jungle
	public static final RegistryObject<Block> jungle_bonsai_stage_1 = BLOCKS.register("jungle_bonsai_stage_1",
			() -> new BlockJungleBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.JUNGLE, 1));
	public static final RegistryObject<Block> jungle_bonsai_stage_2 = BLOCKS.register("jungle_bonsai_stage_2",
			() -> new BlockJungleBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.JUNGLE, 2));
	public static final RegistryObject<Block> jungle_bonsai_stage_3 = BLOCKS.register("jungle_bonsai_stage_3",
			() -> new BlockJungleBonsai(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 1500f)
					.sound(SoundType.STONE).notSolid(), EnumBonsaiTypes.JUNGLE, 3));

	// Misc
	public static final RegistryObject<Block> rafflesia_flower = BLOCKS.register("rafflesia_flower",
			() -> new BlockRafflesiaFlower(Block.Properties.create(Material.PLANTS).doesNotBlockMovement()
					.tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));

	// Tiles

	public static final RegistryObject<Block> rafflesia_of_fidelity = BLOCKS.register("rafflesia_of_fidelity",
			() -> new BlockRafflesiaFlower(
					Block.Properties.create(Material.ROCK).hardnessAndResistance(50f, 1500f).sound(SoundType.STONE)));
	// Misc
	public static final RegistryObject<Block> self_reflection_station = BLOCKS.register("self_reflection_station",
			() -> new BlockAdornmentModStation(
					Block.Properties.create(Material.ROCK).hardnessAndResistance(50f, 1500f).sound(SoundType.STONE)));

	// OBJ
	public static final RegistryObject<Block> obj_icosahedron = BLOCKS.register("obj_icosahedron",
			() -> new BlockIcoSphere(Block.Properties.create(Material.ROCK).hardnessAndResistance(50f, 1500f)
					.sound(SoundType.STONE).notSolid()));
	/*
	 * public static final RegistryObject<Block> end_crystal_somnolent =
	 * BLOCKS.register("end_crystal_somnolent", () -> new
	 * BlockCrystalObj(Block.Properties.create(Material.ROCK).hardnessAndResistance(
	 * 50f, 1500f) .sound(SoundType.STONE).notSolid())); public static final
	 * RegistryObject<Block> end_crystal_nightmare =
	 * BLOCKS.register("end_crystal_nightmare", () -> new
	 * BlockCrystalObj(Block.Properties.create(Material.ROCK).hardnessAndResistance(
	 * 50f, 1500f) .sound(SoundType.STONE).notSolid())); public static final
	 * RegistryObject<Block> raw_hematite = BLOCKS.register("raw_hematite", () ->
	 * new
	 * BlockCrystalObj(Block.Properties.create(Material.ROCK).hardnessAndResistance(
	 * 50f, 1500f) .sound(SoundType.STONE).notSolid()));
	 */

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			RenderTypeLookup.setRenderLayer(BlockInit.rafflesia_flower.get(), RenderType.getCutout());
			RenderTypeLookup.setRenderLayer(BlockInit.obj_icosahedron.get(), RenderType.getCutoutMipped());
			/*
			 * RenderTypeLookup.setRenderLayer(BlockInit.end_crystal_somnolent.get(),
			 * RenderType.getCutout());
			 * RenderTypeLookup.setRenderLayer(BlockInit.end_crystal_nightmare.get(),
			 * RenderType.getCutout());
			 * RenderTypeLookup.setRenderLayer(BlockInit.raw_hematite.get(),
			 * RenderType.getCutout());
			 */
			RenderTypeLookup.setRenderLayer(BlockInit.sacrificial_pyre.get(), RenderType.getCutout());
			RenderTypeLookup.setRenderLayer(BlockInit.auspicious_bundle.get(), RenderType.getCutout());
			RenderTypeLookup.setRenderLayer(BlockInit.machina_imperfecta.get(), RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(BlockInit.machine_glass.get(), RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(BlockInit.occular_heap.get(), RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(BlockInit.untold_easel.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.bonsai_planter.get(), RenderType.getCutoutMipped());

			// RenderTypeLookup.setRenderLayer(BlockInit.anti_bonsai_stage_1.get(),
			// RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.mushroom_bonsai_stage_1.get(), RenderType.getCutoutMipped());
			// RenderTypeLookup.setRenderLayer(BlockInit.somnolent_bonsai_stage_1.get(),
			// RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.oak_bonsai_stage_1.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.jungle_bonsai_stage_1.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.spruce_bonsai_stage_1.get(), RenderType.getCutoutMipped());

			// RenderTypeLookup.setRenderLayer(BlockInit.anti_bonsai_stage_2.get(),
			// RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.mushroom_bonsai_stage_2.get(), RenderType.getCutoutMipped());
			// RenderTypeLookup.setRenderLayer(BlockInit.somnolent_bonsai_stage_2.get(),
			// RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.oak_bonsai_stage_2.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.jungle_bonsai_stage_2.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.spruce_bonsai_stage_2.get(), RenderType.getCutoutMipped());

			// RenderTypeLookup.setRenderLayer(BlockInit.anti_bonsai_stage_3.get(),
			// RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.mushroom_bonsai_stage_3.get(), RenderType.getCutoutMipped());
			// RenderTypeLookup.setRenderLayer(BlockInit.somnolent_bonsai_stage_3.get(),
			// RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.oak_bonsai_stage_3.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.jungle_bonsai_stage_3.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.spruce_bonsai_stage_3.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(BlockInit.hunter_effigy.get(), RenderType.getCutoutMipped());

			RenderTypeLookup.setRenderLayer(BlockInit.beyond_flames.get(), RenderType.getTranslucent());

		}
	}

}

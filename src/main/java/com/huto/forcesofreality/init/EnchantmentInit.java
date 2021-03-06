package com.huto.forcesofreality.init;

import com.huto.forcesofreality.ForcesOfReality;
import com.huto.forcesofreality.enchantment.EnchantmentBowBladeDamage;
import com.huto.forcesofreality.enchantment.EnchantmentBowBladeFireAspect;
import com.huto.forcesofreality.enchantment.EnchantmentBowBladeFlame;
import com.huto.forcesofreality.enchantment.EnchantmentBowBladeInfinity;
import com.huto.forcesofreality.enchantment.EnchantmentBowBladeKnockBack;
import com.huto.forcesofreality.enchantment.EnchantmentBowBladePower;
import com.huto.forcesofreality.enchantment.EnchantmentBowBladePunch;
import com.huto.forcesofreality.enchantment.EnchantmentInfluenceSuppression;
import com.huto.forcesofreality.item.ModItemPredicates;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ForcesOfReality.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class EnchantmentInit {
	public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
			ForcesOfReality.MOD_ID);

	public static final EnchantmentType BOWBLADE = EnchantmentType.create("BOWBLADE", ModItemPredicates.BOWBLADE);

	public static final RegistryObject<Enchantment> bow_blade_sharpness = ENCHANTS.register("bow_blade_sharpness",
			() -> new EnchantmentBowBladeDamage(Enchantment.Rarity.COMMON, 0, EquipmentSlotType.MAINHAND));
	public static final RegistryObject<Enchantment> bow_blade_smite = ENCHANTS.register("bow_blade_smite",
			() -> new EnchantmentBowBladeDamage(Enchantment.Rarity.UNCOMMON, 1, EquipmentSlotType.MAINHAND));
	public static final RegistryObject<Enchantment> bow_blade_bane_of_arthropods = ENCHANTS.register(
			"bow_blade_bane_of_arthropods",
			() -> new EnchantmentBowBladeDamage(Enchantment.Rarity.UNCOMMON, 2, EquipmentSlotType.MAINHAND));
	public static final RegistryObject<Enchantment> bow_blade_fire_aspect = ENCHANTS.register("bow_blade_fire_aspect",
			() -> new EnchantmentBowBladeFireAspect(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));
	public static final RegistryObject<Enchantment> bow_blade_knockback = ENCHANTS.register("bow_blade_knockback",
			() -> new EnchantmentBowBladeKnockBack(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));

	public static final RegistryObject<Enchantment> bow_blade_power = ENCHANTS.register("bow_blade_power",
			() -> new EnchantmentBowBladePower(Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND));

	public static final RegistryObject<Enchantment> bow_blade_flame = ENCHANTS.register("bow_blade_flame",
			() -> new EnchantmentBowBladeFlame(Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND));

	public static final RegistryObject<Enchantment> bow_blade_punch = ENCHANTS.register("bow_blade_punch",
			() -> new EnchantmentBowBladePunch(Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND));
	public static final RegistryObject<Enchantment> bow_blade_infinity = ENCHANTS.register("bow_blade_infinity",
			() -> new EnchantmentBowBladeInfinity(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));

	public static final RegistryObject<Enchantment> influence_suppression = ENCHANTS.register("influence_suppression",
			() -> new EnchantmentInfluenceSuppression(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.HEAD));
	
}

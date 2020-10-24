package com.huto.hutosmod.events;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.huto.hutosmod.HutosMod;
import com.huto.hutosmod.capabilities.mindrunes.RunesCapabilities;
import com.huto.hutosmod.capabilities.mindrunes.IRune;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = HutosMod.MOD_ID)
public class AttachCapability {

	private static ResourceLocation cap = new ResourceLocation(HutosMod.MOD_ID, "rune_cap");

	@SubscribeEvent
	public static void attachCaps(AttachCapabilitiesEvent<ItemStack> event) {
		ItemStack stack = event.getObject();
		if (stack.getItem() instanceof IRune && stack.getItem() != Items.AIR) {
			event.addCapability(cap, new ICapabilityProvider() {
				private final LazyOptional<IRune> opt = LazyOptional.of(() -> (IRune) stack.getItem());

				@Nonnull
				@Override
				public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
					return RunesCapabilities.ITEM_RUNE.orEmpty(cap, opt);
				}
			});
		}
	}
}

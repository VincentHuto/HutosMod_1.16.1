package com.huto.hutosmod.objects.items.tools;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.huto.hutosmod.HutosMod;
import com.huto.hutosmod.containers.ContainerMechanGlove;
import com.huto.hutosmod.containers.MechanGloveItemHandler;
import com.huto.hutosmod.font.ModTextFormatting;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemMechanGlove extends Item {
	String name;
	Integer size;
	Rarity rare;

	public static int selectedModule;
	public static String TAG_SELECTED = "selected";

	public ItemMechanGlove(Properties props, String name, Integer size, Rarity rarity) {
		super(props);
		this.name = name;
		this.size = size;
		this.rare = rarity;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return this.rare == Rarity.EPIC ? true : false;

	}

	public void setSelectedModule(int selectedModuleIn) {
		selectedModule = selectedModuleIn;
	}

	public int getSelectedModule() {
		return selectedModule;
	}

	@Override
	public boolean shouldSyncTag() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn.isRemote) {
			if (!playerIn.isSneaking()) {
				HutosMod.proxy.openMechanGui();
				playerIn.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 0.40f, 1F);
			}

		}
		if (!worldIn.isRemote) {
			if (playerIn.isSneaking()) {
				// open
				playerIn.openContainer(new INamedContainerProvider() {
					@Override
					public ITextComponent getDisplayName() {
						return playerIn.getHeldItem(handIn).getDisplayName();
					}

					@Nullable
					@Override
					public Container createMenu(int windowId, PlayerInventory p_createMenu_2_,
							PlayerEntity p_createMenu_3_) {
						return new ContainerMechanGlove(windowId, p_createMenu_3_.world, p_createMenu_3_.getPosition(),
								p_createMenu_2_, p_createMenu_3_);
					}
				});

			}
		}
		ItemStack stack = playerIn.getHeldItemMainhand();
		IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseThrow(IllegalArgumentException::new);

		ItemStack selectedModuleStack = handler.getStackInSlot(stack.getTag().getInt(TAG_SELECTED));
		System.out.println(selectedModuleStack);

		// NBT TAG
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));

	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if (worldIn != null) {
			IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
					.orElseThrow(IllegalArgumentException::new);

			if (handler != null) {
				tooltip.add(new StringTextComponent(
						TextFormatting.LIGHT_PURPLE + "Rarity: " + ModTextFormatting.toProperCase(rare.name())));
				tooltip.add(new StringTextComponent(TextFormatting.GREEN + "Size: " + size));
				ItemStack selectedModuleStack = handler.getStackInSlot(stack.getTag().getInt(TAG_SELECTED));
				if (stack.hasTag()) {
					tooltip.add(new TranslationTextComponent(
							TextFormatting.GOLD + "Selected Module: " + stack.getTag().getInt(TAG_SELECTED)));
					 handler.getStackInSlot(stack.getTag().getInt(TAG_SELECTED));
					tooltip.add(new TranslationTextComponent(TextFormatting.GOLD + "Selected Module: "
							+ I18n.format(selectedModuleStack.getTranslationKey())));
					/*
					 * if (selectedModuleStack.getItem() != null && selectedModuleStack.getItem()
					 * instanceof ItemMechanModuleBase) { tooltip.add(new TranslationTextComponent(
					 * TextFormatting.GOLD + "Module Tier: " + selectedModuleStack.getTag()));
					 * 
					 * }
					 */
				}
			}
		}
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new MechanGloveCaps(stack, size, nbt);
	}

	@SuppressWarnings("rawtypes")
	class MechanGloveCaps implements ICapabilitySerializable {
		public MechanGloveCaps(ItemStack stack, int size, CompoundNBT nbtIn) {
			itemStack = stack;
			this.size = size;
			inventory = new MechanGloveItemHandler(itemStack, size);
			optional = LazyOptional.of(() -> inventory);
		}

		@SuppressWarnings("unused")
		private int size;
		private ItemStack itemStack;
		private MechanGloveItemHandler inventory;
		private LazyOptional<IItemHandler> optional;

		@Nonnull
		@Override
		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
			if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
				return optional.cast();
			} else
				return LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			inventory.save();
			return new CompoundNBT();
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			inventory.load();
		}
	}
}
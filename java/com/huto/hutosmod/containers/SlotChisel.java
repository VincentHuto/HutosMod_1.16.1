package com.huto.hutosmod.containers;

import com.huto.hutosmod.objects.items.ItemKnapper;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotChisel extends Slot {

	public SlotChisel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() instanceof ItemKnapper) {
			return true;
		} else {
			return false;
		}
	}

}
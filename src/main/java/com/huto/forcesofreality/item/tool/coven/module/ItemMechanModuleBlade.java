package com.huto.forcesofreality.item.tool.coven.module;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemMechanModuleBlade extends ItemMechanModuleBase implements IModuleUse {

	public ItemMechanModuleBlade(Properties properties,int tier, String useTextIn) {
		super(properties,tier, useTextIn);
	}

	@Override
	public int getDamageCost() {
		return 2;
	}

	@Override
	public int getAllegianceChance() {
		return 5;
	}

	@Override
	public void use(PlayerEntity playerIn,Hand handIn, ItemStack itemStack, World worldIn) {
		if (!itemStack.getTag().getBoolean("swordstate")) {
			itemStack.getTag().putBoolean("swordstate", true);
		} else {
			itemStack.getTag().putBoolean("swordstate", false);
		}
	}


}

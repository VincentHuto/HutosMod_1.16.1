package com.huto.forcesofreality.item.coven.tool;

import com.huto.forcesofreality.entity.projectile.EntityFirstBeastBolt;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBeastBolt extends ArrowItem {

	public ItemBeastBolt(Properties builder) {
		super(builder);
	}

	@Override
	public EntityFirstBeastBolt createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		EntityFirstBeastBolt arrowentity = new EntityFirstBeastBolt(worldIn, shooter);
		arrowentity.setPotionEffect(stack);
		return arrowentity;
	}
}

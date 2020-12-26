package com.huto.forcesofreality.objects.blocks.bonsai;

import com.huto.forcesofreality.objects.blocks.util.EnumBonsaiTypes;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BlockSpruceBonsai extends BlockBonsai {
	public BlockSpruceBonsai(Properties properties, EnumBonsaiTypes type, int stage) {
		super(properties, type, stage);
	}

	@Override
	public Item getShearDrop() {
		return Items.SNOWBALL;
	}

}

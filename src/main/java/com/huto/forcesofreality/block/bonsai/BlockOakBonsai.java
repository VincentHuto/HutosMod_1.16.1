package com.huto.forcesofreality.block.bonsai;

import com.huto.forcesofreality.block.util.EnumBonsaiTypes;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BlockOakBonsai extends BlockBonsai {
	public BlockOakBonsai(Properties properties, EnumBonsaiTypes type,int stage) {
		super(properties, type,stage);
	}

	@Override
	public Item getShearDrop() {
		return Items.APPLE;
	}

}

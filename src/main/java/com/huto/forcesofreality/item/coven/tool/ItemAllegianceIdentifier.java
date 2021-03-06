package com.huto.forcesofreality.item.coven.tool;

import com.huto.forcesofreality.capabilitie.covenant.CovenantProvider;
import com.huto.forcesofreality.capabilitie.covenant.ICovenant;
import com.huto.forcesofreality.network.PacketHandler;
import com.huto.forcesofreality.network.coven.SyncCovenPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ItemAllegianceIdentifier extends Item {

	public ItemAllegianceIdentifier(Properties prop) {
		super(prop);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		if (!worldIn.isRemote) {
			ICovenant coven = playerIn.getCapability(CovenantProvider.COVEN_CAPA)
					.orElseThrow(IllegalStateException::new);

			playerIn.getCapability(CovenantProvider.COVEN_CAPA).ifPresent(covens -> {
				PacketHandler.sendCovenToClients(new SyncCovenPacket(covens.getDevotion(), playerIn.getEntityId()),
						playerIn);
			});

			playerIn.sendStatusMessage(new StringTextComponent(coven.getDevotion().toString()), true);
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}

package com.huto.forcesofreality.network.coven;

import java.util.function.Supplier;

import com.huto.forcesofreality.capabilitie.covenant.CovenantProvider;
import com.huto.forcesofreality.capabilitie.covenant.ICovenant;
import com.huto.forcesofreality.network.PacketHandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class CovenantUpdateMorphPacket {

	public CovenantUpdateMorphPacket() {

	}

	public static void handle(final CovenantUpdateMorphPacket msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ServerPlayerEntity sender = ctx.get().getSender(); // the client that sent this packet
			if (sender != null) {
				ICovenant covenant = sender.getCapability(CovenantProvider.COVEN_CAPA)
						.orElseThrow(IllegalStateException::new);
				PacketHandler.CHANNELCOVENANT.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> sender),
						new CovenantPacketServer(covenant.getDevotion()));
			}
		});
		ctx.get().setPacketHandled(true);
	}

	public static void encode(final CovenantUpdateMorphPacket msg, final PacketBuffer packetBuffer) {

	}

	public static CovenantUpdateMorphPacket decode(final PacketBuffer packetBuffer) {
		return new CovenantUpdateMorphPacket();
	}
}
package net.creeperdev.sleepDeprivation.figManager;


import net.minecraft.network.FriendlyByteBuf;

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;


public record FigPacket(
       int interval,
       int intervalRandomness,
       boolean modifyInventory,
       int stageThreshold1,
       int stageThreshold2,
       int stageThreshold3,
       int stageThreshold4,
       int stageThreshold5,
       boolean enableStageMessage,
       String stageMessage1,
       String stageMessage2,
       String stageMessage3,
       String stageMessage4,
       String stageMessage5,
       int inventorySwapMultiplier1,
       int inventorySwapMultiplier2,
       int inventorySwapMultiplier3,
       int inventorySwapMultiplier4,
       int inventorySwapMultiplier5,
       boolean includeHotbar,
       boolean modifyEffects,
       int potency1,
       int potency2,
       int potency3,
       int potency4,
       int potency5
) implements CustomPacketPayload {

    public static final Type<FigPacket> ID = new Type<>(Identifier.fromNamespaceAndPath("sleepdeprivation", "fig_packet"));

    public static final StreamCodec<FriendlyByteBuf, FigPacket> CODEC = StreamCodec.of(
            (buf, p) -> {
                buf.writeVarInt(p.interval);
                buf.writeVarInt(p.intervalRandomness);
                buf.writeBoolean(p.modifyInventory);
                buf.writeVarInt(p.stageThreshold1);
                buf.writeVarInt(p.stageThreshold2);
                buf.writeVarInt(p.stageThreshold3);
                buf.writeVarInt(p.stageThreshold4);
                buf.writeVarInt(p.stageThreshold5);
                buf.writeBoolean(p.enableStageMessage);
                buf.writeUtf(p.stageMessage1);
                buf.writeUtf(p.stageMessage2);
                buf.writeUtf(p.stageMessage3);
                buf.writeUtf(p.stageMessage4);
                buf.writeUtf(p.stageMessage5);
                buf.writeVarInt(p.inventorySwapMultiplier1);
                buf.writeVarInt(p.inventorySwapMultiplier2);
                buf.writeVarInt(p.inventorySwapMultiplier3);
                buf.writeVarInt(p.inventorySwapMultiplier4);
                buf.writeVarInt(p.inventorySwapMultiplier5);
                buf.writeBoolean(p.includeHotbar);
                buf.writeBoolean(p.modifyEffects);
                buf.writeVarInt(p.potency1);
                buf.writeVarInt(p.potency2);
                buf.writeVarInt(p.potency3);
                buf.writeVarInt(p.potency4);
                buf.writeVarInt(p.potency5);
            },
            buf -> new FigPacket(buf.readVarInt(), buf.readVarInt(), buf.readBoolean(),
                    buf.readVarInt(), buf.readVarInt(), buf.readVarInt(), buf.readVarInt(), buf.readVarInt(),
                    buf.readBoolean(),
                    buf.readUtf(), buf.readUtf(), buf.readUtf(), buf.readUtf(), buf.readUtf(),
                    buf.readVarInt(), buf.readVarInt(), buf.readVarInt(), buf.readVarInt(), buf.readVarInt(),
                    buf.readBoolean(), buf.readBoolean(),
                    buf.readVarInt(), buf.readVarInt(), buf.readVarInt(), buf.readVarInt(), buf.readVarInt())
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
package com.the_greatest_shaman;

import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.server.level.ServerPlayer;

public interface Listener {
    void setPlayer(ServerPlayer serverPlayer);
    void setPacket(ServerboundInteractPacket serverboundInteractPacket);
}

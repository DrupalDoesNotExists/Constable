package ru.dne.constable.protocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
import ru.dne.constable.Constable;
import ru.dne.constable.queue.Event;

/**
 * Packet interceptor for ProtocolLib
 */
public class ConstablePacketInterceptor extends PacketAdapter {

    public ConstablePacketInterceptor() {
        super(Constable.getConstable(),
                ListenerPriority.NORMAL,
                PacketType.Play.Client.USE_ENTITY);
    }

    @Override
    public void onPacketReceiving(@NotNull PacketEvent event) {
        PacketContainer packet = event.getPacket();
        EnumWrappers.EntityUseAction action = packet.getEntityUseActions().read(0);

        // Not attacking - do nothing
        if (action != EnumWrappers.EntityUseAction.ATTACK) return;

        Player player = event.getPlayer();
        int entityId = packet.getIntegers().read(0);

        // Get damaged entity and check if it's another player
        @Nullable Entity damaged = player.getNearbyEntities(5, 5, 5)
                .stream().filter(entity -> entity.getType() == EntityType.PLAYER)
                .filter(entity -> entity.getEntityId() == entityId)
                .findFirst().orElse(null);
        if (damaged == null) return;

        event.setCancelled(true);

        // Put event into queue
        Event queueEvent = new Event((LivingEntity) damaged,
                            player, 1.5d);
        Constable.getConstable().getEventQueue().add(queueEvent);
    }

}

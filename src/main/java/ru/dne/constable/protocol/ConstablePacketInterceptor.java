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

import ru.dne.constable.Constable;
import ru.dne.constable.queue.Event;

import java.util.UUID;

/**
 * Packet interceptor for ProtocolLib
 */
public class ConstablePacketInterceptor extends PacketAdapter {

    public ConstablePacketInterceptor() {
        super(Constable.getConstable(),
                ListenerPriority.NORMAL,
                PacketType.Play.Client.ENTITY_ACTION);
    }

    @Override
    public void onPacketReceiving(@NotNull PacketEvent event) {
        PacketContainer packet = event.getPacket();
        EnumWrappers.EntityUseAction action = packet.getEntityUseActions().read(0);

        // Not attacking - do nothing
        if (action != EnumWrappers.EntityUseAction.ATTACK) return;

        event.setCancelled(true);
        Player player = event.getPlayer();
        UUID entityId = packet.getUUIDs().read(0);

        // Get damaged entity and check if it's another player
        Entity damaged = player.getWorld().getEntity(entityId);
        if (damaged.getType() != EntityType.PLAYER) return;

        // Put event into queue
        Event queueEvent = new Event((LivingEntity) damaged,
                            player, 1.5d);
        Constable.getConstable().getEventQueue().add(queueEvent);
    }

}

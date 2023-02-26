package ru.dne.constable;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import ru.dne.constable.configuration.Configuration;
import ru.dne.constable.protocol.ConstablePacketInterceptor;
import ru.dne.constable.queue.EventQueue;
import ru.dne.constable.schedule.QueueHandlingTask;

@Getter
public final class Constable extends JavaPlugin {

    @Getter
    private static Constable constable;
    private final @NotNull EventQueue eventQueue = new EventQueue();

    @Override
    public void onEnable() {
        // Plugin startup logic

        constable = this;

        // Configuration
        saveDefaultConfig();

        if (Configuration.ENABLE_MERGING.bool()) {
            getLogger().info("Merging enabled");

            // ProtocolLib
            getLogger().info("Registering ProtocolLib listeners");
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
            protocolManager.addPacketListener(new ConstablePacketInterceptor());

            // Scheduling
            getLogger().info("Scheduling merging tasks");
            long threshold = (long) (Configuration.MERGING_THRESHOLD.duration().toMillis() / 50);
            new QueueHandlingTask().runTaskTimer(this, threshold, threshold);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

package ru.dne.constable;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.dne.constable.protocol.ConstablePacketInterceptor;
import ru.dne.constable.queue.EventQueue;

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

        // ProtocolLib
        getLogger().info("Registering ProtocolLib listeners");
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new ConstablePacketInterceptor());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

package ru.dne.constable;

import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;

public final class Constable extends JavaPlugin {

    @Getter
    private static Constable constable;

    @Override
    public void onEnable() {
        // Plugin startup logic

        constable = this;

        // Configuration
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

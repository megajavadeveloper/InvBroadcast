package ru.invstudio.invbroadcast.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

    private final Plugin plugin;
    private static FileConfiguration config;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadCfg() {
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
    }

    public static FileConfiguration getConfig() {
        return config;
    }
}

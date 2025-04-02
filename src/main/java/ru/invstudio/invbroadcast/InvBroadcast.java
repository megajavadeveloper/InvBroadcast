package ru.invstudio.invbroadcast;

import org.bukkit.plugin.java.JavaPlugin;
import ru.invstudio.invbroadcast.Utils.ColorManager;
import ru.invstudio.invbroadcast.Utils.ConfigManager;

public final class InvBroadcast extends JavaPlugin {

    private long lastBroadcastTime = 0;
    private long BroadcastDelay;
    private ConfigManager configManager;
    private Commands commandExecutor;

    @Override
    public void onEnable() {
        commandExecutor = new Commands(this);
        this.getCommand("broadcast").setExecutor(commandExecutor);
        this.getCommand("invbc").setExecutor(commandExecutor);

        BroadcastDelay = getConfig().getLong("delay", 600);
        configManager = new ConfigManager(this);
        configManager.loadCfg();

        getLogger().info(ColorManager.color("&b[InvBroadcast] Плагин успешно запущен!"));
        getLogger().info(ColorManager.color("&b[InvBroadcast] t.me/invstudio"));
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public long getLastBroadCastTime() {
        return lastBroadcastTime;
    }

    public long getBroadcastDelay() {
        return BroadcastDelay;
    }

    public void setLastBroadcastTime(long lastBroadcastTime) {
        this.lastBroadcastTime = lastBroadcastTime;
    }
    public void setBroadcastDelay(long broadcastDelay) {
        this.BroadcastDelay = BroadcastDelay;
    }
}

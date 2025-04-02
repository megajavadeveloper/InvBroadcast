package ru.invstudio.invbroadcast;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static ru.invstudio.invbroadcast.Utils.ColorManager.color;

public class Commands implements CommandExecutor {

    private final InvBroadcast plugin;

    public Commands(InvBroadcast plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String commandName = command.getName().toLowerCase();

        switch (commandName) {
            case "broadcast":
                return BroadCastCommand(sender, args);
            case "invbc":
                if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                    return ReloadCommand(sender);
                }
                if(args.length > 0 && args[0].equalsIgnoreCase("info")) {
                    return GetDelayBC(sender);
                }
                break;
        }
        return false;
    }

    private boolean BroadCastCommand(CommandSender sender, String[] args) {

        String prefix = plugin.getConfig().getString("prefix", "&b[InvBroadcast]");
        long delay = System.currentTimeMillis() / 1000;

        if (!sender.hasPermission("invbroadcast.use")) {
            sender.sendMessage(color(prefix + " " + plugin.getConfig().getString("message.noperm")));
        } else {
            if (!sender.hasPermission("invbroadcast.bypass") && (delay - plugin.getLastBroadCastTime()) < plugin.getBroadcastDelay()) {
                long timeLeft = plugin.getBroadcastDelay() - (delay - plugin.getLastBroadCastTime());
                sender.sendMessage(color(prefix + " " + plugin.getConfig().getString("message.delay").replace("%seconds%", String.valueOf(timeLeft))));
                return true;
            } else {
                String format = plugin.getConfig().getString("format");
                String message = format + " " + String.join(" ", args);

                if (sender instanceof Player) {
                    message = message.replace("%player%", sender.getName());
                } else {
                    message = message.replace("%player%", "&0Console");
                }

                if (args.length == 0) {
                    sender.sendMessage(color(prefix + " " + plugin.getConfig().getString("message.use")));
                } else {
                    for (Player player : plugin.getServer().getOnlinePlayers()) {
                        player.sendMessage(color(""));
                        player.sendMessage(color(message));
                        player.sendMessage(color(""));
                    }
                }
            }
            plugin.setLastBroadcastTime(delay);
            return true;
        }
        return true;
    }

    private boolean ReloadCommand(CommandSender sender){
        String prefix = plugin.getConfig().getString("prefix", "&b[InvBroadcast]");
        if (!sender.hasPermission("invbroadcast.admin")) {
            sender.sendMessage(color(prefix + " " + plugin.getConfig().getString("message.noperm")));
            return true;
        }

        plugin.reloadConfig();
        plugin.setBroadcastDelay(plugin.getConfig().getLong("delay", 600));
        sender.sendMessage(color("&b[InvBroadcast] успешно перезагружен!"));
        return true;
    }

    private boolean GetDelayBC(CommandSender sender) {
        sender.sendMessage(color(String.valueOf(plugin.getBroadcastDelay())));
        return true;
    }
}

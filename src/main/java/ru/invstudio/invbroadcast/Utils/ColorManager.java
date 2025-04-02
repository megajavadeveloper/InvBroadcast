package ru.invstudio.invbroadcast.Utils;

import org.bukkit.ChatColor;

public class ColorManager {

    public static String color(String message) {
        return ColorUtil.translateColorCodes(ChatColor.translateAlternateColorCodes('&', message));
    }
}

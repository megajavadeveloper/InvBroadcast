package ru.invstudio.invbroadcast.Utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {

    private static final Pattern hexPattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String translateColorCodes(String textToTranslate) {
        Matcher matcher = hexPattern.matcher(textToTranslate);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hexColor = matcher.group();
            matcher.appendReplacement(buffer, ChatColor.of(hexColor).toString());
        }
        return matcher.appendTail(buffer).toString();
    }
}

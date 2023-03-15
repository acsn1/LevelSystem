package org.acsn1.levelsystem.utils;

import org.bukkit.ChatColor;


public class ChatUtils {

    public static String translateColors(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String decimal(float a) {

        return String.format("%.2f", a);
    }

}

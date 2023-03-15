package org.acsn1.levelsystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerUtils {

    public static Player getPlayer(UUID uuid) {

        Player player = Bukkit.getPlayer(uuid);

        if(player == null) return null;

        return player;
    }

    public static UUID getUUID(Player player) {

        if(player != null) {
            return player.getUniqueId();
        } else{
            OfflinePlayer offline = Bukkit.getOfflinePlayer(player.getName());

            return offline.getUniqueId();
        }
    }

    public static UUID getUUID(String playerName) {
    Player player = Bukkit.getPlayer(playerName);

        if(player != null) {
            return player.getUniqueId();
        } else{
            OfflinePlayer offline = Bukkit.getOfflinePlayer(playerName);

            return offline.getUniqueId();
        }
    }


}

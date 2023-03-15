package org.acsn1.levelsystem.debug;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.objects.Profile;
import org.acsn1.levelsystem.utils.ChatUtils;
import org.acsn1.levelsystem.utils.Global;
import org.acsn1.levelsystem.utils.MessageUtils;
import org.acsn1.levelsystem.utils.PlayerUtils;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Debug {

    public static LevelSystem core = LevelSystem.getPlugin(LevelSystem.class);


    public static void warn(String input) {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.translateColors(MessageUtils.serverName + "&4&l[WARNING]&c " + input));
    }

    public static void promotedToNextLevel(UUID uuid) {
        if (core.getConfigManager().isNextLevelMessageEnabled()) {


            Profile profile = core.getProfileManager().getProfile(uuid);

            if (profile == null) return;

            int level = profile.getLevel();
            int oldLevel = level - 1;
            int prestige = profile.getPrestige();
            int oldPrestige = prestige - 1;

            if (level > Global.MAX_LEVEL) {

                PlayerUtils.getPlayer(uuid).sendMessage(ChatUtils.translateColors("&cYou have reached the maximum level!"));
                return;
            } else {

                PlayerUtils.getPlayer(uuid).sendMessage(ChatUtils.translateColors(core.getConfigManager().getNextLevel()
                        .replace("%level%", String.valueOf(level))
                        .replace("%old_level%", String.valueOf(oldLevel))
                        .replace("%prestige%", String.valueOf(prestige))
                        .replace("%oldprestige", String.valueOf(oldPrestige))
                        .replace("%player%", PlayerUtils.getPlayer(uuid).getName())
                ));
            }
        }
    }

    public static void promotedToNextPrestige(UUID uuid) {

        Profile profile = core.getProfileManager().getProfile(uuid);

        if(profile == null) return;

        int level = profile.getLevel();
        int oldLevel = level - 1;
        int prestige = profile.getPrestige();
        int oldPrestige = prestige - 1;

        if (prestige > Global.MAX_PRESTIGE) {

            PlayerUtils.getPlayer(uuid).sendMessage(ChatUtils.translateColors("&cYou have reached the maximum prestige!"));
            return;
        } else {

            if (core.getConfigManager().isNextPrestigeSelfMessageEnabled()) {

                PlayerUtils.getPlayer(uuid).sendMessage(ChatUtils.translateColors(core.getConfigManager().getNextPrestigeSelf()
                        .replace("%level%", String.valueOf(level))
                        .replace("%old_level%", String.valueOf(oldLevel))
                        .replace("%prestige%", String.valueOf(prestige))
                        .replace("%oldprestige", String.valueOf(oldPrestige))
                        .replace("%player%", PlayerUtils.getPlayer(uuid).getName())
                ));
            }


            if (core.getConfigManager().isNextPrestigeGlobalMessageEnabled()) {
                PlayerUtils.getPlayer(uuid).sendMessage(ChatUtils.translateColors(core.getConfigManager().getNextPrestigeGlobal()
                        .replace("%level%", String.valueOf(level))
                        .replace("%old_level%", String.valueOf(oldLevel))
                        .replace("%prestige%", String.valueOf(prestige))
                        .replace("%oldprestige", String.valueOf(oldPrestige))
                        .replace("%player%", PlayerUtils.getPlayer(uuid).getName())
                ));

            }
        }
    }



}

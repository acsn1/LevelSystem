package org.acsn1.levelsystem.commands;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.objects.Profile;
import org.acsn1.levelsystem.utils.ChatUtils;
import org.acsn1.levelsystem.utils.MessageUtils;
import org.acsn1.levelsystem.utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class ResetProfileCommand implements CommandExecutor {

    private LevelSystem core;
    public ResetProfileCommand(LevelSystem core) {
        this.core = core;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("levelsystem.profile.reset")) {
            if (args.length == 0) {

                sender.sendMessage(ChatUtils.translateColors("&cUsage: /resetprofile <target_name>"));
            }
            if(args.length == 1) {

                new BukkitRunnable() {

                    public void run() {

                        Profile profile = core.getProfileManager().getProfile(PlayerUtils.getUUID(args[0]));
                        if(profile == null) {
                            sender.sendMessage(ChatUtils.translateColors("&c" + args[0] + " does not have a profile!"));
                            cancel();
                        } else
                        {

                            core.getProfileManager().resetProfile(profile);
                            sender.sendMessage(ChatUtils.translateColors("&a" + args[0] + "'s profile has been reset!"));

                            cancel();
                        }

                    }

                }.runTaskTimer(core, 0, 2 * 20L);

            }
        } else {
            sender.sendMessage(ChatUtils.translateColors(MessageUtils.noPermission));
            return true;
        }

        return false;
    }
}

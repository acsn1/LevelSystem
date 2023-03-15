package org.acsn1.levelsystem.commands;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.objects.Profile;
import org.acsn1.levelsystem.utils.ChatUtils;
import org.acsn1.levelsystem.utils.Global;
import org.acsn1.levelsystem.utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ProfileCommand implements CommandExecutor {

    private LevelSystem core;
    public ProfileCommand(LevelSystem core) {
        this.core = core;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;



            if(!player.hasPermission("levelsystem.profile.others")) {

                Profile profile = core.getProfileManager().getProfile(player.getUniqueId());

                if(profile == null) {
                    player.sendMessage(ChatUtils.translateColors("&cYou currently have not a profile set. Contact an administrator to fix the issue."));
                    return true;
                }
                for(String output : core.getConfigManager().getProfileMessage()) {

                    player.sendMessage(ChatUtils.translateColors(output
                            .replace("%player%", player.getName())
                            .replace("%prestige%", String.valueOf(profile.getPrestige()))
                            .replace("%level%", String.valueOf(profile.getLevel()))
                            .replace("%experience%", ChatUtils.decimal(profile.getExperience()))
                            .replace("%max_experience%", String.valueOf(Global.MAX_EXPERIENCE))
                            .replace("%max_level%", String.valueOf(Global.MAX_LEVEL))
                            .replace("%max_prestige%", String.valueOf(Global.MAX_PRESTIGE))

                    ));

                }

            } else{

                if(args.length == 0) {

                    Profile profile = core.getProfileManager().getProfile(player.getUniqueId());

                    if(profile == null) {
                        player.sendMessage(ChatUtils.translateColors("&cYou currently have not a profile set. Contact an administrator to fix the issue."));
                        return true;
                    }
                    for(String output : core.getConfigManager().getProfileMessage()) {

                        player.sendMessage(ChatUtils.translateColors(output
                                .replace("%player%", player.getName())
                                .replace("%prestige%", String.valueOf(profile.getPrestige()))
                                .replace("%level%", String.valueOf(profile.getLevel()))
                                .replace("%experience%", ChatUtils.decimal(profile.getExperience()))
                                .replace("%max_experience%", String.valueOf(Global.MAX_EXPERIENCE))
                                .replace("%max_level%", String.valueOf(Global.MAX_LEVEL))
                                .replace("%max_prestige%", String.valueOf(Global.MAX_PRESTIGE))

                        ));

                    }

                }

                if(args.length == 1){

                    new BukkitRunnable() {

                        public void run() {

                            Profile target = core.getProfileManager().getProfile(PlayerUtils.getUUID(args[0]));

                            if (target == null) {
                                player.sendMessage(ChatUtils.translateColors("&c" + args[0] + " does not have a profile!"));
                                cancel();
                            } else {

                                for (String output : core.getConfigManager().getProfileMessage()) {

                                    player.sendMessage(ChatUtils.translateColors(output
                                            .replace("%player%", player.getName())
                                            .replace("%prestige%", String.valueOf(target.getPrestige()))
                                            .replace("%level%", String.valueOf(target.getLevel()))
                                            .replace("%experience%", ChatUtils.decimal(target.getExperience()))
                                            .replace("%max_experience%", String.valueOf(Global.MAX_EXPERIENCE))
                                            .replace("%max_level%", String.valueOf(Global.MAX_LEVEL))
                                            .replace("%max_prestige%", String.valueOf(Global.MAX_PRESTIGE))

                                    ));

                                }
                                cancel();
                            }
                        }

                    }.runTaskTimer(core, 0, 2 * 20L);

                }

            }

        }


        return false;
    }



}

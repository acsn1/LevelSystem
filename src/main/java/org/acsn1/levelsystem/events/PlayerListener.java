package org.acsn1.levelsystem.events;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.objects.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private LevelSystem core;
    public PlayerListener(LevelSystem core){ this.core = core; }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Profile profile = core.getProfileManager().getProfile(player.getUniqueId());

        if(profile == null) {
            Profile newProfile = new Profile(player.getUniqueId(), 0, 0, 0.0f);
            core.getProfileManager().createProfile(newProfile);
        }


    }

    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        Profile profile = core.getProfileManager().getProfile(player.getUniqueId());
        if(profile == null) return;

        core.getProfileManager().saveProfile(profile);

    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        Profile profile = core.getProfileManager().getProfile(player.getUniqueId());
        if(profile == null) return;


        event.setFormat(event.getFormat()
                .replace("%prestige%", String.valueOf(profile.getPrestige()))
                .replace("%level%", String.valueOf(profile.getLevel()))
        );
    }



}

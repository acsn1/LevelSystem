package org.acsn1.levelsystem.managers;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.events.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    private LevelSystem core;
    public EventManager(LevelSystem core) {
        this.core = core;
        addEvents();
    }

    private void addEvents() {

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerListener(core), core);


    }


}

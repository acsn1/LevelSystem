package org.acsn1.levelsystem.objects;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.debug.Debug;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class AutoSave {

    private LevelSystem core;

    public AutoSave(LevelSystem core) {
        this.core = core;

        if(core.getConfigManager().isAutoSaveEnabled()) {
            Debug.warn("&aAuto-saving is currently enabled! [LevelSystem]");

            new BukkitRunnable() {
                int countdown = core.getConfigManager().getAutoSaveDelay();

                public void run() {

                    if(countdown > 0) {
                        countdown--;
                    } else{


                        for (Profile profiles : core.getProfileManager().getProfiles()) {

                            File file = new File(core.getDataFolder() + "/data/", profiles.getUuid().toString() + ".yml");
                            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                            config.set("profile.uuid", profiles.getUuid().toString());
                            config.set("profile.prestige", profiles.getPrestige());
                            config.set("profile.level", profiles.getLevel());
                            config.set("profile.experience", profiles.getExperience());

                            try {
                                config.save(file);

                            } catch (Exception exception) {
                                Debug.warn("Failed to automatically save all live data! [LevelSystem]");
                            }

                        }
                        countdown = core.getConfigManager().getAutoSaveDelay();
                        Debug.warn("&aAll profiles were automatically saved! [LevelSystem]");

                    }
                }


            }.runTaskTimer(core, 30 * 20L, 20L);

        } else{
            Debug.warn("Auto-saving is currently disabled. It is advised that auto-save is enabled to prevent data loss! [LevelSystem]");
        }
    }

}

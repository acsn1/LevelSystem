package org.acsn1.levelsystem.managers;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.debug.Debug;
import org.acsn1.levelsystem.utils.ChatUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class ConfigManager {

    private File file;
    private YamlConfiguration config;
    private LevelSystem core;
    public ConfigManager(LevelSystem core) {

        this.core = core;
        init();
    }

    private void init() {

        file = new File(core.getDataFolder(), "config.yml");

        if(!file.exists()) {

            try{
                file.createNewFile();
            } catch(Exception exception) {
                Debug.warn("Could not create" + file.getName() + " at path:" + file.getPath());
            }

            core.saveResource("config.yml", true);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public void reload() {

        config = YamlConfiguration.loadConfiguration(file);
    }

    public String getNextLevel() {
        reload();
        return ChatUtils.translateColors(config.getString("next-level-message"));

    }

    public String getNextPrestigeSelf() {
        reload();
        return ChatUtils.translateColors(config.getString("next-prestige-message-self"));
    }

    public String getNextPrestigeGlobal() {
        reload();
        return ChatUtils.translateColors(config.getString("next-prestige-message-global"));

    }

    public boolean isNextLevelMessageEnabled() {
        reload();
        return config.getBoolean("next-level-message-send");
    }

    public boolean isNextPrestigeSelfMessageEnabled() {
        reload();
        return config.getBoolean("next-prestige-message-self-send");
    }

    public boolean isNextPrestigeGlobalMessageEnabled() {
        reload();
        return config.getBoolean("next-prestige-message-global-send");
    }

    public List<String> getProfileMessage() {
        reload();
        return config.getStringList("profile-message");
    }

    public float getMaxExperience() {
        reload();
        return (float) config.getDouble("max-experience");
    }

    public int getMaxLevel() {
        reload();
        return config.getInt("max-level");
    }

    public int getMaxPrestige() {
        reload();
        return config.getInt("max-prestige");

    }

    public boolean isAutoSaveEnabled() {
        reload();
        return config.getBoolean("autosave");
    }

    public int getAutoSaveDelay() {
        return config.getInt("autosave-delay");
    }




}

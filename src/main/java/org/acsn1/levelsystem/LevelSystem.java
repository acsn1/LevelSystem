package org.acsn1.levelsystem;

import org.acsn1.levelsystem.debug.Debug;
import org.acsn1.levelsystem.managers.CommandManager;
import org.acsn1.levelsystem.managers.ConfigManager;
import org.acsn1.levelsystem.managers.EventManager;
import org.acsn1.levelsystem.managers.ProfileManager;
import org.acsn1.levelsystem.objects.AutoSave;
import org.acsn1.levelsystem.objects.Profile;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelSystem extends JavaPlugin {

    private ConfigManager configManager;
    private ProfileManager profileManager;
    private EventManager eventManager;
    private CommandManager commandManager;
    private AutoSave autoSave;


    @Override
    public void onEnable() {

        Debug.warn("&aPlugin was enabled successfully! [LevelSystem]");

        configManager = new ConfigManager(this);
        profileManager = new ProfileManager(this);
        eventManager = new EventManager(this);
        commandManager = new CommandManager(this);

        autoSave = new AutoSave(this);

    }

    @Override
    public void onDisable() {

        // save all profiles on plugin disable
        for(Profile liveProfiles : getProfileManager().getProfiles()) {

            getProfileManager().saveProfile(liveProfiles);

        }

    }

    public ConfigManager getConfigManager() { return configManager; }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public CommandManager getCommandManager() { return commandManager; }

    public AutoSave getAutoSave() { return autoSave; }

}

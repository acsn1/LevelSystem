package org.acsn1.levelsystem.managers;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.debug.Debug;
import org.acsn1.levelsystem.objects.Profile;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ProfileManager {

    private Set<Profile> PROFILES = new HashSet<>();
    private LevelSystem core;
    public ProfileManager(LevelSystem core) {
        this.core = core;
        this.loadProfiles();
    }

    private void loadProfiles() {
        File file = new File(core.getDataFolder() + "/data/");
        if (!file.exists()) file.mkdirs();

        YamlConfiguration config;
        for (File fileProfiles : Objects.requireNonNull(file.listFiles())) {
            config = YamlConfiguration.loadConfiguration(fileProfiles);

            // Select variables
            UUID uuid = UUID.fromString(Objects.requireNonNull(config.getString("profile.uuid")));
            int prestige = config.getInt("profile.prestige");
            int level = config.getInt("profile.level");
            float experience = (float) config.getDouble("profile.experience");

            // Create profile object
            Profile profile = new Profile(uuid, prestige, level, experience);

            // Add profile to the set
            PROFILES.add(profile);

        }

    }

    public void createProfile(Profile profile) {

        if(profileExists(profile)) return;

        PROFILES.add(profile);
        saveProfile(profile);

    }

    public void saveProfile(Profile profile) {

        File file = new File(core.getDataFolder() + "/data/", profile.getUuid().toString() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("profile.uuid", profile.getUuid().toString());
        config.set("profile.prestige", profile.getPrestige());
        config.set("profile.level", profile.getLevel());
        config.set("profile.experience", profile.getExperience());

        try{
            config.save(file);
        } catch(Exception exception) {

            Debug.warn("Could not save profile for " + profile.getUuid().toString() + "!");
        }
    }


    public void resetProfile(Profile profile) {

        if(!(profileExists(profile))) return;

        File file = new File(core.getDataFolder() + "/data/", profile.getUuid().toString() + ".yml");

        if(file.exists()) file.delete();

        profile.setExperience(0.0f);
        profile.setLevel(0);
        profile.setPrestige(0);

        saveProfile(profile);

    }

    public Profile getProfile(UUID uuid) {

        Profile profile = null;

        for(Profile liveProfiles : PROFILES) {
            if(liveProfiles.getUuid().equals(uuid)) {
                profile = liveProfiles;
            }
        }
        return profile;
    }

    public boolean profileExists(Profile profile) {

        for(Profile liveProfiles : PROFILES) {
            if(liveProfiles.getUuid().equals(profile.getUuid())) {
                return true;
            }
        }
        return false;
    }


    public Set<Profile> getProfiles() {
        return PROFILES;
    }



}

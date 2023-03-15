package org.acsn1.levelsystem.objects;

import org.acsn1.levelsystem.debug.Debug;
import org.acsn1.levelsystem.utils.Global;

import java.util.UUID;

public class Profile {

    private final UUID uuid;
    private int prestige;
    private int level;
    private float experience;


    public Profile(UUID uuid, int prestige, int level, float experience) {
        this.uuid = uuid;
        this.prestige = prestige;
        this.level = level;
        this.experience = experience;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public boolean increaseExperience(float experience) {
        setExperience(getExperience()+Math.abs(experience));
        if(getExperience() >= Global.MAX_EXPERIENCE) {
            setExperience(0.0f);

            if(getLevel() < Global.MAX_LEVEL) {
                setLevel(getLevel()+1);
                Debug.promotedToNextLevel(uuid);
                return true;
            } else{
                if(getPrestige() < Global.MAX_PRESTIGE) {
                    setPrestige(getPrestige() + 1);
                    setLevel(0);
                    Debug.promotedToNextPrestige(uuid);
                    return true;
                }
            }

        }
        return false;
    }

    public void decreaseExperience(float experience) {
        setExperience(getExperience()-Math.abs(experience));
    }


}

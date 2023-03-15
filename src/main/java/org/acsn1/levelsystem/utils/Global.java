package org.acsn1.levelsystem.utils;

import org.acsn1.levelsystem.LevelSystem;

public class Global {

    public static LevelSystem core = LevelSystem.getPlugin(LevelSystem.class);

    public static int MAX_PRESTIGE = core.getConfigManager().getMaxPrestige();
    public static int MAX_LEVEL = core.getConfigManager().getMaxLevel();
    public static float MAX_EXPERIENCE = core.getConfigManager().getMaxExperience();

}

package org.acsn1.levelsystem.api;

import org.acsn1.levelsystem.LevelSystem;


public class LevelAPI {

    public static LevelSystem core = LevelSystem.getPlugin(LevelSystem.class);

    public static LevelSystem getInstance() {
        return core;
    }


}

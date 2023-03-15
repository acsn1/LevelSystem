package org.acsn1.levelsystem.managers;

import org.acsn1.levelsystem.LevelSystem;
import org.acsn1.levelsystem.commands.ProfileCommand;
import org.acsn1.levelsystem.commands.ResetProfileCommand;

public class CommandManager {

    private LevelSystem core;
    public CommandManager(LevelSystem core) {

        this.core = core;
        this.addCommands();
    }

    private void addCommands() {

        core.getCommand("profile").setExecutor(new ProfileCommand(core));
        core.getCommand("resetprofile").setExecutor(new ResetProfileCommand(core));

    }

}

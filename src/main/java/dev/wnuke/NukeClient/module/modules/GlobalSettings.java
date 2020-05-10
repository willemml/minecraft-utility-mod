package dev.wnuke.NukeClient.module.modules;

import dev.wnuke.NukeClient.module.Category;
import dev.wnuke.NukeClient.module.Module;

public class GlobalSettings extends Module {
    public GlobalSettings() {
        super("Global Settings", Category.HIDDEN, "This module is just here to hold all of the global settings.");
    }

    @Override
    public void selfSettings() {
        settings.addSetting("Command Prefix", "'");
    }
}

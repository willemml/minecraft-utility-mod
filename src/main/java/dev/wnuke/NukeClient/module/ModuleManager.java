package dev.wnuke.NukeClient.module;

import dev.wnuke.NukeClient.module.modules.LightningListener;

public class ModuleManager {
    public void register() {
        new LightningListener();
    }
}

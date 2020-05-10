package dev.wnuke.NukeClient.command.commands;

import dev.wnuke.NukeClient.command.Command;

import static dev.wnuke.NukeClient.NukeClient.SETTINGS_MANAGER;
import static dev.wnuke.NukeClient.util.ChatMessage.sendMessage;

public class ConfigReload extends Command {
    public ConfigReload() {
        super("Config reloader", new String[]{"reload"}, "Reloads the mod's config from file");
    }

    @Override
    public void call(String[] args) {
        SETTINGS_MANAGER.loadSettings();
        sendMessage("Config has been reloaded");
    }
}

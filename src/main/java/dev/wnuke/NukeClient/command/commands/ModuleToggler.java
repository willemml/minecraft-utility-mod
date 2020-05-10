package dev.wnuke.NukeClient.command.commands;

import dev.wnuke.NukeClient.command.Command;
import dev.wnuke.NukeClient.module.Module;

import java.util.Map;

import static dev.wnuke.NukeClient.NukeClient.MODULE_MANAGER;
import static dev.wnuke.NukeClient.util.ChatMessage.sendMessage;

public class ModuleToggler extends Command {
    public ModuleToggler() {
        super("Module toggler", new String[]{"toggle"}, "Toggles the specified module.");
    }

    @Override
    public void call(String[] args) {
        if (args[1].length() > 1 && !args[1].equals(" ")) {
            boolean moduleExists = false;
            for (Map.Entry<String, Module> module : MODULE_MANAGER.getModules().entrySet()) {
                if (module.getKey().replaceAll(" ", "").toLowerCase().equals(args[1].replaceAll(" ", "").toLowerCase())) {
                    module.getValue().toggle();
                    String enabledString = "off";
                    if (module.getValue().isEnabled()) {
                        enabledString = "on";
                    }
                    moduleExists = true;
                    sendMessage("Turned " + args[1] + " " + enabledString);
                    break;
                }
            }
            if (!moduleExists) {
                sendMessage("Module " + args[1] + " not found");
            }
        } else {
            sendMessage("Please specify a module name");
        }
    }
}

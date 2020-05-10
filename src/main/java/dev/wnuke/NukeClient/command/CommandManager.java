package dev.wnuke.NukeClient.command;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static dev.wnuke.NukeClient.NukeClient.MODULE_MANAGER;
import static dev.wnuke.NukeClient.NukeClient.SETTINGS_MANAGER;
import static dev.wnuke.NukeClient.util.ChatMessage.sendMessage;

public class CommandManager {
    public CommandManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void chatEvent(ClientChatEvent event) {
        String commandPrefix = (String) MODULE_MANAGER.getGlobalSettings().getSetting("Command Prefix");
        if (event.getMessage().startsWith(commandPrefix)) {
            // testing stuff, to be replaced
            if (event.getMessage().replaceFirst(commandPrefix, "").equals("reload")) {
                SETTINGS_MANAGER.loadSettings();
                sendMessage("Reloaded Settings.");
            } else {
                sendMessage("You sent something with the command prefix!");
                event.setCanceled(true);
            }
            event.setCanceled(true);
        }
    }
}

package dev.wnuke.NukeClient.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static dev.wnuke.NukeClient.NukeClient.MODULE_MANAGER;
import static dev.wnuke.NukeClient.util.ChatMessage.sendMessage;

public class CommandManager {
    Gson gson = new GsonBuilder().create();
    private Reflections reflections = new Reflections("dev.wnuke");
    public static Map<String, Command> commands = new HashMap<>();
    public CommandManager() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        commands.clear();

        // Find and register all commands
        Set<Class<? extends Command>> commandClasses = reflections.getSubTypesOf(Command.class);
        for (Class<? extends Command> commandClass : commandClasses) {
            Command command = commandClass.getConstructor().newInstance();
            commands.put(command.getName(), command);
        }
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void chatEvent(ClientChatEvent event) {
        String commandPrefix = (String) MODULE_MANAGER.getGlobalSettings().getSetting("Command Prefix");
        if (event.getMessage().startsWith(commandPrefix)) {
            boolean commandExists = false;
            if (event.getMessage().length() > 1) {
                String firstArg = event.getMessage().replaceFirst(commandPrefix, "").split(" ")[0];
                // regex by @dominikaaaa from KAMI Blue
                String[] argsList = event.getMessage().replaceFirst(commandPrefix + firstArg, "").split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Split by every space if it isn't surrounded by quotes
                for (Map.Entry<String, Command> command : commands.entrySet()) {
                    for (String alias : command.getValue().getAliases()) {
                        if (alias.equals(firstArg)) {
                            command.getValue().call(argsList);
                            commandExists = true;
                            break;
                        }
                    }
                    if (commandExists) {
                        break;
                    }
                }
                if (!commandExists) {
                    sendMessage("Command " + firstArg + " not found");
                }
            } else {
                sendMessage("Please enter a command after the prefix");
            }
            Minecraft.getInstance().ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
            event.setCanceled(true);
        }
    }
}

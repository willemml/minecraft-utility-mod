package dev.wnuke.NukeClient.command;

import dev.wnuke.NukeClient.NukeClient;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Logger;

public class Command {
    protected Minecraft mc = Minecraft.getInstance();
    protected Logger LOGGER = NukeClient.log;
    private String name;
    private String description;
    private String[] aliases;

    public Command(String name, String[] aliases, String description) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
    }

    public void call(String[] args) {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }
}

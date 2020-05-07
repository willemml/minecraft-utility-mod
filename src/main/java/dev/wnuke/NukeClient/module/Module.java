package dev.wnuke.NukeClient.module;

import dev.wnuke.NukeClient.NukeClient;
import dev.wnuke.NukeClient.util.ModuleSettings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

import static dev.wnuke.NukeClient.NukeClient.SETTINGS_MANAGER;

public class Module {
    protected Minecraft mc = Minecraft.getInstance();
    protected Logger LOGGER = NukeClient.log;
    private String name;
    private boolean enabled;
    private Category category;
    private String description;
    public ModuleSettings settings = new ModuleSettings();

    public Module(String name, Category category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
        enabled = false;
    }

    public void registerSettings() {
        settings.addSetting("enabled", isEnabled());
        selfSettings();
        LOGGER.info("Registered settings of " + this.getName());
    }

    public void selfSettings() {}

    public void onTick() {}

    @SubscribeEvent
    public void gameTickEvent(TickEvent event) {
        onTick();
    }

    public void onEnable() {
        for(Method method : getClass().getMethods()) {
            if (method.isAnnotationPresent(SubscribeEvent.class)) {
                MinecraftForge.EVENT_BUS.register(this);
                break;
            }
        }
        onEnabled();
        LOGGER.info("Enabled " + this.getName());
    }

    public void onDisable() {
        try{
            for(Method method : getClass().getMethods()) {
                if (method.isAnnotationPresent(SubscribeEvent.class)) {
                    MinecraftForge.EVENT_BUS.unregister(this);
                    break;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        onDisabled();
        LOGGER.info("Disabled " + this.getName());
    }

    public void onEnabled() {}

    public void onDisabled() {}

    public ModuleSettings getSettings() {
        return settings;
    }

    public void setSettings(ModuleSettings newSettings) {
        settings = newSettings;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getDesc() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }

    public void toggle() {
        setEnabled(!isEnabled());
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled) onEnable();
        else onDisable();
        settings.setSetting("enabled", enabled);
        SETTINGS_MANAGER.updateSettings();
    }
}

package dev.wnuke.NukeClient.module;

import dev.wnuke.NukeClient.util.ModuleSettings;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static dev.wnuke.NukeClient.NukeClient.SETTINGS_MANAGER;


public class ModuleManager {
    private Reflections reflections = new Reflections("dev.wnuke");
    public static Map<String, Module> modules = new HashMap<>();
    public void loadModules() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        modules.clear();

        // Find and register all modules
        Set<Class<? extends Module>> moduleClasses = reflections.getSubTypesOf(Module.class);
        for (Class<? extends Module> moduleClass : moduleClasses) {
            Module module = moduleClass.getConstructor().newInstance();
            modules.put(module.getName(), module);
        }

        // Load or create config
        SETTINGS_MANAGER.loadSettings();

        // Enable modules if their settings indicate they should be
        for (Map.Entry<String, Module> module : modules.entrySet()) {
            if ((boolean) module.getValue().getSettings().getSetting("enabled")) {
                module.getValue().onEnable();
            }
        }
    }
    public ModuleSettings getGlobalSettings() {
        return modules.get("Global Settings").getSettings();
    }

    public Map<String, Module> getModules() {
        return modules;
    }
}

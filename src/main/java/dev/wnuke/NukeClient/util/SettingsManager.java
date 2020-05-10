package dev.wnuke.NukeClient.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.wnuke.NukeClient.NukeClient;
import dev.wnuke.NukeClient.module.Module;
import dev.wnuke.NukeClient.module.ModuleManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static dev.wnuke.NukeClient.NukeClient.NUKE_CLIENT_CONFIG_FILE;

public class SettingsManager {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    File configFile = new File(NUKE_CLIENT_CONFIG_FILE);

    public Map<String, ModuleSettings> readSettings() {
        Map<String, ModuleSettings> settingsArray = new HashMap<>();
        if (configFile.exists() && configFile.isFile()) {
            try {
                NukeClient.log.info("Reading config file...");
                settingsArray = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, ModuleSettings>>(){}.getType());
                NukeClient.log.info("Successfully read config file.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                NukeClient.log.fatal("Could not read config file.");
            }
        } else {
            try {
                NukeClient.log.info("Creating config file...");
                FileWriter fw = new FileWriter(configFile);
                gson.toJson(settingsArray, fw);
                fw.flush();
                fw.close();
                NukeClient.log.info("Created config file.");
            } catch (IOException e) {
                e.printStackTrace();
                NukeClient.log.fatal("Could not create config file.");
            }
        }
        return settingsArray;
    }

    public void writeSettings(Map<String, ModuleSettings> settingsArray) {
        try {
            NukeClient.log.info("Writing config file");
            FileWriter fw = new FileWriter(configFile);
            gson.toJson(settingsArray, fw);
            fw.flush();
            fw.close();
            NukeClient.log.info("Wrote config file.");
        } catch (IOException e) {
            e.printStackTrace();
            NukeClient.log.fatal("Could not write config file.");
        }
    }

    public void updateSettings() {
        Map<String, ModuleSettings> settingsArray = new HashMap<>();
        for (Map.Entry<String, Module> module : ModuleManager.modules.entrySet()) {
            settingsArray.put(module.getKey(), module.getValue().getSettings());
        }
        writeSettings(settingsArray);
    }

    public void loadSettings() {
        Map<String, ModuleSettings> settingsArray = readSettings();
        for (Map.Entry<String, Module> module : ModuleManager.modules.entrySet()) {
            String moduleName = module.getValue().getName();
            try {
                if (settingsArray.containsKey(moduleName)) {
                    module.getValue().setSettings(settingsArray.get(moduleName));
                } else {
                    module.getValue().registerSettings();
                }
            } catch (NullPointerException npe) {
                module.getValue().registerSettings();
            }
        }
        updateSettings();
    }
}

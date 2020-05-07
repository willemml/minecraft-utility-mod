package dev.wnuke.NukeClient;

import dev.wnuke.NukeClient.module.ModuleManager;
import dev.wnuke.NukeClient.util.SettingsManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("nuke-client")
public class NukeClient {

    public static final String MODNAME = "NUKE Client";
    public static final String MODVER = "v1.0.0";

    public static final String NUKE_CLIENT_CONFIG_FILE = "NUKEClientConfig.json";

    public static final Logger log = LogManager.getLogger("NUKE Client");
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();
    public static final SettingsManager SETTINGS_MANAGER = new SettingsManager();
    public static EventBus eventBus;

    public NukeClient() {
        // Do register listeners for setup
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Load modules
        log.info("Initializing " + MODNAME + " " + MODVER);
        try {
            MODULE_MANAGER.loadModules();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException err) {
            log.fatal(err);
            log.warn("Failed to load at least one module.");
        }
        log.info(MODNAME + " initialized!");
    }
}

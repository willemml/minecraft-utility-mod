package dev.wnuke.NukeClient;

import com.google.common.eventbus.EventBus;
import dev.wnuke.NukeClient.module.ModuleManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("nuke-client")
public class NukeClient {

    public static final String MODNAME = "NUKE Client";
    public static final String MODVER = "v1.0.0";

    private static final String NUKE_CLIENT_CONFIG_FILE = "NUKEClientConfig.json";

    public static final Logger log = LogManager.getLogger("NUKE Client");
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();
    public static EventBus eventBus;

    public NukeClient() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        log.info("\n\nInitializing " + MODNAME + " " + MODVER);
        MODULE_MANAGER.register();
        log.info(MODNAME + " Mod initialized!\n");
    }
}

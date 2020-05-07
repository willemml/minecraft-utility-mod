package dev.wnuke.NukeClient.module.modules;

import dev.wnuke.NukeClient.module.Category;
import dev.wnuke.NukeClient.module.Module;
import net.minecraft.client.audio.ISound;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static dev.wnuke.NukeClient.util.ChatMessage.sendMessage;

public class LightningListener extends Module {
    public LightningListener() {
        super("Lightning Listener", Category.WORLD, "Listens for lightning strikes and logs the coordinates.");
    }

    @Override
    public void selfSettings() {
        this.settings.addSetting("logToFile", false);
        this.settings.addSetting("logToChat", true);
    }

    @SubscribeEvent
    public void onPlaySound(PlaySoundEvent event) {
        ISound sound = event.getSound();
        if (sound.getSoundLocation().getPath().equals("entity.lightning_bolt.thunder")) {
            if ((boolean) settings.getSetting("logToChat")){
                sendMessage("Lightning struck at X: " + sound.getX() + " Y: " + sound.getY() + " Z: " + sound.getZ());
            }
        }
        if (sound.getSoundLocation().getPath().equals("entity.wither.spawn")) {
            if ((boolean) settings.getSetting("logToChat")) {
                sendMessage("Wither spawned at X: " + sound.getX() + " Y: " + sound.getY() + " Z: " + sound.getZ());
            }
        }
        if (sound.getSoundLocation().getPath().equals("entity.wither.death")) {
            if ((boolean) settings.getSetting("logToChat")) {
                sendMessage("Wither died at X: " + sound.getX() + " Y: " + sound.getY() + " Z: " + sound.getZ());
            }
        }
    }
}

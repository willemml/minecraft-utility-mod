package dev.wnuke.NukeClient.module.modules;

import dev.wnuke.NukeClient.module.Category;
import dev.wnuke.NukeClient.module.Module;

public class FullBright extends Module {
    public FullBright() {
        super("Full Bright", Category.RENDER, "Makes everything maximum brightness");
    }

    private double originalBrightness = 1.0;

    @Override
    public void onEnabled() {
        originalBrightness = mc.gameSettings.gamma;
    }

    public void onTick() {
        if (mc.gameSettings.gamma < 16.0) mc.gameSettings.gamma += 1.2;
    }

    @Override
    public void onDisabled() {
        mc.gameSettings.gamma = originalBrightness;
    }
}

package dev.wnuke.nukeclient.module.modules

import dev.wnuke.nukeclient.event.Subscribe
import dev.wnuke.nukeclient.event.events.Tick
import dev.wnuke.nukeclient.module.Category
import dev.wnuke.nukeclient.module.Module

class FullBright : Module("full-bright", "Let's you see in the dark.", Category.RENDER) {
    private var startGamma = 0.0

    override fun onEnable() {
        startGamma = mc.options.gamma
    }

    override fun onDisable() {
        mc.options.gamma = startGamma
    }

    @Subscribe
    fun onTick(event: Tick) {
        if (mc.options.gamma < 16.0) {
            mc.options.gamma = 16.0
        }
    }
}
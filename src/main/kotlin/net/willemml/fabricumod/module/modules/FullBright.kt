package net.willemml.fabricumod.module.modules

import net.willemml.fabricumod.event.Subscribe
import net.willemml.fabricumod.event.events.Tick
import net.willemml.fabricumod.module.Category
import net.willemml.fabricumod.module.Module

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
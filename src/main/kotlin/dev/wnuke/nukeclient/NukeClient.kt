package dev.wnuke.nukeclient

import dev.wnuke.nukeclient.event.EventManager
import dev.wnuke.nukeclient.module.ModuleManager
import dev.wnuke.nukeclient.module.modules.FullBright
import net.fabricmc.api.ModInitializer

object NukeClient : ModInitializer {
    @JvmStatic
    val eventManager = EventManager()
    val moduleManager = ModuleManager()

    override fun onInitialize() {
        moduleManager.getModuleByClass(FullBright::class)?.enable()
        println("--------------------------")
        println("")
        println("NUKE Client Loaded!")
        println("")
        println("--------------------------")
    }
}
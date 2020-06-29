package dev.wnuke.nukeclient

import dev.wnuke.nukeclient.event.EventManager
import net.fabricmc.api.ModInitializer

object NukeClient : ModInitializer {
    @JvmStatic
    val eventManager = EventManager()

    override fun onInitialize() {
        println("--------------------------")
        println("")
        println("NUKE Client Loaded!")
        println("")
        println("--------------------------")
    }
}
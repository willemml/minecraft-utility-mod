package dev.wnuke.nukeclient

import dev.wnuke.nukeclient.event.EventManager
import dev.wnuke.nukeclient.event.Subscribe
import dev.wnuke.nukeclient.event.events.Tick
import net.fabricmc.api.ModInitializer

object NukeClient : ModInitializer {
    @JvmStatic
    val eventManager = EventManager()

    override fun onInitialize() {
        eventManager.register(NukeClient.javaClass)
        println("--------------------------")
        println("")
        println("NUKE Client Loaded!")
        println("")
        println("--------------------------")
    }

    @Subscribe
    fun onTick(e: Tick) {
        println("Ticked!")
    }
}
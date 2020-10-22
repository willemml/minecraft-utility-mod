package net.willemml.fabricumod

import net.willemml.fabricumod.event.EventManager
import net.willemml.fabricumod.module.ModuleManager
import net.willemml.fabricumod.module.modules.FullBright
import net.fabricmc.api.ModInitializer

object FabricUMod : ModInitializer {
    @JvmStatic
    val eventManager = EventManager()
    val moduleManager = ModuleManager()

    override fun onInitialize() {
        moduleManager.getModuleByClass(FullBright::class)?.enable()
        println("--------------------------")
        println("")
        println("Fabric Utility Mod Loaded!")
        println("")
        println("--------------------------")
    }
}
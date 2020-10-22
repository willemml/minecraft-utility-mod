package net.willemml.fabricumod.module

import net.willemml.fabricumod.FabricUMod.eventManager
import net.minecraft.client.MinecraftClient

open class Module(
        val name: String,
        val description: String,
        val category: Category
) {
    val mc: MinecraftClient = MinecraftClient.getInstance()

    var enabled: Boolean = false

    open fun onEnable() {}

    open fun onDisable() {}

    fun enable() {
        enabled = true
        eventManager.register(this)
    }
    fun disable() {
        enabled = false
        eventManager.unRegister(this)
    }

    fun toggle() {
        if (enabled) disable()
        else enable()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Module

        if (name != other.name) return false
        if (description != other.description) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }

    override fun toString(): String {
        return "Module(name='$name', description='$description', category=$category, enabled=$enabled)"
    }
}
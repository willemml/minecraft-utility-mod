package dev.wnuke.nukeclient.module

import org.reflections.Reflections
import kotlin.reflect.KClass

class ModuleManager {
    private val modules: HashSet<Module> = HashSet()

    init {
        modules.clear()
        val moduleClassIterator =
                Reflections("dev.wnuke.nukeclient.module").getSubTypesOf(
                        Module::class.java
                ).iterator()
        while (moduleClassIterator.hasNext()) {
            val module = moduleClassIterator.next().getConstructor().newInstance()
            modules.add(module)
        }
    }

    fun <T:Module> getModuleByClass(clazz: KClass<T>): Module? {
        val moduleIterator = modules.iterator()
        while (moduleIterator.hasNext()) {
            val current = moduleIterator.next()
            if (current::class == clazz) return current
        }
        return null
    }

    fun getModuleByName(name: String): Module? {
        val moduleIterator = modules.iterator()
        while (moduleIterator.hasNext()) {
            val current = moduleIterator.next()
            if (current.name == name) return current
        }
        return null
    }

    fun getModulesInCat(category: Category): HashSet<Module> {
        val modulesInCategory: HashSet<Module> = HashSet()
        val moduleIterator = modules.iterator()
        while (moduleIterator.hasNext()) {
            val current = moduleIterator.next()
            if (current.category == category) modulesInCategory.add(current)
        }
        return modulesInCategory
    }
}

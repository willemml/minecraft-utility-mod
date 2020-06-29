package dev.wnuke.nukeclient.event

import java.lang.reflect.Method


class EventManager {
    private val registeredMethods: HashMap<Class<*>, Method> = HashMap()

    fun register(clazz: Class<*>) {
        clazz.declaredMethods.forEach { if (it.isAnnotationPresent(Subscribe::class.java)) register(it) }
    }

    fun post(event: Any) {
        registeredMethods.forEach { (t, u) -> if (t == event) u(event) }
    }

    private fun register(method: Method) {
        if (method.parameters.size == 1) {
            if (method.parameters[0].type.genericSuperclass == Event::class.java) {
                registeredMethods[method.parameters[0].type] = method
            }
        }
    }
}
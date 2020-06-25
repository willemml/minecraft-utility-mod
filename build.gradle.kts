import net.fabricmc.loom.util.GradleSupport

plugins {
    id("java")
    id("fabric-loom") version "0.4-SNAPSHOT"
    kotlin("jvm") version "1.3.71"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("io.github.manosbatsis.gradle.plugin.reflections") version "1.1"
}

minecraft {}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val modid = "nuke-client"
val kotlinVersion = "1.3.71+build.1"
val fabricApiVersion = "0.13.1+build.370-1.16"
version = "1.0.0"
group = "dev.wnuke"

base {
    archivesBaseName = "nuke-client"
}

dependencies {
    minecraft(group = "com.mojang", name = "minecraft", version = "1.16.1")
    mappings(group = "net.fabricmc", name = "yarn", version = "1.16.1+build.4", classifier = "v2")
    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = "0.8.8+build.202")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation(group = "net.fabricmc.fabric-api", name = "fabric-api", version = fabricApiVersion)

    // Reflections
    modImplementation(group = "javassist", name = "javassist", version = "3.12.1.GA")
    modImplementation(group = "org.reflections", name = "reflections", version = "0.9.10")

    // Kotlin
    modImplementation(group = "net.fabricmc", name = "fabric-language-kotlin", version = kotlinVersion)
}

tasks {
    named<io.github.manosbatsis.gradle.plugin.reflections.ReflectionsMetadataEmbeddingTask>("reflections") {
        dependsOn(classes)
    }
    named<org.gradle.jvm.tasks.Jar>("jar") {
        dependsOn(reflections)
        from("LICENSE", "README.md")
    }
    named<net.fabricmc.loom.task.RemapJarTask>("remapJar") {
        dependsOn(shadowJar)
        mustRunAfter(shadowJar)
    }
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        dependencies {
            include(dependency("javassist:javassist"))
            include(dependency("org.reflections:reflections"))
            include(dependency("net.fabricmc:fabric-language-kotlin"))
            include(dependency("net.fabricmc.fabric-api:fabric-api"))
        }
    }
    named<ProcessResources>("processResources") {
        filesMatching("fabric.mod.json") {
            expand("version" to version)
        }
    }
}
/*
 * mojang-maps.fabric
 * Copyright (C) 2025 Abel van Hulst/Abelkrijgtalles/Abelpro678
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

plugins {
    alias(libs.plugins.fabric.loom)
}

dependencies {
    minecraft("com.mojang:minecraft:${libs.versions.minecraft.version.get()}")
    modImplementation(libs.fabric.loader.get())
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc.fabric-api:fabric-api:${libs.versions.fabric.api.get()}+${libs.versions.minecraft.version.get()}")

    testImplementation("net.fabricmc:fabric-loader-junit:${libs.fabric.loader.get().version}")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:${libs.versions.junit.jupiter.get()}")
}

tasks.processResources {
    filesMatching("fabric.mod.json") {
        expand(
            "version" to libs.versions.mojang.maps.get(),
            "compatible_minecraft_versions" to libs.versions.minecraft.compatible.get(),
            "java_version" to libs.versions.java.get()
        )
    }
}

fabricApi {
    val choiceFile = layout.buildDirectory.file("EULA_choice").get().asFile
    choiceFile.createNewFile()
    val choice = choiceFile.readText()
    if (choice.trim() != "true") {
        println("By running these tests, you automatically agree to the Minecraft EULA.")
        println("If not, please interrupt (Ctrl + C) or stop the build in 7 seconds.")
        Thread.sleep(4_000)
        println("3")
        Thread.sleep(1_000)
        println("2")
        Thread.sleep(1_000)
        println("1")
        Thread.sleep(1_000)
        println("Your choice to agree has been saved. Run the clean task to remove your choice.")
        println("You can also delete this file: ${choiceFile.absolutePath}")
        choiceFile.writeText("true")
    }
    configureTests {
        createSourceSet = true
        modId = "mojang_maps-test"
        eula = true
        enableClientGameTests = false
    }
}
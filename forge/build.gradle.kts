/*
 * mojang-maps.forge
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
    alias(libs.plugins.forge.gradle)
}

tasks.jar {
    archiveClassifier = "slim"
}

minecraft {
    mappings("official", libs.versions.minecraft.version.get())
}

dependencies {
    minecraft("net.minecraftforge:forge:${libs.versions.minecraft.version.get()}-${libs.versions.forge.get()}")

    // make sure this is up to date with the main subproject dependencies
    shadowMe(project(":common"))
    shadowMe(project(":platform"))
    shadowMe(project(":platform-nms"))
}

tasks.processResources {
    filesMatching("META-INF/mods.toml") {
        expand(
            "version" to libs.versions.mojang.maps.get(),
            // For whatever reason forge uses [1.18, 1.18.1, 1.18.2) instead of the standard ["1.18", "1.18.1", "1.18.2"]
            "compatible_minecraft_versions" to libs.versions.minecraft.compatible.get().replace("\"", "")
                .replace("]", ",)")
        )
        filter { line ->
            if (!line.contains("mojang-maps#creditscode-i-used")) {
                line.replace(Regex("#.*"), "").trim()
            } else {
                line
            }
        }
    }
    // clean empty lines
    doLast {
        fileTree(destinationDir).matching {
            include("META-INF/mods.toml")
        }.forEach { file ->
            val lines = file.readLines()
                .map { it.trim() }
                .filter { it.isNotEmpty() }
            file.writeText(lines.joinToString("\n"))
        }
    }
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}

reobf {
    create("shadowJar") {}
}
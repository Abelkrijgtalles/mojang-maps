/*
 * mojang-maps
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

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    `java-library`
    alias(libs.plugins.idea.ext) // Fix conflict between ForgeGradle & ModDevGradle fighting
    alias(libs.plugins.shadow) apply false
}

val projectsBeingAnnoyingWithShadow: Array<Project> = arrayOf(project("forge"))
val projectsBeingAnnoyingWithJacksonCore: Array<Project> = arrayOf(project("neoforge"))

allprojects.forEach { p ->
    p.apply(plugin = "java")
    p.apply(plugin = "java-library")
    p.plugins.apply(libs.plugins.shadow.get().pluginId)

    java {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    }

    p.repositories {
        mavenCentral()

        maven {
            name = "Jitpack"
            url = uri("https://jitpack.io")
        }
        maven {
            name = "Paper"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven {
            name = "Sponge"
            url = uri("https://repo.spongepowered.org/repository/maven-public/")
        }
    }

    p.configurations {
        if (!projectsBeingAnnoyingWithShadow.contains(p)) {
            create("shadowMe") {
                extendsFrom(getByName("implementation"))
            }
        } else {
            create("shadowMe")
            getByName("implementation") {
                extendsFrom(getByName("shadowMe"))
            }
        }
    }

    p.tasks.named<ShadowJar>("shadowJar") {
        configurations = listOf(p.configurations.getByName("shadowMe"))
        archiveBaseName.set("mojang_maps-" + libs.versions.mojang.maps.get() + "-" + p.name)
        archiveClassifier.set("")
        exclude("**/icon.svg")
        exclude("**/favicon.ico")

        minimize()

        if (projectsBeingAnnoyingWithShadow.contains(p)) {
            finalizedBy("reobfShadowJar")
        }

    }

    p.tasks.build {
        dependsOn(p.tasks.named<ShadowJar>("shadowJar"))
    }
}

subprojects.forEach { p ->
    val needsCommon = p != project("common") && p != project("platform") && p != project("platform-nms")
    val nmsProject = p == project("fabric") || p == project("forge") || p == project("neoforge")

    if (!projectsBeingAnnoyingWithShadow.contains(p)) {
        // you have to manually add them to the annoying projects
        if (needsCommon) {
            p.dependencies {
                implementation(project(":common"))
            }
        }

        if (p != project("platform")) {
            p.dependencies {
                implementation(project(":platform"))
            }
        }

        if (nmsProject) {
            p.dependencies {
                implementation(project(":platform-nms"))
            }
        }
    }

    if (!projectsBeingAnnoyingWithJacksonCore.contains(p) && needsCommon) {

        p.dependencies {

            implementation("com.fasterxml.jackson.core:jackson-core:${libs.versions.jackson.core.get()}")
            implementation("com.fasterxml.jackson.core:jackson-databind:${libs.versions.jackson.core.get()}")

        }

    }

}
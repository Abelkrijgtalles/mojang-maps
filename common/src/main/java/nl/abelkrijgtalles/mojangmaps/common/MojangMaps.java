/*
 * mojang_maps.common.main
 * Copyright (C) 2024 Abel van Hulst/Abelkrijgtalles/Abelpro678
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

package nl.abelkrijgtalles.mojangmaps.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MojangMaps {

    public static final String MOD_ID = "mojang_maps";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static String MINECRAFT_VERSION = getMinecraftVersion();

    public static void init() {

        LOGGER.info("Running Mojang Maps on Minecraft version {}.", MINECRAFT_VERSION);

    }

    private static String getMinecraftVersion() {

        #if MC_VER == MC_1_16_5
        return "1.16.4-1.16.5";
        #elif MC_VER == MC_1_17_1
        return "1.17-1.17.1";
        #elif MC_VER == MC_1_18_2
        return "1.18.2";
        #elif MC_VER == MC_1_19_2
        return "1.19.2";
        #elif MC_VER == MC_1_19_4
        return "1.19.4";
        #elif MC_VER == MC_1_20_1
        return "1.20-1.20.1";
        #elif MC_VER == MC_1_20_2
        return "1.20.2";
        #elif MC_VER == MC_1_20_4
        return "1.20.3-1.20.4";
        #elif MC_VER == MC_1_20_6
        return "1.20.5-1.20.6";
        #elif MC_VER == MC_1_21_0
        return "1.21-1.21.1";
        #endif

    }

}

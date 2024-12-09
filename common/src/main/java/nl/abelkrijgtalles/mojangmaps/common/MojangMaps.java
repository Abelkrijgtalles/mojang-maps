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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.DetectedVersion;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.LoaderInfo;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigGroup;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigItem;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MojangMaps {

    public static final String MOD_ID = "mojang_maps";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private static final String bstatsMessage = """
            bStats (https://bStats.org) collects some basic information for plugin authors, like how
            many people use their plugin and their total player count. It's recommended to keep bStats
            enabled, but if you're not comfortable with this, you can turn this setting off. There is no
            performance penalty associated with having metrics enabled, and data sent to bStats is fully
            anonymous.""";
    public static LoaderInfo loaderInfo;

    public static void init(LoaderInfo loaderInfo) {

        MojangMaps.loaderInfo = loaderInfo;

        LOGGER.info("Running Mojang Maps on Minecraft version {}.", DetectedVersion.tryDetectVersion().getName());
        LOGGER.info(loaderInfo.getConfig().get("message"));

    }

    public static List<ConfigObject> getDefaultConfig() {

        List<ConfigObject> config = new ArrayList<>(List.of(
                new ConfigItem("message", "Hello you are cool :)", "Very cool")));

        if (!loaderInfo.isBstatsNative()) {

            config.addAll(0, List.of(
                    new ConfigGroup("bstats", bstatsMessage, List.of(
                            new ConfigItem("enabled", "true"),
                            new ConfigItem("serverUuid", UUID.randomUUID().toString()),
                            new ConfigItem("logFailedRequests", "false"),
                            new ConfigItem("logSentData", "false"),
                            new ConfigItem("logResponseData", "false")
                    ))
            ));

        }

        return config;
    }

}

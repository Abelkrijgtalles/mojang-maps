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

import java.util.Collections;
import java.util.List;
import net.minecraft.DetectedVersion;
import nl.abelkrijgtalles.mojangmaps.common.config.roads.RoadData;
import nl.abelkrijgtalles.mojangmaps.common.platform.LoaderInfo;
import nl.abelkrijgtalles.mojangmaps.common.platform.config.ConfigItem;
import nl.abelkrijgtalles.mojangmaps.common.platform.config.ConfigObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MojangMaps {

    public static final String MOD_ID = "mojang_maps";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static LoaderInfo loaderInfo;

    /**
     * Initialize Mojang Maps
     *
     * @param loaderInfo The loader specific info.
     */
    public static void init(LoaderInfo loaderInfo) {

        MojangMaps.loaderInfo = loaderInfo;
        LOGGER.info("Running Mojang Maps on Minecraft version {}.", DetectedVersion.tryDetectVersion().getName());

        RoadData roadData = new RoadData();
        if (!loaderInfo.isRunningTests()) roadData.setupRoadData();

    }

    public static List<ConfigObject> getDefaultConfig() {

        return Collections.singletonList(
                new ConfigItem("message", "Hello you are cool :)", "Very cool")
        );
    }

}

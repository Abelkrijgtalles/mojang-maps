/*
 * mojang_maps.common.main
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

package nl.abelkrijgtalles.mojangmaps.common;

import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import java.nio.file.Path;
import nl.abelkrijgtalles.mojangmaps.common.config.MojangMapsConfig;
import nl.abelkrijgtalles.mojangmaps.common.config.roads.RoadData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MojangMaps {

    public static final String MOD_ID = "mojang_maps";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static LoaderInfo loaderInfo;
    public static MojangMapsConfig config;

    /**
     * Initialize Mojang Maps
     *
     * @param loaderInfo The loader specific info.
     */
    public static void init(LoaderInfo loaderInfo) {

        MojangMaps.loaderInfo = loaderInfo;
        LOGGER.info("Running Mojang Maps on Minecraft version {}.", loaderInfo.getPlatform().getMinecraftVersion());

        YamlConfigurationProperties configurationProperties = YamlConfigurationProperties.newBuilder()
                .setNameFormatter(NameFormatters.LOWER_KEBAB_CASE)
                .build();
        config = YamlConfigurations.update(Path.of(loaderInfo.getDataDirectory().toString(), "config.yml"), MojangMapsConfig.class, configurationProperties);

        RoadData roadData = new RoadData();
        if (!loaderInfo.isRunningTests()) roadData.setupRoadData();

    }

}

/*
 * nl.abelkrijgtalles.mojangmaps.mojang_maps.fabric.test
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

package nl.abelkrijgtalles.mojangmaps.common.config.roads;

import java.nio.file.Path;
import java.util.List;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.model.Road;
import nl.abelkrijgtalles.mojangmaps.fabric.MojangMapsFabric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoadDataTest {

    @Test
    void generateRoadData() {

        MojangMaps.LOGGER.info("Generating the roads data in {}", Path.of(MojangMaps.loaderInfo.getConfig().getDataDirectory().toString(), "roads.mmd").toString());
        RoadData generator = new RoadData();
        generator.generateRoadData(RoadData.roads);

    }

    @Test
    void readRoadData() {

        MojangMaps.LOGGER.info("Reading roads data in {}", Path.of(MojangMaps.loaderInfo.getConfig().getDataDirectory().toString(), "roads.mmd").toString());
        RoadData generator = new RoadData();
        List<Road> roads = generator.readRoadData();
        for (Road road : roads) {

            MojangMaps.LOGGER.info(road.getName());
            MojangMaps.LOGGER.info(road.getWorldName());
            MojangMaps.LOGGER.info(road.getWaypoints());

        }

    }

    @BeforeEach
    void setup() {

        MojangMapsFabric.init();

    }

}

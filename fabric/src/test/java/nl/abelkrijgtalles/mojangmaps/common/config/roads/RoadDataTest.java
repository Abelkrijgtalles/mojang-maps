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

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.core.Position;
import net.minecraft.world.phys.Vec3;
import nl.abelkrijgtalles.mojangmaps.common.model.Road;
import nl.abelkrijgtalles.mojangmaps.fabric.MojangMapsFabric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoadDataTest {

    @RepeatedTest(5)
    void testGeneratingRoadData(RepetitionInfo repetitionInfo) {

        List<Road> roads = new ArrayList<>();
        Random rand = new Random();
        Faker faker = new Faker();
        RoadData roadData = new RoadData();

        for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {

            List<Position> waypoints = new ArrayList<>();

            for (int j = 0; j < repetitionInfo.getCurrentRepetition(); j++) {

                waypoints.add(new Vec3(rand.nextDouble(1000), rand.nextDouble(256), rand.nextDouble(1000)));

            }

            roads.add(new Road(faker.address().streetName(), "world", waypoints));

        }

        roadData.generateRoadData(roads);
        assertEquals(roads, roadData.readRoadData());

    }

    @BeforeEach
    void setup() {

        MojangMapsFabric.init(true);

    }

}

/*
 * MojangMaps
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

package nl.abelkrijgtalles.MojangMaps.pathfinding;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.object.Road;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import org.bukkit.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

class NewAStarTest {

    // The performance for the A* tests (lower = better performance). This is most importantly used for the number of tests and the max number of roads per test (there could be fewer roads).
    private final int ASTAR_PERFOMANCE = 15;
    private List<Location> availableLocations = new ArrayList<>();

    @RepeatedTest(ASTAR_PERFOMANCE)
    void findRoute() {

        NewAStar aStar = new NewAStar();
        Random random = new Random();

        Location beginning = availableLocations.get(random.nextInt(availableLocations.size()));
        Location end = availableLocations.get(random.nextInt(availableLocations.size()));

        aStar.findRoute(beginning, end);

    }

    @AfterEach
    public void tearDown() {

        MockBukkit.unmock();
    }

    @BeforeEach
    public void setUp() {

        ServerMock server = MockBukkit.mock();
        MockBukkit.load(MojangMaps.class);
        server.addSimpleWorld("world");

        Random random = new Random();

        for (int i = 0; i < random.nextInt(1, ASTAR_PERFOMANCE + 1); i++) {

            List<Integer> locations = new ArrayList<>();

            for (int j = 0; j < random.nextInt(1, ASTAR_PERFOMANCE + 1); j++) {

                Location location = new Location(server.getWorlds().getFirst(), random.nextDouble(1, ASTAR_PERFOMANCE * 1000 + 1), random.nextDouble(1, ASTAR_PERFOMANCE * 1000 + 1), random.nextDouble(1, ASTAR_PERFOMANCE * 1000 + 1));
                locations.add(NodesConfigUtil.getLocations().size());
                availableLocations.add(location);
                NodesConfigUtil.addLocation(location);

            }

            Road road = new Road(locations);
            NodesConfigUtil.addRoad(road);

        }

    }

}
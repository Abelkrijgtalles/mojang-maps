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

package nl.abelkrijgtalles.MojangMaps.util.file;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.object.Road;
import org.bukkit.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NodesConfigUtilTest {

    private ServerMock server;
    private MojangMaps plugin;
    private WorldMock world;

    private static Stream<Arguments> oneToFiveAsStreamOfArguments() {

        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5)
        );

    }

    private static Location getRandomLocation() {

        int x = generateRandomInt((int) -30e+6, (int) 30e+6);
        int y = generateRandomInt(0, 256);
        int z = generateRandomInt((int) -30e+6, (int) 30e+6);

        return new Location(null, x, y, z);

    }

    private static Stream<Arguments> get5RandomCoordinates() {

        List<Arguments> arguments = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Location randomLocation = getRandomLocation();

            arguments.add(Arguments.of(
                    (int) randomLocation.getX(),
                    (int) randomLocation.getY(),
                    (int) randomLocation.getZ()
            ));

        }

        return arguments.stream();

    }

    private static int generateRandomInt(int min, int max) {

        return min + (int) (Math.random() * ((max - min) + 1));

    }

    @AfterEach
    public void tearDown() {

        MockBukkit.unmock();
    }

    @BeforeEach
    public void setUp() {

        server = MockBukkit.mock();
        plugin = MockBukkit.load(MojangMaps.class);
        world = server.addSimpleWorld("world");

    }

    @ParameterizedTest
    @MethodSource("get5RandomCoordinates")
    public void register5Locations(int x, int y, int z) {

        Location location = new Location(world, x, y, z);

        NodesConfigUtil.addLocation(location);

        Assertions.assertEquals(location, NodesConfigUtil.getLocations().get(0));

    }

    @ParameterizedTest
    @MethodSource("oneToFiveAsStreamOfArguments")
    public void register5RoadsWithoutName(int numberOfLocationsInRoad) {

        List<Location> locations = new ArrayList<>();
        List<Integer> locationpointers = new ArrayList<>();

        for (int i = 0; i < numberOfLocationsInRoad; i++) {

            Location location = getRandomLocation();
            location.setWorld(world);
            NodesConfigUtil.addLocation(location);
            locations.add(location);
            locationpointers.add(i);

        }

        NodesConfigUtil.addRoad(new Road(locationpointers));

        for (int i = 0; i < numberOfLocationsInRoad; i++) {

            Road road = NodesConfigUtil.getRoads().get(0);

            Assertions.assertEquals(locations.get(i), NodesConfigUtil.getLocations().get(road.getLocations().get(i)));

        }

    }

    @ParameterizedTest
    @MethodSource("oneToFiveAsStreamOfArguments")
    public void register5RoadsWithName(int numberOfLocationsInRoad) {

        List<Location> locations = new ArrayList<>();
        List<Integer> locationpointers = new ArrayList<>();
        String name = "ThisIsACoolNameForARoad";

        for (int i = 0; i < numberOfLocationsInRoad; i++) {

            Location location = getRandomLocation();
            location.setWorld(world);
            NodesConfigUtil.addLocation(location);
            locations.add(location);
            locationpointers.add(i);

        }

        NodesConfigUtil.addRoad(new Road(name, locationpointers));

        for (int i = 0; i < numberOfLocationsInRoad; i++) {

            Road road = NodesConfigUtil.getRoads().get(0);

            Assertions.assertEquals(locations.get(i), NodesConfigUtil.getLocations().get(road.getLocations().get(i)));
            Assertions.assertEquals(name, road.getName());

        }

    }

}
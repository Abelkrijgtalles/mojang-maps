///*
// * mojang_maps.spigot.test
// * Copyright (C) 2025 Abel van Hulst/Abelkrijgtalles/Abelpro678
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//
//package mojangmaps.common.config.roads;
//
//import com.github.javafaker.Faker;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import nl.abelkrijgtalles.mojangmaps.common.config.roads.RoadData;
//import nl.abelkrijgtalles.mojangmaps.common.model.Road;
//import nl.abelkrijgtalles.mojangmaps.platform.world.Vec3;
//import nl.abelkrijgtalles.mojangmaps.spigot.SpigotMojangMaps;
//import nl.abelkrijgtalles.mojangmaps.spigot.platform.world.SpigotLevel;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockbukkit.mockbukkit.MockBukkit;
//import org.mockbukkit.mockbukkit.ServerMock;
//import org.mockbukkit.mockbukkit.world.WorldMock;
//
//public class RoadDataTest {
//
//    private ServerMock server;
//    private SpigotMojangMaps plugin;
//    private WorldMock world;
//
//    // they don't work at the moment but i'm going to fix them later
////    @RepeatedTest(TestSettings.REPEATED_TEST_COUNT)
////    void testGeneratingRoadData(RepetitionInfo repetitionInfo) {
////
////        List<Road> roads = new ArrayList<>();
////        Random rand = new Random();
////        Faker faker = new Faker();
////        RoadData roadData = new RoadData();
////        int numberOfRoadsAndWaypointsPerRoad = repetitionInfo.getCurrentRepetition();
////
////        for (int i = 0; i < numberOfRoadsAndWaypointsPerRoad; i++) {
////
////            List<Vec3> waypoints = new ArrayList<>();
////
////            for (int j = 0; j < numberOfRoadsAndWaypointsPerRoad; j++) {
////
////                waypoints.add(new Vec3(rand.nextDouble() * 1000, rand.nextDouble() * 256, rand.nextDouble() * 1000));
////
////            }
////
////            roads.add(new Road(faker.address().streetName(), "world", waypoints));
////
////        }
////
////        roadData.generateRoadData(roads);
////
////        // doing this cause junit does weird
////        List<Road> readRoads = roadData.readRoadData();
////        for (int i = 0; i < roads.size(); i++) {
////
////            Road road = roads.get(i);
////            Road readRoad = readRoads.get(i);
////
////            assertEquals(road.getName(), readRoad.getName());
////            assertEquals(road.getWorldKey(), readRoad.getWorldKey());
////
////            List<Vec3> roadWaypoints = road.getWaypoints();
////            List<Vec3> readRoadWaypoints = readRoad.getWaypoints();
////
////            for (int j = 0; j < roadWaypoints.size(); j++) {
////
////                Position position = roadWaypoints.get(j);
////                Position readPosition = readRoadWaypoints.get(j);
////
////                assertEquals((int) position.x(), (int) readPosition.x());
////                assertEquals((int) position.y(), (int) readPosition.y());
////                assertEquals((int) position.z(), (int) readPosition.z());
////
////            }
////
////        }
////
////    }
////
//    @BeforeEach
//    void setup() {
//
//        SpigotMojangMaps.isRunningTests = true;
//
//        server = MockBukkit.mock();
//        plugin = MockBukkit.load(SpigotMojangMaps.class);
//        world = server.addSimpleWorld("world");
//
//    }
//
//    @Test
//    void createRoadsMMD() {
//
//        List<Road> roads = new ArrayList<>();
//        Random rand = new Random();
//        Faker faker = new Faker();
//        RoadData roadData = new RoadData();
//        int numberOfRoadsAndWaypointsPerRoad = 3;
//
//        for (int i = 0; i < numberOfRoadsAndWaypointsPerRoad; i++) {
//
//            List<Vec3> waypoints = new ArrayList<>();
//
//            for (int j = 0; j < numberOfRoadsAndWaypointsPerRoad; j++) {
//
//                waypoints.add(new Vec3((int) (rand.nextDouble() * 1000), (int) (rand.nextDouble() * 256), (int) (rand.nextDouble() * 1000)));
//
//            }
//
//            Road road = new Road(faker.address().streetName(), new SpigotLevel(world).getIdentifier(), waypoints);
//            System.out.println(waypoints);
//            System.out.println(road);
//            roads.add(road);
//
//        }
//
//        roadData.generateRoadData(roads);
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Stop the mock server
//        MockBukkit.unmock();
//    }
//
//}

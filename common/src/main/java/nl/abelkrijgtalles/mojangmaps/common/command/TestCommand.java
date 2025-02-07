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

package nl.abelkrijgtalles.mojangmaps.common.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import nl.abelkrijgtalles.mojangmaps.common.config.roads.RoadData;
import nl.abelkrijgtalles.mojangmaps.common.model.Road;
import nl.abelkrijgtalles.mojangmaps.platform.command.Command;
import nl.abelkrijgtalles.mojangmaps.platform.command.CommandSource;
import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Vec3;

public class TestCommand implements Command {


    @Override
    public boolean run(CommandSource source) {

        if (source.getPermission().console()) return false;

        List<Road> roads = new ArrayList<>();
        Random rand = new Random();
        RoadData roadData = new RoadData();

//        for (int i = 0; i < 3; i++) {
//
//            List<Vec3> waypoints = new ArrayList<>();
//
//            for (int j = 0; j < 3; j++) {
//
//                waypoints.add(new Vec3((int) (rand.nextDouble() * 1000), (int) (rand.nextDouble() * 256), (int) (rand.nextDouble() * 1000)));
//
//            }
//
//            Road road = new Road("sigma", source.getLevel(), waypoints);
//            roads.add(road);
//
//        }

        List<Vec3> waypoints = Arrays.asList(new Vec3(29, source.getLevel().getHeightAtLocation(HeightMapType.MOTION_BLOCKING_NO_LEAVES, 29, -5), -5), new Vec3(34, source.getLevel().getHeightAtLocation(HeightMapType.MOTION_BLOCKING_NO_LEAVES, 34, -12), -12));
        roads.add(new Road("Halo", source.getLevel(), waypoints));

        for (Road road : roads) {

            System.out.println(road.getName());

        }

        try {
            roadData.generateRoadData(roads);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public String getCommand() {

        return "test";
    }

}

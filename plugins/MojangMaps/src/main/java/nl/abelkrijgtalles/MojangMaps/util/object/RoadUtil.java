/*
 * Copyright (C) 2023 Abel van Hulst/Abelkrijgtalles/Abelpro678
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.util.object;

import nl.abelkrijgtalles.MojangMaps.object.Road;
import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RoadUtil {
    public static void addMoreLocations(Player p, List<Location> locations) {

        int i = 0;
        List<Location> locationsCopy = new ArrayList<>(locations);

        for (Location location : locationsCopy) {

            if (i != 0) {

                Location location1 = locations.get(i - 1);
                int oneAxisDistance = LocationUtil.getOneAxisDistance(location1, location);

                if (oneAxisDistance > 4) {

                    int neededLocations = (int) Math.floor((double) oneAxisDistance / 5);

                    for (int j = 0; j < neededLocations - 1; j += 1) {

                        locations.add(new Location(p.getWorld(), Math.round((float) ((location.getBlockX() - location1.getBlockX()) / neededLocations) * (j + 1)) + location1.getBlockX(), location1.getBlockY(), Math.round((float) ((location.getBlockZ() - location1.getBlockZ()) / neededLocations) * (j + 1)) + location1.getBlockZ()));

                    }

                }

            }

            i += 1;

        }

    }

    public static String getRoadNameFromLocation(Location location) {

        for (Road road : NodesConfigUtil.getRoads()) {

            for (Integer location1 : road.getLocations()) {

                if (NodesConfigUtil.getLocations().get(location1) == location) {

                    return road.getName();

                }

            }

        }

        return null;

    }

    public static String getLocationMessage(Player p) {

        Location closestLocation = LocationUtil.getClosestLocation(p.getLocation());

        if (closestLocation != null) {

            if (LocationUtil.isTheSameLocation(p.getLocation(), closestLocation, 5)) {

                return MessageUtil.getMessage("currentlyon").formatted(RoadUtil.getRoadNameFromLocation(LocationUtil.getClosestLocation(p.getLocation())));

            }

        }
        return "";

    }
}

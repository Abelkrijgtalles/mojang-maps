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

import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;

import org.bukkit.Location;

public class LocationUtil {

    public static boolean isTheSameLocation(Location location, Location otherLocation, int gap) {

        return getDistance(location, otherLocation) < gap + 1;

    }

    public static int getDistance(Location location1, Location location2) {

        int x1 = location1.getBlockX();
        int x2 = location2.getBlockX();
        int z1 = location1.getBlockZ();
        int z2 = location2.getBlockZ();

        return Math.abs(x1 - x2) + Math.abs(z1 - z2);

    }

    public static int getOneAxisDistance(Location location1, Location location2) {

        if (Math.abs(location1.getBlockX() - location2.getBlockX()) > Math.abs(location1.getBlockZ() - location2.getBlockZ()) || Math.abs(location1.getBlockX() - location2.getBlockX()) == Math.abs(location1.getBlockZ() - location2.getBlockZ())) {

            return Math.abs(location1.getBlockX() - location2.getBlockX());

        } else {

            return Math.abs(location1.getBlockZ() - location2.getBlockZ());

        }

    }

    public static Location getClosestLocation(Location location) {

        double minDistanceSquared = Double.MAX_VALUE;
        Location closestLocation = null;

        for (Location location1 : NodesConfigUtil.getLocations()) {
            double distanceSquared = location.distanceSquared(location1);

            if (distanceSquared < minDistanceSquared) {
                minDistanceSquared = distanceSquared;
                closestLocation = location1;
            }
        }

        return closestLocation;

    }

}

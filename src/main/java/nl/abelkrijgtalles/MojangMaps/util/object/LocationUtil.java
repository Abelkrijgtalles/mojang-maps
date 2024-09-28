/*
 * MojangMaps
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.util.object;

import java.util.List;
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

    public static Location getClosestLocation(List<Location> locations, Location location) {

        double minDistanceSquared = Double.MAX_VALUE;
        Location closestLocation = null;

        for (Location location1 : locations) {
            double distanceSquared = location.distanceSquared(location1);

            if (distanceSquared < minDistanceSquared) {
                minDistanceSquared = distanceSquared;
                closestLocation = location1;
            }
        }

        return closestLocation;

    }

    public static Location getLowestLocation(List<Location> locations) {

        double minX = Integer.MAX_VALUE;
        double minY = Integer.MAX_VALUE;
        double minZ = Integer.MAX_VALUE;

        for (Location loc : locations) {
            if (loc.getBlockX() < minX) minX = loc.getBlockX();
            if (loc.getBlockY() < minY) minY = loc.getBlockY();
            if (loc.getBlockZ() < minZ) minZ = loc.getBlockZ();
        }

        return new Location(locations.getFirst().getWorld(), minX, minY, minZ);

    }

    public static Location getHighestLocation(List<Location> locations) {

        double maxX = Integer.MIN_VALUE;
        double maxY = Integer.MIN_VALUE;
        double maxZ = Integer.MIN_VALUE;

        for (Location loc : locations) {
            if (loc.getBlockX() > maxX) maxX = loc.getBlockX();
            if (loc.getBlockY() > maxY) maxY = loc.getBlockY();
            if (loc.getBlockZ() > maxZ) maxZ = loc.getBlockZ();
        }

        return new Location(locations.getFirst().getWorld(), maxX, maxY, maxZ);

    }

}

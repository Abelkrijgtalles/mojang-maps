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

package nl.abelkrijgtalles.MojangMaps.util.pathfinding;

import java.util.ArrayList;
import java.util.List;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Grid;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.Tile;
import nl.abelkrijgtalles.MojangMaps.util.object.LocationUtil;
import org.bukkit.Location;

public class GridUtil {

    public static Grid generateGrid(int width, int height, int xOffset, int zOffset) {

        ArrayList<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile t = new Tile(i, j);
                tiles.add(t);
            }
        }

        Grid grid = new Grid(width, height, tiles);
        grid.setxOffset(xOffset);
        grid.setzOffset(zOffset);
        return grid;

    }

    public static Grid generateGridWithLowestAndHighestLocations(List<Location> locations) {

        Location lowestLocation = LocationUtil.getLowestLocation(locations);
        Location highestLocation = LocationUtil.getHighestLocation(locations);

        System.out.println("min: " + lowestLocation);
        System.out.println("max: " + highestLocation);

        int width = highestLocation.getBlockX() - lowestLocation.getBlockX() + 1;
        int height = highestLocation.getBlockZ() - lowestLocation.getBlockZ() + 1;

        int xOffset = -lowestLocation.getBlockX();
        int zOffset = -lowestLocation.getBlockZ();

        return generateGrid(width, height, xOffset, zOffset);

    }

}

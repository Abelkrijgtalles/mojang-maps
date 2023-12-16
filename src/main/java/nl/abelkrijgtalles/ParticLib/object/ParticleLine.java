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

package nl.abelkrijgtalles.ParticLib.object;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

import nl.abelkrijgtalles.ParticLib.util.LocationUtil;

public class ParticleLine {

    private final List<Location> locations;
    private final Color color;
    private double density = 1;

    public ParticleLine(List<Location> locations, Color color) {

        if (!LocationUtil.areInSameWorld(locations)) {

            throw new IllegalArgumentException("The locations of the created ParticleLine aren't in the same world.");

        }

        this.locations = locations;
        this.color = color;

    }

    public ParticleLine(List<Location> locations, double density, Color color) {

        if (!LocationUtil.areInSameWorld(locations)) {

            throw new IllegalArgumentException("The locations of the created ParticleLine aren't in the same world.");

        }

        this.locations = locations;
        this.density = density;
        this.color = color;

    }

    public ParticleLine(Location location1, Location location2, Color color) {

        List<Location> locations = new ArrayList<Location>();
        locations.add(location1);
        locations.add(location2);

        if (!LocationUtil.areInSameWorld(locations)) {

            throw new IllegalArgumentException("The locations of the created ParticleLine aren't in the same world.");

        }

        this.locations = locations;
        this.color = color;

    }

    public ParticleLine(Location location1, Location location2, double density, Color color) {

        List<Location> locations = new ArrayList<Location>();
        locations.add(location1);
        locations.add(location2);

        if (!LocationUtil.areInSameWorld(locations)) {

            throw new IllegalArgumentException("The locations of the created ParticleLine aren't in the same world.");

        }

        this.locations = locations;
        this.density = density;
        this.color = color;

    }

    public World getWorld() {

        return locations.get(0).getWorld();

    }

    public List<Location> getLocations() {
        return locations;
    }

    public Color getColor() {
        return color;
    }

    public double getDensity() {
        return density;
    }
}

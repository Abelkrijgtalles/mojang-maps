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

import java.util.*;
import java.util.stream.Collectors;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.NewGraph;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.NewGraphLocation;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.NewGraphLocationScorer;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.NewRouteFinder;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import org.bukkit.Location;

public class NewAStar {

    private NewGraph<NewGraphLocation> graph;
    private NewRouteFinder<NewGraphLocation> routeFinder;

    public NewAStar() {

        Set<NewGraphLocation> locations = new HashSet<>();
        Map<Location, Set<Location>> connections = new HashMap<>();

        for (Location location : NodesConfigUtil.getLocations()) {

            locations.add(new NewGraphLocation(location));
            connections.put(location, calculateNeighbours(location).stream().collect(Collectors.toSet()));

        }

        graph = new NewGraph<>(locations, connections);
        routeFinder = new NewRouteFinder<>(graph, new NewGraphLocationScorer(), new NewGraphLocationScorer());

    }

    // expects location is in locations
    public void findRoute(Location start, Location end) {

        List<NewGraphLocation> route = routeFinder.findRoute(graph.getNode(start), graph.getNode(end));

        if (route.isEmpty()) {
            System.out.println("womp path gone");
        }

        route.stream().map(NewGraphLocation::getLocation).collect(Collectors.toList()).forEach(location -> System.out.println(location.toString()));

    }

    private ArrayList<Location> calculateNeighbours(Location location) {

        ArrayList<Location> neighbours = new ArrayList<>();

        neighbours = addToLocationsIfInLocations(neighbours, location.add(-1f, 0f, 0f)); // west

        neighbours = addToLocationsIfInLocations(neighbours, location.add(1f, 0f, 0f)); //east

        neighbours = addToLocationsIfInLocations(neighbours, location.add(0f, 0f, -1f)); //north

        neighbours = addToLocationsIfInLocations(neighbours, location.add(0f, 0f, 1f)); //south

        neighbours = addToLocationsIfInLocations(neighbours, location.add(-1f, 0f, -1f)); //northwest

        neighbours = addToLocationsIfInLocations(neighbours, location.add(1f, 0f, 1f)); //southeast

        neighbours = addToLocationsIfInLocations(neighbours, location.add(1f, 0f, -1f)); //northeast

        neighbours = addToLocationsIfInLocations(neighbours, location.add(-1f, 0f, 1f)); //southwest

        return neighbours;

    }

    private ArrayList<Location> addToLocationsIfInLocations(ArrayList<Location> locations, Location location) {

        if (NodesConfigUtil.isInLocations(location)) {
            locations.add(location);
        }

        return locations;

    }

}

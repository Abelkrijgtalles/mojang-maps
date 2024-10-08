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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.NewGraph;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.NewGraphNode;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.NewLocationNode;
import nl.abelkrijgtalles.MojangMaps.pathfinding.object.NewRouteFinder;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import org.bukkit.Location;

public class NewAstar {

    private NewGraph<NewGraphNode> graph;
    private NewRouteFinder<NewGraphNode> routeFinder;

    public void setup() {

        Set<NewGraphNode> locations = new HashSet<>();
        Map<Location, Set<Location>> connections = new HashMap<>();

        for (Location location : NodesConfigUtil.getLocations()) {

            locations.add(new NewLocationNode(location));

        }

    }

}

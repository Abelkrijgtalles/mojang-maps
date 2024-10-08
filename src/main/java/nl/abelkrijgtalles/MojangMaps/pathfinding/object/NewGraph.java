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

package nl.abelkrijgtalles.MojangMaps.pathfinding.object;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.bukkit.Location;

public class NewGraph<T extends NewGraphNode> {

    private final Set<T> nodes;
    private final Map<Location, Set<Location>> connections;

    public NewGraph(Set<T> nodes, Map<Location, Set<Location>> connections) {

        this.nodes = nodes;
        this.connections = connections;
    }

    public T getNode(Location location) {

        return nodes.stream()
                .filter(node -> node.getLocation().equals(location))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Couldn't find node with location: " + location.serialize()));
    }

    public Set<T> getConnections(T node) {
        return connections.get(node.getLocation()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());
    }

}

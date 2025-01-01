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

package nl.abelkrijgtalles.mojangmaps.common.pathfinding.abstraction;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Graph<T extends GraphNode> {

    private final Set<T> nodes;
    private final Map<UUID, Set<UUID>> connections;

    public Graph(Set<T> nodes, Map<UUID, Set<UUID>> connections) {

        this.nodes = nodes;
        this.connections = connections;
    }

    public T getNode(UUID uuid) {

        return nodes.stream()
                .filter(node -> node.getUUID().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No node found with UUID %s.".formatted(uuid)));

    }

    public Set<T> getConnections(T node) {

        return connections.get(node.getUUID()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());

    }

}

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

package nl.abelkrijgtalles.MojangMaps.util.other;

import java.util.*;
import nl.abelkrijgtalles.MojangMaps.object.NewNode;
import org.bukkit.Location;

public class AStarUtil {

    public static List<Location> findPath(NewNode start, NewNode end) {

        PriorityQueue<NewNode> openQueue = createQueue();
        List<NewNode> closedList = new ArrayList<>();
        boolean endFound = false;

        openQueue.add(start);

        while (true) {

            NewNode currentNode = openQueue.poll();
            if (currentNode == null) break;
            closedList.add(currentNode);

            if (currentNode.getUuid() == end.getUuid()) {
                endFound = true;
                break;
            }

            for (NewNode neighBour : getNeighbours(currentNode, start, end)) {



            }

        }

        if (!endFound) return null;


        return null;

    }

    private static PriorityQueue<NewNode> createQueue() {

        Comparator<NewNode> openQueueComparator = Comparator.comparingInt(NewNode::getfCost).thenComparingInt(NewNode::gethCost);

        return new PriorityQueue<>(openQueueComparator);

    }

    private static int getGridSize() {

        return 5;

    }

    private static List<NewNode> getNeighbours(NewNode parent, NewNode start, NewNode end) {

        List<NewNode> neighbours = new ArrayList<>();
        Location parentLocation = parent.getLocation();
        int gridSize = getGridSize();

        for (int i = -1 * gridSize; i < 2 * gridSize; i += gridSize) {

            for (int j = -1 * gridSize; j < 2 * gridSize; j += gridSize) {

                if (i != 0 && j != 0 && isRoad()) {

                    neighbours.add(getNodeWithOffsetFromLocation(parent.getUuid(), parentLocation, i, j, start, end));

                }

            }

        }

        return neighbours;

    }

    private static boolean isRoad() {

        return true;

    }

    private static NewNode getNodeWithOffsetFromLocation(UUID parent, Location location, int offsetX, int offsetZ, NewNode start, NewNode end) {
        // TODO: find some way to implement this in a 3d system, as it's partially 3d

        Location newLocation = new Location(location.getWorld(), location.getBlockX() + offsetX, location.getBlockY(), location.getBlockZ() + offsetZ);

        return new NewNode(newLocation, parent, start, end);


    }

}

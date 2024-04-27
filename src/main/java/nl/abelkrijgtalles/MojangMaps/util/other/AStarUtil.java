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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
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

            for (NewNode neighBour : getNeighbours()) {


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

    private static List<NewNode> getNeighbours(NewNode parent) {

        List<NewNode> neighbours = new ArrayList<>();
        Location parentLocation = parent.getLocation();


    }

    private static boolean isRoad() {

        return true;

    }

}

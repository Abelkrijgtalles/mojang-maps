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

package nl.abelkrijgtalles.MojangMaps.object;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.LocationUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.NodeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ActiveNavigation {

    /* Explanation of every variable:
        player: Just the UUID of the player, which makes it store less data on the ram.
        beginning: The exact location of the beginning of the navigation. This isn't tied to any node.
        beginningNode: The node object of beginningLocation.
        beginningLocation: The closest node to the beginning.
        destination, destinationNode and destinationLocation: It's beginning, beginningNode and beginningLocation but instead of the beginning, it's the destination.
        nodes: The nodes that the player has to take to reach destinationLocation/destinationNode from beginningLocation/beginningNode.
        activeNodes: The nodes that are in a 5 block radius of the player, and will actually display to the player as a line of particles between the activeNodes.
     */

    // beginningLocation and destinationLocation are the closest location/node to the beginning/destination
    private UUID player;
    private Location beginning;
    private Node beginningNode;
    private Location beginningLocation;
    private Location destination;
    private Node destinationNode;
    private Location destinationLocation;
    private List<Node> nodes;
    private List<Node> activeNodes;

    public ActiveNavigation(UUID player, Location beginning, Location destination) {

        this.player = player;
        setBeginning(beginning);
        setDestination(destination);

    }

    public Location getDestination() {

        return destination;
    }

    public void setDestination(Location destination) {

        this.destination = destination;
        this.destinationLocation = LocationUtil.getClosestLocation(NodesConfigUtil.getLocations(), destination);

        List<Node> nodes = NodeUtil.addAdjacentNodes();
        this.destinationNode = NodeUtil.findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(destinationLocation)));

        Node.calculateShortestPath(beginningNode);
        this.nodes = destinationNode.getShortestPath();

    }

    public Location getBeginning() {

        return beginning;
    }

    public void setBeginning(Location beginning) {

        this.beginning = beginning;
        this.beginningLocation = LocationUtil.getClosestLocation(NodesConfigUtil.getLocations(), beginning);

        List<Node> nodes = NodeUtil.addAdjacentNodes();
        this.beginningNode = NodeUtil.findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(beginningLocation)));

        Node.calculateShortestPath(beginningNode);
        this.nodes = destinationNode.getShortestPath();
    }

    public UUID getPlayer() {

        return player;
    }

    public List<Node> getNodes() {

        return nodes;
    }

    public List<Node> setAndGetActiveNodes(int indexOfStartingNode) {

        activeNodes.clear();
        List<Node> availableNodes = nodes.subList(indexOfStartingNode, nodes.size() - 1);
        for (Node node : availableNodes) {

            if (LocationUtil.isTheSameLocation(Bukkit.getPlayer(player).getLocation(), NodeUtil.getLocationFromNode(node), 5)) {

                activeNodes.add(node);

            } else {
                break;
            }

        }

        return activeNodes;

    }

    public List<Node> getActiveNodesFromPlayerPosition() {

        List<Location> locationsOfNodes = new ArrayList<>();

        for (Node node : nodes) {

            locationsOfNodes.add(NodeUtil.getLocationFromNode(node));

        }

        Location closestLocationToPlayer = LocationUtil.getClosestLocation(locationsOfNodes, Bukkit.getPlayer(player).getLocation());
        return setAndGetActiveNodes(nodes.indexOf(NodeUtil.findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToPlayer)))));

    }

}

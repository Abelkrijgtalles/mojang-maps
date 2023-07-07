package nl.abelkrijgtalles.mojangmaps.util;

import nl.abelkrijgtalles.mojangmaps.managers.dijkstras_algorithm.Node;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class NodeUtil {

    public static List<Node> addAdjacentNodes(List<Location> locations) {
        // Create the node list
        List<Node> nodes = new ArrayList<Node>();

        for (Location location : locations) {
            nodes.add(new Node(Integer.toString(locations.indexOf(location))));
        }

        // Add Adjacent nodes
        for (Node node : nodes) {
            for (Node adjacentNode : nodes) {
                if (adjacentNode.getName() != node.getName()) {
                    // TODO: Clean up this mess of a function
                    node.addAdjacentNode(adjacentNode, LocationUtil.getDistance(getLocationFromNode(locations, node), getLocationFromNode(locations, adjacentNode)));
                }
            }
        }

        return nodes;
    }

    public static Location getLocationFromNode(List<Location> locations, Node node) {

        return locations.get(Integer.valueOf(node.getName()));

    }

}

package nl.abelkrijgtalles.mojangmaps.util;

import nl.abelkrijgtalles.mojangmaps.objects.Node;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class NodeUtil {

    public static List<Node> addAdjacentNodes() {
        // Create the node list
        List<Node> nodes = new ArrayList<Node>();

        List<Location> locations = NodesConfigUtil.getLocations();

        for (Location location : locations) {
            nodes.add(new Node(Integer.toString(locations.indexOf(location))));
        }

        // Add Adjacent nodes
        for (Node node : nodes) {
            for (Node adjacentNode : nodes) {
                if (adjacentNode.getName() != node.getName() && LocationUtil.isTheSameLocation(getLocationFromNode(node), getLocationFromNode(adjacentNode), 10)) {
                    // TODO: Clean up this mess of a function
                    node.addAdjacentNode(adjacentNode, LocationUtil.getDistance(getLocationFromNode(node), getLocationFromNode(adjacentNode)));
                }
            }
        }

        return nodes;
    }

    public static Location getLocationFromNode(Node node) {

        return NodesConfigUtil.getLocations().get(Integer.valueOf(node.getName()));

    }

}

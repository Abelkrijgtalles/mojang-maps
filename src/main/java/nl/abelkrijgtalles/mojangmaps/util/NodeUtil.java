package nl.abelkrijgtalles.mojangmaps.util;

import nl.abelkrijgtalles.mojangmaps.objects.Node;
import nl.abelkrijgtalles.mojangmaps.util.config.NodesConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

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

        return NodesConfigUtil.getLocations().get(Integer.parseInt(node.getName()));

    }

    public static void registerLocation(Location location) {
        // TODO: inLocation needs to be renamed in the following functions
        List<Location> locations = (List<Location>) NodesConfigUtil.get().getList("locations");
        for (Location inLocation : locations) {
            if (LocationUtil.isTheSameLocation(location, inLocation, 0)) {
                return;
            }
        }

        NodesConfigUtil.addLocation(location);

    }

    public static void registerLocation(Player p, Location location) {

        List<Location> locations = (List<Location>) NodesConfigUtil.get().getList("locations");
        for (Location inLocation : locations) {
            if (LocationUtil.isTheSameLocation(location, inLocation, 0)) {
                p.sendMessage(ChatColor.RED + "This location is already registered.");
                return;
            }
        }

        NodesConfigUtil.addLocation(location);

        p.sendMessage(ChatColor.YELLOW + "Registered a location at x: " + p.getLocation().getBlockX() + ", z: " + p.getLocation().getBlockZ() + ".");

    }

}

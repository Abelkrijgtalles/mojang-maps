package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.objects.Node;
import nl.abelkrijgtalles.mojangmaps.util.LocationUtil;
import nl.abelkrijgtalles.mojangmaps.util.NodeUtil;
import nl.abelkrijgtalles.mojangmaps.util.NodesConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoToCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {

            Player p = (Player) commandSender;
            List<Integer> coordinates = new ArrayList<>();

            if (strings.length < 3) {

                p.sendMessage(ChatColor.RED + "You did not run the command with arguments. Please run it again with the 3 arguments.");
                p.sendMessage(ChatColor.YELLOW + "Example: " + ChatColor.WHITE + "/goto <x> <y> <z>.");

                return true;

            }

            if (strings.length > 3) {

                p.sendMessage(ChatColor.RED + "You have run the command with too many arguments. Please run it again with the 3 arguments.");
                p.sendMessage(ChatColor.YELLOW + "Example: " + ChatColor.WHITE + "/goto <x> <y> <z>.");

                return true;

            }

            for (String coordinate : strings) {

                try {

                    Integer.parseInt(coordinate);

                } catch (NumberFormatException e) {

                    p.sendMessage(ChatColor.RED + "Invalid coordinates. Please run it again with the right coordinates.");
                    p.sendMessage(ChatColor.YELLOW + "Example: " + ChatColor.WHITE + "/goto <x> <y> <z>.");

                    return true;

                }

            }

            Location location = new Location(p.getWorld(), Double.parseDouble(strings[0]), Double.parseDouble(strings[1]), Double.parseDouble(strings[2]));
            Location closestLocationToPlayer = LocationUtil.getClosestLocation(p.getLocation());
            Location closestLocationToLocation = LocationUtil.getClosestLocation(location);

            List<Node> nodes = NodeUtil.addAdjacentNodes();
            Node playerNode = findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToPlayer)));
            Node locationNode = findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToLocation)));

            Node.calculateShortestPath(playerNode);

            Node.printPaths(Collections.singletonList(locationNode), p);
            p.sendMessage("Then finally go to X: " + location.getBlockX() + " Z: " + location.getBlockZ());
        }
        return true;
    }

    private Node findNodeByName(List<Node> nodes, String name) {
        return nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}

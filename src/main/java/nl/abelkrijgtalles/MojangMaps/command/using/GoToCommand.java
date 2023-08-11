package nl.abelkrijgtalles.MojangMaps.command.using;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.object.Node;
import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.LocationUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.NodeUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class GoToCommand implements CommandExecutor {

    private final MojangMaps plugin;

    public GoToCommand(MojangMaps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {

            Player p = (Player) commandSender;

            if (strings.length < 3) {

                p.sendMessage(ChatColor.RED + MessageUtil.getMessage("noarguments").formatted(3));
                p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("example").formatted(ChatColor.WHITE + "/goto <x> <y> <z>."));

                return true;

            }

            if (strings.length > 3) {

                p.sendMessage(ChatColor.RED + MessageUtil.getMessage("toomanyarguments").formatted(3));
                p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("example").formatted(ChatColor.WHITE + "/goto <x> <y> <z>."));

                return true;

            }

            for (String coordinate : strings) {

                try {

                    Integer.parseInt(coordinate);

                } catch (NumberFormatException e) {

                    p.sendMessage(ChatColor.RED + MessageUtil.getMessage("invalidcoordinates"));
                    p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("example").formatted(ChatColor.WHITE + "/goto <x> <y> <z>."));

                    return true;

                }

            }

            Location location = new Location(p.getWorld(), Double.parseDouble(strings[0]), Double.parseDouble(strings[1]), Double.parseDouble(strings[2]));
            Location closestLocationToPlayer = LocationUtil.getClosestLocation(p.getLocation());
            Location closestLocationToLocation = LocationUtil.getClosestLocation(location);

            List<Node> nodes = NodeUtil.addAdjacentNodes();
            Node playerNode = findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToPlayer)));
            Node locationNode = findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToLocation)));

            p.sendMessage(ChatColor.YELLOW + "Loading, this could take some time.");
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> Node.calculateShortestPath(playerNode));

            Node.printPaths(Collections.singletonList(locationNode), p);
            p.sendMessage(MessageUtil.getMessage("finallygoto").formatted(location.getBlockX(), location.getBlockZ()));
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

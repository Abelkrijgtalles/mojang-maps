package nl.abelkrijgtalles.MojangMaps.command.using;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.object.Node;
import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.LocationUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.NodeUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class NavigationCommand implements CommandExecutor {

    private final MojangMaps plugin;

    public NavigationCommand(MojangMaps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player p) {

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

            openGUI(p, strings);

        }

        return true;
    }

    private void openGUI(Player p, String[] strings) {

        Location location = new Location(p.getWorld(), Double.parseDouble(strings[0]), Double.parseDouble(strings[1]), Double.parseDouble(strings[2]));
        Location closestLocationToPlayer = LocationUtil.getClosestLocation(p.getLocation());
        Location closestLocationToLocation = LocationUtil.getClosestLocation(location);

        List<Node> nodes = NodeUtil.addAdjacentNodes();
        Node playerNode = findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToPlayer)));
        Node locationNode = findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToLocation)));

        p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("load"));
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            // starting timer
            final int[] ticksWhileCalculating = {0};
            int taskID = new BukkitRunnable() {
                @Override
                public void run() {

                    ticksWhileCalculating[0] += 1;

                }
            }.runTaskTimer(plugin, 0, 1).getTaskId();

            Node.calculateShortestPath(playerNode);

            Bukkit.getScheduler().cancelTask(taskID);
            p.sendMessage(MessageUtil.getMessage("calcins").formatted(ticksWhileCalculating[0] * .05));

            List<Node> shortestPath = locationNode.getShortestPath();

            SGMenu menu = MojangMaps.spiGUI.create("Navigation", (int) Math.ceil(shortestPath.size() / 9.0));

            for (Node node : shortestPath) {

                SGButton button = new SGButton(

                        new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).name(node.getLocationText()).build()

                );

                menu.addButton(button);

            }

            Bukkit.getScheduler().runTask(plugin, () -> p.openInventory(menu.getInventory()));

        });


    }

    private Node findNodeByName(List<Node> nodes, String name) {
        return nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}

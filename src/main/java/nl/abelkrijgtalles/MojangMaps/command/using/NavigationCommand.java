/*
 * Copyright (C) 2023 Abel van Hulst/Abelkrijgtalles/Abelpro678
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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

        if (playerNode == null || locationNode == null) {
            p.sendMessage(ChatColor.RED + MessageUtil.getMessage("nonodesfound"));
            return;
        }

        p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("load"));
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            // starting timer
            GoToCommand.calculateAndTime(p, playerNode, plugin);

            List<Node> shortestPath = locationNode.getShortestPath();

            SGMenu menu = MojangMaps.spiGUI.create(MessageUtil.getMessage("navigation"), (int) Math.ceil((shortestPath.size() + 1) / 9.0));

            for (Node node : shortestPath) {

                SGButton button = new SGButton(

                        new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).name(node.getLocationText()).build()

                );

                menu.addButton(button);

            }

            SGButton button = new SGButton(

                    new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).name(MessageUtil.getMessage("finallygoto").formatted(strings[0], strings[2])).build()

            );

            menu.addButton(button);

            Bukkit.getScheduler().runTask(plugin, () -> p.openInventory(menu.getInventory()));
            p.sendMessage(MessageUtil.getMessage("blocksprediction").replace("%s\n", "").formatted(locationNode.getDistance()));

        });


    }

    private Node findNodeByName(List<Node> nodes, String name) {
        return nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}

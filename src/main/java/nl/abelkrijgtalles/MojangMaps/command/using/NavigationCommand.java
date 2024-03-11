/*
 * MojangMaps
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.command.using;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import java.util.List;
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
import org.bukkit.entity.Player;

public class NavigationCommand {

    public NavigationCommand(Player p, CommandArguments commandArguments) throws WrapperCommandSyntaxException {

        openGUI(p, commandArguments);

    }

    private void openGUI(Player p, CommandArguments commandArguments) throws WrapperCommandSyntaxException {

        MojangMaps plugin = MojangMaps.getPlugin(MojangMaps.class);

        Location location = (Location) commandArguments.get("location");
        Location closestLocationToPlayer = LocationUtil.getClosestLocation(p.getLocation());
        Location closestLocationToLocation = LocationUtil.getClosestLocation(location);

        List<Node> nodes = NodeUtil.addAdjacentNodes();
        Node playerNode = NodeUtil.findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToPlayer)));
        Node locationNode = NodeUtil.findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToLocation)));

        if (playerNode == null || locationNode == null) {
            throw CommandAPI.failWithString(MessageUtil.getMessage("nonodesfound"));
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

                    new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).name(MessageUtil.getMessage("finallygoto").formatted(location.getX(), location.getZ())).build()

            );

            menu.addButton(button);

            Bukkit.getScheduler().runTask(plugin, () -> p.openInventory(menu.getInventory()));
            p.sendMessage(MessageUtil.getMessage("blocksprediction").replace("%s\n", "").formatted(locationNode.getDistance()));

        });


    }

}

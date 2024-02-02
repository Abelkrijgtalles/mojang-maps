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

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import java.util.Collections;
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
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GoToCommand {

    public GoToCommand(Player p, CommandArguments commandArguments) throws WrapperCommandSyntaxException {

        MojangMaps plugin = MojangMaps.getPlugin(MojangMaps.class);

        Location location = (Location) commandArguments.get("location");
        Location closestLocationToPlayer = LocationUtil.getClosestLocation(p.getLocation());
        Location closestLocationToLocation = LocationUtil.getClosestLocation(location);

        List<Node> nodes = NodeUtil.addAdjacentNodes();
        Node playerNode = findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToPlayer)));
        Node locationNode = findNodeByName(nodes, String.valueOf(NodesConfigUtil.getLocations().indexOf(closestLocationToLocation)));

        if (playerNode == null || locationNode == null) {
            throw CommandAPI.failWithString(MessageUtil.getMessage("nonodesfound"));
        }

        p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("load"));
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            // start timer
            calculateAndTime(p, playerNode, plugin);

            Node.printPaths(Collections.singletonList(locationNode), p);
            p.sendMessage(MessageUtil.getMessage("finallygoto").formatted(location.getBlockX(), location.getBlockZ()));
        });

    }

    public static void calculateAndTime(Player p, Node playerNode, MojangMaps plugin) {

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
    }

    private Node findNodeByName(List<Node> nodes, String name) {

        return nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}

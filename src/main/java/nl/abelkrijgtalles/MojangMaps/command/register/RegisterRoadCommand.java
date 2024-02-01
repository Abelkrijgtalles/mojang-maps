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

package nl.abelkrijgtalles.MojangMaps.command.register;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import java.util.ArrayList;
import java.util.List;
import nl.abelkrijgtalles.MojangMaps.object.Road;
import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.RoadUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RegisterRoadCommand {

    public RegisterRoadCommand(Player p, CommandArguments args) throws WrapperCommandSyntaxException {

        String[] strings = (String[]) args.get("locations");
        // TODO: rename this variable
        List<Integer> coordinates = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        List<Integer> locationsPointers = new ArrayList<>();

        if (strings.length <= 2) {

            throw CommandAPI.failWithString(MessageUtil.getMessage("noargumentsother"));

        }

        if (strings.length % 3 == 0) {

            if (args.get("name") != null) {

                String name = (String) args.get("name");

                for (String coordinate : strings) {

                    try {

                        Integer.parseInt(coordinate);

                    } catch (NumberFormatException e) {

                        throw CommandAPI.failWithString(MessageUtil.getMessage("invalidcoordinates"));

                    }

                    coordinates.add(Integer.parseInt(coordinate));

                }

                for (int i = 0; i < strings.length; i += 1) {

                    if ((i + 1) % 3 == 0) {

                        int x = Integer.parseInt(strings[i - 2]);
                        int y = Integer.parseInt(strings[i - 1]);
                        int z = Integer.parseInt(strings[i]);

                        locations.add(new Location(p.getWorld(), x, y, z));

                    }

                }

                RoadUtil.addMoreLocations(p, locations);

                for (Location location : locations) {

                    NodesConfigUtil.addLocation(location);
                    locationsPointers.add(NodesConfigUtil.getLocations().size() - 1);

                }

                NodesConfigUtil.addRoad(new Road(name, locationsPointers));
                p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("registeredroad"));

            } else {

                for (String coordinate : strings) {

                    try {

                        Integer.parseInt(coordinate);

                    } catch (NumberFormatException e) {

                        throw CommandAPI.failWithString(MessageUtil.getMessage("invalidcoordinates"));

                    }

                    coordinates.add(Integer.parseInt(coordinate));

                }

                for (int i = 0; i < strings.length; i += 1) {

                    if ((i + 1) % 3 == 0) {

                        int x = Integer.parseInt(strings[i - 2]);
                        int y = Integer.parseInt(strings[i - 1]);
                        int z = Integer.parseInt(strings[i]);

                        locations.add(new Location(p.getWorld(), x, y, z));

                    }

                }

                RoadUtil.addMoreLocations(p, locations);

                for (Location location : locations) {

                    NodesConfigUtil.addLocation(location);
                    locationsPointers.add(NodesConfigUtil.getLocations().size() - 1);

                }

                NodesConfigUtil.addRoad(new Road(locationsPointers));
                p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("registeredroad"));

            }

        } else {

            throw CommandAPI.failWithString(MessageUtil.getMessage("invalidcoordinates"));

        }

    }

}

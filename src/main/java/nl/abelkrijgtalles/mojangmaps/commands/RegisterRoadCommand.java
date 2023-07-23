package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.objects.Road;
import nl.abelkrijgtalles.mojangmaps.util.LocationUtil;
import nl.abelkrijgtalles.mojangmaps.util.NodesConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RegisterRoadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player p) {

            boolean hasName = false;
            // TODO: rename this variable
            List<Integer> coordinates = new ArrayList<>();
            List<Location> locations = new ArrayList<>();
            List<Integer> locationsPointers = new ArrayList<>();

            if (strings.length < 3) {

                p.sendMessage(ChatColor.RED + "You did not run the command with arguments. Please run it again with the right arguments.");
                p.sendMessage(ChatColor.YELLOW + "Example: " + ChatColor.WHITE + "/registerroad <(optional) name of road> <coordinates of the locations as x y z with a space between arguments>.");

                return true;

            }

            try {

                Integer.parseInt(strings[0]);

            } catch (NumberFormatException e) {

                hasName = true;

            }

            if ((hasName && (strings.length - 1) % 3 == 0) || (!hasName && strings.length % 3 == 0)) {

                if (hasName) {

                    String name = strings[0];

                    for (int i = 0; i < strings.length; i++) {

                        if (i != 0) {

                            strings[i - 1] = strings[i];

                        }

                    }

                    for (String coordinate : strings) {

                        try {

                            Integer.parseInt(coordinate);

                        } catch (NumberFormatException e) {

                            p.sendMessage(ChatColor.RED + "Invalid coordinates. Please run it again with the right coordinates.");
                            p.sendMessage(ChatColor.YELLOW + "Example: " + ChatColor.WHITE + "/registerroad <(optional) name of road> <coordinates of the locations as x y z with a space between arguments>.");

                            return true;

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

                    LocationUtil.addMoreLocations(p, locations);

                    for (Location location : locations) {

                        NodesConfigUtil.addLocation(location);
                        locationsPointers.add(NodesConfigUtil.getLocations().size() - 1);

                    }

                    NodesConfigUtil.addRoad(new Road(name, locationsPointers));
                    p.sendMessage(ChatColor.YELLOW + "Registered a new road");

                } else {

                    for (String coordinate : strings) {

                        try {

                            Integer.parseInt(coordinate);

                        } catch (NumberFormatException e) {

                            p.sendMessage(ChatColor.RED + "Invalid coordinates. Please run it again with the right coordinates.");
                            p.sendMessage(ChatColor.YELLOW + "Example: " + ChatColor.WHITE + "/registerroad <(optional) name of road> <coordinates of the locations as x y z with a space between arguments>.");

                            return true;

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

                    LocationUtil.addMoreLocations(p, locations);

                    for (Location location : locations) {

                        NodesConfigUtil.addLocation(location);
                        locationsPointers.add(NodesConfigUtil.getLocations().size() - 1);

                    }

                    NodesConfigUtil.addRoad(new Road(locationsPointers));
                    p.sendMessage(ChatColor.YELLOW + "Registered a new road");

                }

            } else {

                p.sendMessage(ChatColor.RED + "Invalid coordinates. Please run it again with the right coordinates.");
                p.sendMessage(ChatColor.YELLOW + "Example: " + ChatColor.WHITE + "/registerroad <(optional) name of road> <coordinates of the locations as x y z with a space between arguments>.");

            }

        }

        return true;
    }
}

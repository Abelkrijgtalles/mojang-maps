package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.objects.Road;
import nl.abelkrijgtalles.mojangmaps.util.MessageUtil;
import nl.abelkrijgtalles.mojangmaps.util.NodesConfigUtil;
import nl.abelkrijgtalles.mojangmaps.util.RoadUtil;
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

                p.sendMessage(ChatColor.RED + MessageUtil.getMessage("noargumentsother"));
                p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("example") + ChatColor.WHITE + "/registerroad <" + MessageUtil.getMessage("registerroadarguments") + ">.");

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

                            p.sendMessage(ChatColor.RED + MessageUtil.getMessage("invalidcoordinates"));
                            p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("example") + ChatColor.WHITE + "/registerroad <" + MessageUtil.getMessage("registerroadarguments") + ">.");

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

                            p.sendMessage(ChatColor.RED + MessageUtil.getMessage("invalidcoordinates"));
                            p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("example") + ChatColor.WHITE + "/registerroad <" + MessageUtil.getMessage("registerroadarguments") + ">.");

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

                    RoadUtil.addMoreLocations(p, locations);

                    for (Location location : locations) {

                        NodesConfigUtil.addLocation(location);
                        locationsPointers.add(NodesConfigUtil.getLocations().size() - 1);

                    }

                    NodesConfigUtil.addRoad(new Road(locationsPointers));
                    p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("registeredroad"));

                }

            } else {

                p.sendMessage(ChatColor.RED + MessageUtil.getMessage("invalidcoordinates"));
                p.sendMessage(ChatColor.YELLOW + MessageUtil.getMessage("example") + ChatColor.WHITE + "/registerroad <" + MessageUtil.getMessage("registerroadarguments") + ">.");

            }

        }

        return true;
    }
}

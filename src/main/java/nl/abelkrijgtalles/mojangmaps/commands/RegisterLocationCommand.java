package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.util.LocationUtil;
import nl.abelkrijgtalles.mojangmaps.util.NodesConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RegisterLocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player p = (Player) commandSender;

            List<Location> locations = (List<Location>) NodesConfigUtil.get().getList("locations");
            for (Location location : locations) {
                if (LocationUtil.isTheSameLocation(p.getLocation(), location, 0)) {
                    p.sendMessage(ChatColor.RED + "This location is already registered.");
                    return true;
                }
            }
            locations.add(p.getLocation());
            NodesConfigUtil.save();
            p.sendMessage(ChatColor.YELLOW + "Registered a location at x: " + p.getLocation().getBlockX() + ", z: " + p.getLocation().getBlockZ() + ".");

        }

        return true;
    }
}

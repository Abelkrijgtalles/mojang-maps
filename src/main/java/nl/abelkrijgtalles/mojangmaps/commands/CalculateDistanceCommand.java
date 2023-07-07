package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.managers.config.NodesConfig;
import nl.abelkrijgtalles.mojangmaps.managers.dijkstras_algorithm.Node;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CalculateDistanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        List<Location> locations = (List<Location>) NodesConfig.get().getList("locations");
        List<Node> nodes = new ArrayList<Node>();

        for (Location location : locations) {
            nodes.add(new Node(Integer.toString(locations.indexOf(location))));
        }


        return true;
    }
}

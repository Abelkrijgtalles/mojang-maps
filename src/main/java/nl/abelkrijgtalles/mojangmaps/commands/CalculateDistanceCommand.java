package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.objects.Node;
import nl.abelkrijgtalles.mojangmaps.util.NodeUtil;
import nl.abelkrijgtalles.mojangmaps.util.NodesConfigUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CalculateDistanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            List<Location> locations = NodesConfigUtil.getLocations();
            locations.add(p.getLocation());

            List<Node> nodes = NodeUtil.addAdjacentNodes();

            Node.calculateShortestPath(nodes.get(locations.size() - 1));
            Node.printPaths(nodes, p);
        }


        return true;
    }
}

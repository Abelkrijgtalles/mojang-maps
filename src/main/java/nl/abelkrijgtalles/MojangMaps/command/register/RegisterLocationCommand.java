package nl.abelkrijgtalles.MojangMaps.command.register;

import nl.abelkrijgtalles.MojangMaps.util.object.NodeUtil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterLocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player p = (Player) commandSender;

            NodeUtil.registerLocation(p, p.getLocation());

        }

        return true;
    }
}

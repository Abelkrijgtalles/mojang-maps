package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.util.MessageUtil;
import nl.abelkrijgtalles.mojangmaps.util.RoadUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhereAmIStandingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player p) {

            if (!RoadUtil.getLocationMessage(p).equals("")) {

                p.sendMessage(RoadUtil.getLocationMessage(p));

            } else {

                p.sendMessage(MessageUtil.getMessage("notonaroad"));

            }

        }

        return true;
    }
}

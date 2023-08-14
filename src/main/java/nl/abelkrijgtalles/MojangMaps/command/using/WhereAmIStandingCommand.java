package nl.abelkrijgtalles.MojangMaps.command.using;

import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import nl.abelkrijgtalles.MojangMaps.util.object.RoadUtil;

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

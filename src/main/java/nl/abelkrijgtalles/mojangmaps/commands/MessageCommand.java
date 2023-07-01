package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.files.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            player.sendMessage(CustomConfig.get().getString("message"));

        }

        return true;
    }
}

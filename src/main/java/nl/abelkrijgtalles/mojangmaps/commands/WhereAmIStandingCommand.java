package nl.abelkrijgtalles.mojangmaps.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhereAmIStandingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player p = (Player) commandSender;

            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Hello"));

        }

        return true;
    }
}

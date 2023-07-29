package nl.abelkrijgtalles.mojangmaps.commands;

import nl.abelkrijgtalles.mojangmaps.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class TestCommand implements CommandExecutor {

    public static void testConsole() {

        Logger logger = Bukkit.getLogger();
        logger.info(MessageUtil.getMessage("nolocationroad"));
        logger.info(MessageUtil.getMessage("unnamedroad"));
        logger.info(MessageUtil.getMessage("locationalreadyregistered"));
        logger.info(MessageUtil.getMessage("registerlocation").formatted(69, 69));
        logger.info(MessageUtil.getMessage("currentlyon").formatted("BallsStraat"));
        logger.info(MessageUtil.getMessage("blocksprediction").formatted("X: 69 Z: 69 (BallsStraat)", 69));
        logger.info(MessageUtil.getMessage("thengoto").formatted(MessageUtil.getMessage("blocksprediction").formatted("X: 69 Z: 69 (BallsStraat)", 69)));
        logger.info(MessageUtil.getMessage("noarguments").formatted(69));
        logger.info(MessageUtil.getMessage("example"));
        logger.info(MessageUtil.getMessage("toomanyarguments").formatted(69));
        logger.info(MessageUtil.getMessage("invalidcoordinates"));
        logger.info(MessageUtil.getMessage("finallygoto").formatted(69, 69));
        logger.info(MessageUtil.getMessage("registerroadarguments"));
        logger.info(MessageUtil.getMessage("registeredroad"));
        logger.info(MessageUtil.getMessage("notonaroad"));

    }

    public static void testChat(Player p) {

        p.sendMessage(MessageUtil.getMessage("nolocationroad"));
        p.sendMessage(MessageUtil.getMessage("unnamedroad"));
        p.sendMessage(MessageUtil.getMessage("locationalreadyregistered"));
        p.sendMessage(MessageUtil.getMessage("registerlocation").formatted(69, 69));
        p.sendMessage(MessageUtil.getMessage("currentlyon").formatted("BallsStraat"));
        p.sendMessage(MessageUtil.getMessage("blocksprediction").formatted("X: 69 Z: 69 (BallsStraat)", 69));
        p.sendMessage(MessageUtil.getMessage("thengoto").formatted(MessageUtil.getMessage("blocksprediction").formatted("X: 69 Z: 69 (BallsStraat)", 69)));
        p.sendMessage(MessageUtil.getMessage("noarguments").formatted(69));
        p.sendMessage(MessageUtil.getMessage("example"));
        p.sendMessage(MessageUtil.getMessage("toomanyarguments").formatted(69));
        p.sendMessage(MessageUtil.getMessage("invalidcoordinates"));
        p.sendMessage(MessageUtil.getMessage("finallygoto").formatted(69, 69));
        p.sendMessage(MessageUtil.getMessage("registerroadarguments"));
        p.sendMessage(MessageUtil.getMessage("registeredroad"));
        p.sendMessage(MessageUtil.getMessage("notonaroad"));

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player p) {

            p.sendMessage("Eerst console");
            testConsole();
            p.sendMessage("Nu chat");
            testChat(p);


        }

        return true;
    }

}

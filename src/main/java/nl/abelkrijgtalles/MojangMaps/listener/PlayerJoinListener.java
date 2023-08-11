package nl.abelkrijgtalles.MojangMaps.listener;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final MojangMaps plugin;

    public PlayerJoinListener(MojangMaps plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        if (plugin.isPluginOutdated && e.getPlayer().isOp()) {

            e.getPlayer().sendMessage(ChatColor.RED + MessageUtil.getMessage("outdated").formatted(ChatColor.UNDERLINE + "https://github.com/Abelkrijgtalles/mojang-maps/releases/latest"));

        }

    }

}

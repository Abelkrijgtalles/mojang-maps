package nl.abelkrijgtalles.MojangMaps.listener;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.object.RoadUtil;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerWalkListener implements Listener {

    private final MojangMaps plugin;

    public PlayerWalkListener(MojangMaps plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        if (plugin.getConfig().getBoolean("street-actionbar") && p.hasPermission("mojangmaps.using.viewlocation")) {

            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(RoadUtil.getLocationMessage(p)));

        }

    }

}

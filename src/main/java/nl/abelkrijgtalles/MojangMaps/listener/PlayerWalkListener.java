/*
 * Copyright (C) 2023 Abel van Hulst/Abelkrijgtalles/Abelpro678
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.listener;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.object.RoadUtil;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class PlayerWalkListener implements Listener {

    private final MojangMaps plugin;

    public PlayerWalkListener(MojangMaps plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        // current fix is made by @ajh123, see more in issue #6
        String roadMsg = RoadUtil.getLocationMessage(p); // ask the RoadUtil for the location message
        if (plugin.getConfig().getBoolean("street-actionbar") && p.hasPermission("mojangmaps.using.viewlocation") && !roadMsg.trim().isEmpty()) {

            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(roadMsg));

        }
        // the fix by @ajh123 made in issue #6 ends here

    }

}

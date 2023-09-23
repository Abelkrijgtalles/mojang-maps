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

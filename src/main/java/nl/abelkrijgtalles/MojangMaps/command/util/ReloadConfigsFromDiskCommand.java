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

package nl.abelkrijgtalles.MojangMaps.command.util;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.TranslationUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ReloadConfigsFromDiskCommand implements CommandExecutor {

    private final MojangMaps plugin;

    public ReloadConfigsFromDiskCommand(MojangMaps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        File config = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("MojangMaps")).getDataFolder(), "config.yml");

        NodesConfigUtil.reload();
        try {
            plugin.getConfig().load(config);
        } catch (IOException | InvalidConfigurationException e) {
            MojangMaps.getMMLogger().warning("Could not load config.");
        }

        TranslationUtil translationUtil = new TranslationUtil(plugin);
        translationUtil.updateTranslations();

        return true;
    }
}

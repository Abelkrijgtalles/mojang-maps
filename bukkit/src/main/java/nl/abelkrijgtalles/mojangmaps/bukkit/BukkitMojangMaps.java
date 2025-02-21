/*
 * mojang-maps.bukkit.main
 * Copyright (C) 2025 Abel van Hulst/Abelkrijgtalles/Abelpro678
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.mojangmaps.bukkit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import nl.abelkrijgtalles.mojangmaps.bukkit.platform.BukkitLoaderInfo;
import nl.abelkrijgtalles.mojangmaps.bukkit.platform.command.BukkitCommand;
import nl.abelkrijgtalles.mojangmaps.bukkit.platform.world.BukkitLevel;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.command.Commands;
import nl.abelkrijgtalles.mojangmaps.platform.command.Command;
import nl.abelkrijgtalles.mojangmaps.platform.command.CommandSource;
import nl.abelkrijgtalles.mojangmaps.platform.command.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class BukkitMojangMaps extends JavaPlugin {

    public static JavaPlugin INSTANCE;
    public static boolean isRunningTests;

    private static SimpleCommandMap scm;
    private SimplePluginManager spm;

    public static SimpleCommandMap getCommandMap() {

        return scm;
    }

    @Override
    public void onEnable() {

        INSTANCE = this;
        MojangMaps.init(new BukkitLoaderInfo(isRunningTests));

        if (!MojangMaps.loaderInfo.isRunningTests()) {
            setupSimpleCommandMap();
            List<BukkitCommand> convertedCommands = new ArrayList<>();
            for (Command command : Commands.getCommands()) {

                convertedCommands.add(new BukkitCommand(command.getCommand()) {

                    @Override
                    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {

                        CommandSource source;

                        if (sender instanceof Player p)
                            source = new CommandSource(new Permission(false), new BukkitLevel(p.getWorld()));
                        else source = new CommandSource(new Permission(true));

                        return command.run(source);
                    }
                });

            }

            registerCommands(convertedCommands);
        }

    }

    private void registerCommands(List<BukkitCommand> commands) {

        commands.forEach(command -> scm.register(MojangMaps.MOD_ID, command));
    }

    private void setupSimpleCommandMap() {

        spm = (SimplePluginManager) INSTANCE.getServer().getPluginManager();
        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (Exception e) {
            MojangMaps.LOGGER.error("Unable to get SimplePluginManager.commandMap: {}", String.valueOf(e));
        }
        f.setAccessible(true);
        try {
            scm = (SimpleCommandMap) f.get(spm);
        } catch (Exception e) {
            MojangMaps.LOGGER.error("Unable to get a plugin manager of the commandMap or something, I don't really know. Just copied the code from the internet: {}", String.valueOf(e));
        }

    }

}

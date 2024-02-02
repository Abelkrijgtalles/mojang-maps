/*
 * MojangMaps
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps;

import com.google.gson.JsonObject;
import com.samjakob.spigui.SpiGUI;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.LocationType;
import dev.jorel.commandapi.arguments.StringArgument;
import java.util.*;
import java.util.logging.Logger;
import nl.abelkrijgtalles.MojangMaps.command.GiveRegisterItemCommand;
import nl.abelkrijgtalles.MojangMaps.command.register.RegisterLocationCommand;
import nl.abelkrijgtalles.MojangMaps.command.register.RegisterRoadCommand;
import nl.abelkrijgtalles.MojangMaps.command.using.GoToCommand;
import nl.abelkrijgtalles.MojangMaps.command.using.NavigationCommand;
import nl.abelkrijgtalles.MojangMaps.command.using.WhereAmIStandingCommand;
import nl.abelkrijgtalles.MojangMaps.command.util.ReloadConfigsFromDiskCommand;
import nl.abelkrijgtalles.MojangMaps.listener.PlayerJoinListener;
import nl.abelkrijgtalles.MojangMaps.listener.PlayerWalkListener;
import nl.abelkrijgtalles.MojangMaps.listener.RegisterItemListener;
import nl.abelkrijgtalles.MojangMaps.object.Road;
import nl.abelkrijgtalles.MojangMaps.util.file.MessageUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.TranslationUtil;
import nl.abelkrijgtalles.MojangMaps.util.other.HTTPUtil;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.DrilldownPie;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class MojangMaps extends JavaPlugin {

    public static boolean isOnline = true;
    public static SpiGUI spiGUI;
    public static boolean isCreatingARoad = false;
    public static List<Location> creatingRoadLocations = new ArrayList<>();
    public static int creatingRoadParticleTaskId = -1;
    public boolean isPluginOutdated = false;

    private static void addLanguageChart(Metrics metrics, MojangMaps plugin) {

        metrics.addCustomChart(new DrilldownPie("language", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String language = plugin.getConfig().getString("language");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(language, 1);

            switch (Objects.requireNonNull(language)) {
                case "af" -> map.put("Afrikaans", entry);
                case "ar" -> map.put("Arabic", entry);
                case "ca" -> map.put("Catalan", entry);
                case "cs" -> map.put("Czech", entry);
                case "da" -> map.put("Danish", entry);
                case "de" -> map.put("German", entry);
                case "el" -> map.put("Greek", entry);
                case "en" -> map.put("English", entry);
                case "es-ES" -> map.put("Spanish", entry);
                case "fi" -> map.put("Finnish", entry);
                case "fr" -> map.put("French", entry);
                case "he" -> map.put("Hebrew", entry);
                case "hu" -> map.put("Hungarian", entry);
                case "it" -> map.put("Italian", entry);
                case "ja" -> map.put("Japanese", entry);
                case "ko" -> map.put("Korean", entry);
                case "la-LA" -> map.put("Latin", entry);
                case "nl" -> map.put("Dutch", entry);
                case "no" -> map.put("Norwegian", entry);
                case "pl" -> map.put("Polish", entry);
                case "pt-BR" -> map.put("Portuguese, Brazilian", entry);
                case "pt-PT" -> map.put("Portuguese", entry);
                case "ro" -> map.put("Romanian", entry);
                case "ru" -> map.put("Russian", entry);
                case "sr" -> map.put("Serbian (Cyrillic)", entry);
                case "sv-SE" -> map.put("Swedish", entry);
                case "tr" -> map.put("Turkish", entry);
                case "uk" -> map.put("Ukrainian", entry);
                case "vi" -> map.put("Vietnamese", entry);
                case "zh-CN" -> map.put("Chinese Simplified", entry);
                case "zh-TW" -> map.put("Chinese Traditional", entry);
                default -> map.put("Other", entry);
            }

            return map;
        }));

    }

    public static Logger getMMLogger() {

        return MojangMaps.getPlugin(MojangMaps.class).getLogger();

    }

    @Override
    public void onDisable() {

//        CommandAPI.onDisable();

        if (isPluginOutdated) {

            getLogger().warning("Don't forget to update Mojang Maps.");

        }

    }

    @Override
    public void onLoad() {

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(true).useLatestNMSVersion(true));

    }

    @Override
    public void onEnable() {

        // Bstats init
        int pluginId = 19295;
        Metrics metrics = new Metrics(this, pluginId);
        addLanguageChart(metrics, this);

        // Config init
        ConfigurationSerialization.registerClass(Road.class);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        NodesConfigUtil.setup();
        TranslationUtil translationUtil = new TranslationUtil();
        translationUtil.updateTranslations();

        // Update stuff
        checkVersion();
        if (isPluginOutdated) {

            getLogger().warning("Mojang Maps is outdated, please download the newest version at: https://github.com/Abelkrijgtalles/mojang-maps/releases/latest");

        }

        // Commands Init
        CommandAPI.onEnable();
        new CommandAPICommand("registerlocation")
                .withShortDescription("Register a location.")
                .withPermission(CommandPermission.fromString("mojangmaps.register.location"))
                .withAliases("createlocation")
                .executesPlayer(((player, commandArguments) -> {
                    new RegisterLocationCommand(player);
                }))
                .register();
        new CommandAPICommand("registerroad")
                .withShortDescription("Register a road.")
                .withPermission(CommandPermission.fromString("mojangmaps.register.road"))
                .withArguments(new StringArgument("name").setOptional(true))
                .withArguments(new GreedyStringArgument("locations"))
                .withUsage("/registerroad <" + MessageUtil.getMessage("registerroadarguments") + ">.", "/registerroad <" + MessageUtil.getMessage("registerroadarguments") + ">.")
                .withAliases("createroad")
                .executesPlayer((RegisterRoadCommand::new))
                .register();
        new CommandAPICommand("goto")
                .withShortDescription("Go to a specific location.")
                .withPermission(CommandPermission.fromString("mojangmaps.using.goto"))
                .withArguments(new LocationArgument("location", LocationType.BLOCK_POSITION, false))
                .withUsage("/goto <x> <y> <z>.", "/goto <x> <y> <z>.")
                .executesPlayer((GoToCommand::new))
                .register();
        new CommandAPICommand("whereamistanding")
                .withShortDescription("Shows where you're standing.")
                .withPermission(CommandPermission.fromString("mojangmaps.using.viewlocation"))
                .withAliases("whichroad", "where")
                .executesPlayer(((player, commandArguments) -> {
                    new WhereAmIStandingCommand(player);
                }))
                .register();
        new CommandAPICommand("reloadconfigsfromdisk")
                .withShortDescription("Reloads all the configs from disk.")
                .withPermission(CommandPermission.fromString("mojangmaps.util.reloadconfigs"))
                .withAliases("reloadconfigs", "reloadconfig")
                .executes(((commandSender, commandArguments) -> {
                    new ReloadConfigsFromDiskCommand();
                }))
                .register();
        new CommandAPICommand("navigation")
                .withShortDescription("Go to a specific location and view the navigation in GUI form.")
                .withPermission(CommandPermission.fromString("mojangmaps.using.navigation"))
                .withAliases("gotogui")
                .withUsage("/navigation <x> <y> <z>.", "/navigation <x> <y> <z>.")
                .withArguments(new LocationArgument("location", LocationType.BLOCK_POSITION, false))
                .executesPlayer((NavigationCommand::new))
                .register();
        new CommandAPICommand("giveregisteritem")
                .withShortDescription("Hello, this is a test")
                .executesPlayer(((player, commandArguments) -> {
                    new GiveRegisterItemCommand(player);
                }))
                .register();

        // Listeners/Events init
        getServer().getPluginManager().registerEvents(new PlayerWalkListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new RegisterItemListener(), this);

        // SpiGUI init
        spiGUI = new SpiGUI(this);

    }

    private void checkVersion() {

        JsonObject latestRelease = HTTPUtil.HTTPRequestJSONObject("https://api.github.com/repos/Abelkrijgtalles/mojang-maps/releases/latest");
        if (isOnline) {
            assert latestRelease != null;
            if (Objects.equals(latestRelease.getAsJsonPrimitive("name").toString(), getDescription().getVersion())) {
                isPluginOutdated = true;

            }
        }

    }

}

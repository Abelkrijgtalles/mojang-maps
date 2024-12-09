/*
 * nl.abelkrijgtalles.mojangmaps.mojang_maps.fabric.main
 * Copyright (C) 2024 Abel van Hulst/Abelkrijgtalles/Abelpro678
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

package nl.abelkrijgtalles.mojangmaps.fabric;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.loader.api.FabricLoader;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.LoaderInfo;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.Config;
import org.apache.logging.log4j.Level;
import org.bstats.MetricsBase;
import org.bstats.charts.CustomChart;
import org.bstats.json.JsonObjectBuilder;

public class Metrics {

    // based on bstats-bukkit, which is licensed under the MIT license, which applies to this file.

    private final MetricsBase metricsBase;

    /**
     * Creates a new Metrics instance.
     *
     * @param loaderInfo Your {@link LoaderInfo} instance.
     * @param serviceId  The id of the service.
     *                   It can be found at <a href="https://bstats.org/what-is-my-plugin-id">What is my plugin id?</a>
     */
    public Metrics(LoaderInfo loaderInfo, int serviceId) {

        // Get the config file
        Config config = loaderInfo.getConfig();

        // Load the data
        boolean enabled = config.getBoolean("enabled");
        String serverUUID = config.get("serverUuid");
        boolean logErrors = config.getBoolean("logFailedRequests");
        boolean logSentData = config.getBoolean("logSentData");
        boolean logResponseStatusText = config.getBoolean("logResponseStatusText");

        metricsBase = new MetricsBase(
                "bukkit",
                serverUUID,
                serviceId,
                enabled,
                this::appendPlatformData,
                this::appendServiceData,
                // See https://github.com/Bastian/bstats-metrics/pull/126
                CompletableFuture::runAsync,
                this::enabled,
                (message, error) -> MojangMaps.LOGGER.log(Level.WARN, message, error),
                (message) -> MojangMaps.LOGGER.log(Level.INFO, message),
                logErrors,
                logSentData,
                logResponseStatusText,
                false
        );

    }

    /**
     * Shuts down the underlying scheduler service.
     */
    public void shutdown() {

        metricsBase.shutdown();
    }

    /**
     * Adds a custom chart.
     *
     * @param chart The chart to add.
     */
    public void addCustomChart(CustomChart chart) {

        metricsBase.addCustomChart(chart);
    }

    private void appendPlatformData(JsonObjectBuilder builder) {

        builder.appendField("playerAmount", getPlayerAmount());
        builder.appendField("onlineMode", MojangMapsFabric.SERVER.usesAuthentication() ? 1 : 0);
        builder.appendField("bukkitVersion", FabricLoader.getInstance().getModContainer("fabricloader").orElseThrow(() -> new RuntimeException("Couldn't find fabric loader")).getMetadata().getVersion().getFriendlyString());
        builder.appendField("bukkitName", FabricLoader.getInstance().getModContainer("fabricloader").orElseThrow(() -> new RuntimeException("Couldn't find fabric loader")).getMetadata().getName());

        builder.appendField("javaVersion", System.getProperty("java.version"));
        builder.appendField("osName", System.getProperty("os.name"));
        builder.appendField("osArch", System.getProperty("os.arch"));
        builder.appendField("osVersion", System.getProperty("os.version"));
        builder.appendField("coreCount", Runtime.getRuntime().availableProcessors());
    }

    private void appendServiceData(JsonObjectBuilder builder) {

        builder.appendField("pluginVersion", FabricLoader.getInstance().getModContainer("mojang_maps").orElseThrow(() -> new RuntimeException("Couldn't find fabric loader")).getMetadata().getVersion().getFriendlyString());
    }

    private int getPlayerAmount() {

        return MojangMapsFabric.SERVER.getPlayerCount();

    }

    private boolean enabled() {

        return true;

    }

}

/*
 * mojang_maps.sponge.main
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

package nl.abelkrijgtalles.mojangmaps.sponge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.Config;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ParsingException;
import org.spongepowered.configurate.serialize.SerializationException;

public class SpongeConfig {

    private final HoconConfigurationLoader loader;
    private final Path configPath;
    private final String defaultConfig;
    private CommentedConfigurationNode rootNode;

    public SpongeConfig(Path configPath, String defaultConfig) {

        this.configPath = configPath;
        loader = HoconConfigurationLoader.builder()
                .path(configPath)
                .build();

        this.defaultConfig = defaultConfig;
    }

    public void loadConfig() {

        try {
            if (Files.notExists(configPath)) {
                saveDefaultConfig();
            }

            rootNode = loader.load(ConfigurationOptions.defaults());
        } catch (ParsingException e) {
            MojangMaps.LOGGER.error("Unable to load Sponge config for Mojang Maps");
            throw new RuntimeException(e);
        }

    }

    private void saveConfig() {

        try {
            loader.save(rootNode);
        } catch (ConfigurateException e) {
            MojangMaps.LOGGER.error("Unable to save Sponge configuration for Mojang Maps.");
            throw new RuntimeException(e);
        }

    }

    private void saveDefaultConfig() {

        try {
            Files.writeString(configPath, defaultConfig);
        } catch (IOException e) {
            MojangMaps.LOGGER.error("Unable to save default Sponge configuration for Mojang Maps.");
            throw new RuntimeException(e);
        }

    }

    public String getString(String key, String defaultValue) {

        return rootNode.node((Object[]) key.split("\\.")).getString(defaultValue);
    }

    public void setString(String key, String value) {

        try {
            rootNode.node((Object[]) key.split("\\.")).set(value);
        } catch (SerializationException e) {
            MojangMaps.LOGGER.error("Unable to set key %s to %s for the Mojang Maps Sponge configuration.".formatted(key, value));
            throw new RuntimeException(e);
        }
    }

    public void setComment(String key, String comment) {

        rootNode.node((Object[]) key.split("\\.")).comment(comment);
    }

    public static class Wrapper implements Config {

        private final SpongeConfig config;

        public Wrapper(Path configPath, String defaultConfig) {

            config = new SpongeConfig(configPath, defaultConfig);

        }

        @Override
        public String get(String key) {

            return config.getString(key, null);
        }

    }

}

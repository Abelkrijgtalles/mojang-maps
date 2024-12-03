/*
 * mojang_maps.fabric.main
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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;
import net.fabricmc.loader.api.FabricLoader;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.Config;

public class SimpleConfig {

    // Based on Simple Config made by magistermaks, which is licensed with the MIT license. Please ignore above copyright notice.

    private final HashMap<String, String> config = new HashMap<>();
    private final ConfigRequest request;
    private boolean broken = false;

    private SimpleConfig(ConfigRequest request) {

        this.request = request;
        String identifier = "Config '" + request.filename + "'";

        if (!request.file.exists()) {
            MojangMaps.LOGGER.info(identifier + " is missing, generating default one...");

            try {
                createConfig();
            } catch (IOException e) {
                MojangMaps.LOGGER.error(identifier + " failed to generate!");
                MojangMaps.LOGGER.trace(e);
                broken = true;
            }
        }

        if (!broken) {
            try {
                loadConfig();
            } catch (Exception e) {
                MojangMaps.LOGGER.error(identifier + " failed to load!");
                MojangMaps.LOGGER.trace(e);
                broken = true;
            }
        }

    }

    /**
     * Creates new config request object, ideally `namespace`
     * should be the name of the mod id of the requesting mod
     *
     * @param filename - name of the config file
     * @return new config request object
     */
    public static ConfigRequest of(String filename) {

        Path path = FabricLoader.getInstance().getConfigDir();
        return new ConfigRequest(path.resolve(filename + ".properties").toFile(), filename);
    }

    private void createConfig() throws IOException {

        // try creating missing files
        request.file.getParentFile().mkdirs();
        Files.createFile(request.file.toPath());

        // write default config data
        PrintWriter writer = new PrintWriter(request.file, "UTF-8");
        writer.write(request.getConfig());
        writer.close();

    }

    private void loadConfig() throws IOException {

        Scanner reader = new Scanner(request.file);
        for (int line = 1; reader.hasNextLine(); line++) {
            parseConfigEntry(reader.nextLine(), line);
        }
    }

    private void parseConfigEntry(String entry, int line) {

        if (!entry.isEmpty() && !entry.startsWith("#")) {
            String[] parts = entry.split("=", 2);
            if (parts.length == 2) {
                config.put(parts[0], parts[1]);
            } else {
                throw new RuntimeException("Syntax error in config file on line " + line + "!");
            }
        }
    }

    /**
     * Queries a value from config, returns `null` if the
     * key does not exist.
     *
     * @return value corresponding to the given key
     * @return value corresponding to the given key
     * @see SimpleConfig#getOrDefault
     */
    @Deprecated
    public String get(String key) {

        return config.get(key);
    }

    /**
     * Returns string value from config corresponding to the given
     * key, or the default string if the key is missing.
     *
     * @return value corresponding to the given key, or the default value
     */
    public String getOrDefault(String key, String def) {

        String val = get(key);
        return val == null ? def : val;
    }

    /**
     * Returns integer value from config corresponding to the given
     * key, or the default integer if the key is missing or invalid.
     *
     * @return value corresponding to the given key, or the default value
     */
    public int getOrDefault(String key, int def) {

        try {
            return Integer.parseInt(get(key));
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * Returns boolean value from config corresponding to the given
     * key, or the default boolean if the key is missing.
     *
     * @return value corresponding to the given key, or the default value
     */
    public boolean getOrDefault(String key, boolean def) {

        String val = get(key);
        if (val != null) {
            return val.equalsIgnoreCase("true");
        }

        return def;
    }

    /**
     * Returns double value from config corresponding to the given
     * key, or the default string if the key is missing or invalid.
     *
     * @return value corresponding to the given key, or the default value
     */
    public double getOrDefault(String key, double def) {

        try {
            return Double.parseDouble(get(key));
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * If any error occurred during loading or reading from the config
     * a 'broken' flag is set, indicating that the config's state
     * is undefined and should be discarded using `delete()`
     *
     * @return the 'broken' flag of the configuration
     */
    public boolean isBroken() {

        return broken;
    }

    /**
     * deletes the config file from the filesystem
     *
     * @return true if the operation was successful
     */
    public boolean delete() {

        MojangMaps.LOGGER.warn("Config '" + request.filename + "' was removed from existence! Restart the game to regenerate it.");
        return request.file.delete();
    }

    public interface DefaultConfig {

        static String empty(String namespace) {

            return "";
        }

        String get(String namespace);

    }

    public static class ConfigRequest {

        private final File file;
        private final String filename;
        private DefaultConfig provider;

        private ConfigRequest(File file, String filename) {

            this.file = file;
            this.filename = filename;
            this.provider = DefaultConfig::empty;
        }

        /**
         * Sets the default config provider, used to generate the
         * config if it's missing.
         *
         * @param provider default config provider
         * @return current config request object
         * @see DefaultConfig
         */
        public ConfigRequest provider(DefaultConfig provider) {

            this.provider = provider;
            return this;
        }

        /**
         * Loads the config from the filesystem.
         *
         * @return config object
         * @see SimpleConfig
         */
        public SimpleConfig request() {

            return new SimpleConfig(this);
        }

        private String getConfig() {

            return provider.get(filename) + "\n";
        }

    }

    public static class Wrapper implements Config {

        private static SimpleConfig simpleConfig;

        public Wrapper(String fileName, DefaultConfig defaultConfig) {

            simpleConfig = SimpleConfig.of(fileName).provider(defaultConfig).request();

        }

        @Override
        public String get(String key) {

            return simpleConfig.get(key);
        }

    }

}

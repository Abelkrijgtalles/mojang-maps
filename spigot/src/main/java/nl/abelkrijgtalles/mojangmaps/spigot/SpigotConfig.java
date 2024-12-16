/*
 * mojang_maps.spigot.main
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

package nl.abelkrijgtalles.mojangmaps.spigot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.platform.config.Config;

public class SpigotConfig implements Config {

    File defaultConfigPath = new File(MojangMapsSpigot.Instance.getDataFolder(), "config.yml");

    public SpigotConfig(String defaultConfig) {

        if (Files.notExists(defaultConfigPath.toPath())) createConfig(defaultConfig);

    }

    private void createConfig(String defaultConfig) {

        try {
            Files.createDirectories(MojangMaps.loaderInfo.getConfig().getDataDirectory());
        } catch (IOException e) {
            MojangMaps.LOGGER.error("Unable to create folder %s.".formatted(MojangMapsSpigot.Instance.getDataFolder()));
            throw new RuntimeException(e);
        }
        try {
            Files.createFile(defaultConfigPath.toPath());
        } catch (IOException e) {
            MojangMaps.LOGGER.error("Unable to create file %s.".formatted(defaultConfigPath));
            throw new RuntimeException(e);
        }
        try {
            Files.writeString(defaultConfigPath.toPath(), defaultConfig);
        } catch (IOException e) {
            MojangMaps.LOGGER.error("Unable to save the default Mojang Maps Spigot configuration.");
            throw new RuntimeException(e);
        }

    }

    @Override
    public String get(String key) {

        return (String) MojangMapsSpigot.Instance.getConfig().get(key);

    }

    @Override
    public Path getDataDirectory() {

        #if MC_VER > MC_1_20_6
        return MojangMapsSpigot.Instance.getDataPath();
        #else
        return MojangMapsSpigot.Instance.getDataFolder().toPath();
        #endif
    }

}

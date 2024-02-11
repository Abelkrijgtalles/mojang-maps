/*
 * MojangMaps
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

package nl.abelkrijgtalles.MojangMaps.util.file;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import org.bukkit.configuration.file.FileConfiguration;

/*
    Table of Config Versions:
    <pre>
    | Version | Indicator/Changes                                                    | Contains config-version | Added in |
    |---------|----------------------------------------------------------------------|-------------------------|----------|
    | 3       | Added config-version                                                 | Yes                     | 2.0      |
    | 2       | Added language                                                       | No                      | 1.3      |
    | 1       | Added street-actionbar                                               | No                      | 1.2      |
    | 0       | Added a comment that said that there was nothing it that config file | No                      | 1.0      |
    </pre>

 */

public class ConfigMigrationUtil {

    private static int getConfigVersion(FileConfiguration config) {

        if (config.contains("config-version", true)) {
            return config.getInt("config-version");
        } else {
            return determineVersion(config);
        }

    }

    private static int determineVersion(FileConfiguration config) {

        if (config.contains("language")) {
            return 2;
        }
        if (config.contains("street-actionbar")) {
            return 1;
        }

        return 0;

    }

    public static void migrateConfig(FileConfiguration config) {

        int oldVersion = getConfigVersion(config);

        MojangMaps.getMMLogger().info(config.saveToString());

        switch (oldVersion) {
            case 2 -> migrateFrom2(config);
            case 1 -> migrateFrom1(config);
            case 0 -> migrateFrom0(config);
        }

    }

    private static void migrateFrom2(FileConfiguration config) {


    }

    private static void migrateFrom1(FileConfiguration config) {

    }

    private static void migrateFrom0(FileConfiguration config) {

    }

}

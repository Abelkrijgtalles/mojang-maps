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

import org.bukkit.configuration.file.FileConfiguration;

/*
    Table of Config Versions:
    <pre>
    | Version | Indicator/Changes                                                    | Added in |
    |---------|----------------------------------------------------------------------|----------|
    | 3       | Added config-version                                                 | 2.0      |
    | 2       | Added language                                                       | 1.3      |
    | 1       | Added street-actionbar                                               | 1.2      |
    | 0       | Added a comment that said that there was nothing it that config file | 1.0      |
    </pre>

 */

public class ConfigMigrationUtil {

    public static Boolean getConfigVersion(FileConfiguration config) {

        return config.contains("config-version", false);

    }

}

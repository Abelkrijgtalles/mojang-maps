/*
 * mojang_maps.common.main
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

package nl.abelkrijgtalles.mojangmaps.common.config;

import java.nio.file.Path;

public interface Config {

    /**
     * Gets a value from the config.
     *
     * @param key The key to get the value from. Seperated with a dot when accessing in a group.
     * @return The value of the key in the config. Returns null if it doesn't exist.
     */
    String get(String key);

    /**
     * @return The directory which contains all the data files for Mojang Maps.
     */
    Path getDataDirectory();

}

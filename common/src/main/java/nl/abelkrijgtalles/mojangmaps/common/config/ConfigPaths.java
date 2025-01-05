/*
 * mojang_maps.common.main
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

package nl.abelkrijgtalles.mojangmaps.common.config;

import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;

public class ConfigPaths {

    public final static String PATHFINDING = "pathfinding";
    public final static String COSTS = "costs";
    public final static String ADDITIONAL_PRE_CALCULATED_RANGE = "extra-pre-calculated-range";

    public static String of(String... paths) {

        StringBuilder combinedPath = new StringBuilder();

        for (int i = 0; i < paths.length; i++) {

            combinedPath.append(paths[i]);
            if (i != paths.length - 1) combinedPath.append('.');

        }

        return combinedPath.toString();

    }

    public static String get(String... paths) {

        return MojangMaps.loaderInfo.getConfig().get(of(paths));

    }

}

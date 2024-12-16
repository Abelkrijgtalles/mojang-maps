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

package nl.abelkrijgtalles.mojangmaps.common.util;

import java.util.List;

public final class RecursiveItem {

    // don't convert to record, as it isn't supported in java 8 (1.16.5)
    private final Object value;
    private final String key;
    private final Object additionalData;
    private final List<String> path;

    public RecursiveItem(Object value, String key, Object additionalData, List<String> path) {

        this.value = value;
        this.key = key;
        this.additionalData = additionalData;
        this.path = path;
    }

    /**
     * Returns the path and key as a {@link String}
     *
     * @return The path and key divided by dots.
     */
    public String toString() {

        StringBuilder pathString = new StringBuilder();
        for (String group : path) {

            pathString.append(group);
            pathString.append('.');

        }
        pathString.append(key);

        return pathString.toString();

    }

    public Object value() {

        return value;
    }

    public String key() {

        return key;
    }

    public Object additionalData() {

        return additionalData;
    }

    public List<String> path() {

        return path;
    }


}

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

package nl.abelkrijgtalles.mojangmaps.common.platform.config;

import java.util.List;

public class ConfigGroup extends ConfigObject {

    private final String name;
    private final List<ConfigObject> children;

    public ConfigGroup(String name, String comment, List<ConfigObject> children) {

        super(comment);

        this.name = name;
        this.children = children;
    }

    public String getName() {

        return name;
    }

    public List<ConfigObject> getChildren() {

        return children;
    }

}

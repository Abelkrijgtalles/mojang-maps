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

import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.LoaderInfo;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.Config;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigGroup;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigItem;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigObject;

public class LoaderInfoFabric implements LoaderInfo {

    private SimpleConfig.Wrapper config;

    public LoaderInfoFabric() {

        config = new SimpleConfig.Wrapper("mojang_maps", this::defaultConfig);

    }

    @Override
    public Config getConfig() {

        return config;
    }

    public String defaultConfig(String filename) {

        StringBuilder config = new StringBuilder();

        for (ConfigObject object : MojangMaps.getDefaultConfig()) {

            if (object instanceof ConfigItem item) {

                config.append(renderItem(item));

            } else {
                ConfigGroup group = (ConfigGroup) object;
                config.append(renderGroup(group));
            }

        }

        return config.toString();

    }

    private String renderItem(ConfigItem item) {

        StringBuilder itemString = new StringBuilder();

        if (!item.getComment().isEmpty()) {
            itemString.append("# ");
            itemString.append(item.getComment());
            itemString.append('\n');
        }
        itemString.append(item.getKey());
        itemString.append(": ");
        itemString.append(item.getValue());
        itemString.append("\n\n");

        return itemString.toString();

    }

    private String renderGroup(ConfigGroup group) {

        StringBuilder groupString = new StringBuilder();

        if (!group.getComment().isEmpty()) {
            groupString.append("# ");
            groupString.append(group.getComment());
            groupString.append('\n');
        }
        groupString.append(group.getName());
        groupString.append(":");
        groupString.append("\n\n");

        for (ConfigObject object : group.getChildren()) {

            if (object instanceof ConfigItem item) {

                groupString.append('\t');
                groupString.append(renderItem(item));

            } else {

                ConfigGroup nestedGroup = (ConfigGroup) object;
                groupString.append("\t");
                groupString.append(renderGroup(nestedGroup));
                groupString.append("\n\n");

            }

        }

        return groupString.toString();

    }

}

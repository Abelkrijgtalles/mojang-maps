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

package nl.abelkrijgtalles.mojangmaps.common.compatibility.config;

import java.util.List;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;

public class YamlLikeConfigGenerator {

    // all include needed spaces
    private final String groupDefineBeginSymbol;
    private final String groupDefineEndSymbol;
    private final String valueDefineSymbol;
    private final String commentDefineSymbol;

    public YamlLikeConfigGenerator(String groupDefineBeginSymbol, String groupDefineEndSymbol, String valueDefineSymbol, String commentDefineSymbol) {

        this.groupDefineBeginSymbol = groupDefineBeginSymbol;
        this.groupDefineEndSymbol = groupDefineEndSymbol;
        this.valueDefineSymbol = valueDefineSymbol;
        this.commentDefineSymbol = commentDefineSymbol;
    }

    public String renderConfig(List<ConfigObject> config) {

        StringBuilder configString = new StringBuilder();

        for (ConfigObject object : MojangMaps.getDefaultConfig()) {

            if (object instanceof ConfigItem item) {

                configString.append(renderItem(item));

            } else {
                ConfigGroup group = (ConfigGroup) object;
                configString.append(renderGroup(group));
            }

        }

        return configString.toString();

    }

    private String renderItem(ConfigItem item) {

        StringBuilder itemString = new StringBuilder();

        if (!item.getComment().isEmpty()) {
            itemString.append(commentDefineSymbol);
            itemString.append(item.getComment());
            itemString.append('\n');
        }
        itemString.append(item.getKey());
        itemString.append(valueDefineSymbol);
        itemString.append(item.getValue());
        itemString.append("\n\n");

        return itemString.toString();

    }

    private String renderGroup(ConfigGroup group) {

        StringBuilder groupString = new StringBuilder();

        if (!group.getComment().isEmpty()) {
            groupString.append(commentDefineSymbol);
            groupString.append(group.getComment());
            groupString.append('\n');
        }
        groupString.append(group.getName());
        groupString.append(groupDefineBeginSymbol);
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

        groupString.append(groupDefineEndSymbol);

        return groupString.toString();

    }

    public static class Defaults {

        // adding toml in the future could be a nice thing, wouldn't know why I would add it, + it has to change more stuff and makes it even more abstract.
        public static final YamlLikeConfigGenerator PURE_YAML = new YamlLikeConfigGenerator(":", null, ": ", "# ");
        public static final YamlLikeConfigGenerator SPONGE = new YamlLikeConfigGenerator(" {", "}", " = ", "# ");

    }

}

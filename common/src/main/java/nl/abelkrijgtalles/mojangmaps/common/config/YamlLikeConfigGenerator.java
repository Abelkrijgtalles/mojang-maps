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

import java.util.ArrayList;
import java.util.List;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.platform.config.ConfigGroup;
import nl.abelkrijgtalles.mojangmaps.common.platform.config.ConfigItem;
import nl.abelkrijgtalles.mojangmaps.common.platform.config.ConfigObject;
import nl.abelkrijgtalles.mojangmaps.common.util.RecursiveItem;

public class YamlLikeConfigGenerator {

    // all include needed spaces
    private final String groupDefineBeginSymbol;
    private final String groupDefineEndSymbol;
    private final Boolean groupDotNotation;
    private final String valueDefineSymbol;
    private final String commentDefineSymbol;

    public YamlLikeConfigGenerator(String groupDefineBeginSymbol, String groupDefineEndSymbol, Boolean groupDotNotation, String valueDefineSymbol, String commentDefineSymbol) {

        this.groupDefineBeginSymbol = groupDefineBeginSymbol;
        this.groupDefineEndSymbol = groupDefineEndSymbol;
        this.groupDotNotation = groupDotNotation;
        this.valueDefineSymbol = valueDefineSymbol;
        this.commentDefineSymbol = commentDefineSymbol;
    }

    public String renderConfig(List<ConfigObject> config) {

        StringBuilder configString = new StringBuilder();

        for (ConfigObject object : MojangMaps.getDefaultConfig()) {

            if (object instanceof ConfigItem item) {

                configString.append(renderItem(item, true));

            } else {
                ConfigGroup group = (ConfigGroup) object;
                configString.append(renderGroup(group));
            }

        }

        return configString.toString();

    }

    private String renderItem(ConfigItem item, boolean includeComment) {

        StringBuilder itemString = new StringBuilder();

        if (!item.getComment().isEmpty() && includeComment) {
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

        if (groupDotNotation) return renderGroupWithDotAnnotation(group);

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
                groupString.append(renderItem(item, true));

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

    private String renderGroupWithDotAnnotation(ConfigGroup group) {

        StringBuilder groupString = new StringBuilder();
        List<RecursiveItem> allNestedChildren = getAllNestedChildrenFromGroup(group, new ArrayList<>());

        for (RecursiveItem item : allNestedChildren) {

            if (item.additionalData() instanceof String comments && !comments.isEmpty()) {
                groupString.append(comments);
                groupString.append('\n');

            }

            groupString.append(item);
            groupString.append(valueDefineSymbol);
            groupString.append(item.value());
            groupString.append("\n\n");

        }

        return groupString.toString();

    }

    private List<RecursiveItem> getAllNestedChildrenFromGroup(ConfigGroup group, List<String> path) {

        List<RecursiveItem> children = new ArrayList<>();
        boolean commentAdded = false;
        path.add(group.getName());

        for (ConfigObject object : group.getChildren()) {

            if (object instanceof ConfigItem item) {

                StringBuilder comment = new StringBuilder();
                if (!commentAdded && !group.getComment().isEmpty()) {

                    comment.append(commentDefineSymbol);
                    comment.append(group.getComment());

                    if (!item.getComment().isEmpty()) {
                        comment.append('\n');
                        comment.append(commentDefineSymbol);
                        comment.append(item.getComment());
                    }

                    commentAdded = true;

                }

                children.add(new RecursiveItem(item.getKey(), item.getValue(), comment.toString(), path));

            } else {

                ConfigGroup nestedGroup = (ConfigGroup) object;
                children.addAll(getAllNestedChildrenFromGroup(nestedGroup, path));

            }

        }

        return children;
    }

    public static class Defaults {

        // adding toml in the future could be a nice thing, wouldn't know why I would add it, + it has to change more stuff and makes it even more abstract.
        public static final YamlLikeConfigGenerator PURE_YAML = new YamlLikeConfigGenerator(":", null, false, ": ", "# ");
        public static final YamlLikeConfigGenerator SPONGE = new YamlLikeConfigGenerator(" {", "}", false, " = ", "# ");
        public static final YamlLikeConfigGenerator FABRIC = new YamlLikeConfigGenerator(null, null, true, " = ", "# ");

    }

}

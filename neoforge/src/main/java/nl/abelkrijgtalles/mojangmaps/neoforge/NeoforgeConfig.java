/*
 * mojang_maps.neoforge.main
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

package nl.abelkrijgtalles.mojangmaps.neoforge;

import java.util.HashMap;
import net.neoforged.neoforge.common.ModConfigSpec;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.Config;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigGroup;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigItem;
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.ConfigObject;

public class NeoforgeConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec CONFIG;
    public static final HashMap<String, ModConfigSpec.ConfigValue> values = new HashMap<>();

    static {


        for (ConfigObject object : MojangMaps.getDefaultConfig()) {

            if (object instanceof ConfigItem item) {

                if (!item.getComment().isEmpty())
                    values.put(item.getKey(), BUILDER.comment(item.getComment()).define(item.getKey(), item.getValue()));
                else values.put(item.getKey(), BUILDER.define(item.getKey(), item.getValue()));

            } else {
                ConfigGroup group = (ConfigGroup) object;
                renderGroup(group);
            }

        }

        CONFIG = BUILDER.build();

    }

    private static void renderGroup(ConfigGroup group) {

        if (!group.getComment().isEmpty()) BUILDER.push(group.getName()).comment(group.getComment());
        else BUILDER.push(group.getName());

        for (ConfigObject object : group.getChildren()) {

            if (object instanceof ConfigItem item) {

                if (!item.getComment().isEmpty())
                    values.put(item.getKey(), BUILDER.comment(item.getComment()).define(item.getKey(), item.getValue()));
                else values.put(item.getKey(), BUILDER.define(item.getKey(), item.getValue()));

            } else {

                ConfigGroup nestedGroup = (ConfigGroup) object;
                renderGroup(nestedGroup);

            }

        }

        BUILDER.pop();

    }

    public static class Wrapper implements Config {

        @Override
        public String get(String key) {

            return values.get(key).get().toString();
        }

    }

}

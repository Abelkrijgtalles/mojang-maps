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
import nl.abelkrijgtalles.mojangmaps.common.compatibility.config.YamlLikeConfigGenerator;

public class LoaderInfoFabric implements LoaderInfo {

    private final SimpleConfig.Wrapper config;
    private final YamlLikeConfigGenerator yamlLikeConfigGenerator;

    public LoaderInfoFabric() {

        config = new SimpleConfig.Wrapper("mojang_maps", this::defaultConfig);
        yamlLikeConfigGenerator = new YamlLikeConfigGenerator(":", null, ": ", "# ");

    }

    @Override
    public Config getConfig() {

        return config;
    }

    public String defaultConfig(String filename) {

        return yamlLikeConfigGenerator.renderConfig(MojangMaps.getDefaultConfig());

    }

}

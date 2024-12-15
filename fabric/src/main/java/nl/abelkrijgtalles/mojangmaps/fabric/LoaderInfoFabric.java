/*
 * nl.abelkrijgtalles.mojangmaps.mojang_maps.fabric.main
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
import nl.abelkrijgtalles.mojangmaps.common.config.YamlLikeConfigGenerator;
import nl.abelkrijgtalles.mojangmaps.common.platform.LoaderInfo;
import nl.abelkrijgtalles.mojangmaps.common.platform.config.Config;

public class LoaderInfoFabric implements LoaderInfo {

    private final SimpleConfig.Wrapper config;
    private final boolean runningTests;

    public LoaderInfoFabric(boolean runningTests) {

        config = new SimpleConfig.Wrapper("mojang_maps", this::defaultConfig);
        this.runningTests = runningTests;

    }

    @Override
    public Config getConfig() {

        return config;
    }

    @Override
    public boolean isRunningTests() {

        return runningTests;
    }

    private String defaultConfig(String filename) {

        return YamlLikeConfigGenerator.Defaults.FABRIC.renderConfig(MojangMaps.getDefaultConfig());

    }

}

/*
 * nl.abelkrijgtalles.mojangmaps.mojang_maps.fabric.main
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

package nl.abelkrijgtalles.mojangmaps.fabric.platform;

import java.nio.file.Path;
import java.nio.file.Paths;
import net.fabricmc.loader.api.FabricLoader;
import nl.abelkrijgtalles.mojangmaps.common.LoaderInfo;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.nms.platform.NMSPlatform;
import nl.abelkrijgtalles.mojangmaps.platform.Platform;

public class FabricLoaderInfo implements LoaderInfo {

    private final boolean runningTests;

    public FabricLoaderInfo(boolean runningTests) {

        this.runningTests = runningTests;

    }

    @Override
    public boolean isRunningTests() {

        return runningTests;
    }

    @Override
    public Platform getPlatform() {

        return new NMSPlatform(new FabricNMSUtils());
    }

    @Override
    public Path getDataDirectory() {

        return Paths.get(String.valueOf(FabricLoader.getInstance().getConfigDir()), MojangMaps.MOD_ID);
    }

}

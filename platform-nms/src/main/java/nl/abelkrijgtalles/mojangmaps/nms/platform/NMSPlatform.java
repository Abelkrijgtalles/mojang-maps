/*
 * mojang_maps.platform-nms.main
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

package nl.abelkrijgtalles.mojangmaps.nms.platform;

import java.util.Arrays;
import net.minecraft.DetectedVersion;
import net.minecraft.server.level.ServerLevel;
import nl.abelkrijgtalles.mojangmaps.nms.platform.util.NMSUtils;
import nl.abelkrijgtalles.mojangmaps.nms.platform.world.NMSLevel;
import nl.abelkrijgtalles.mojangmaps.platform.Platform;
import nl.abelkrijgtalles.mojangmaps.platform.world.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NMSPlatform implements Platform {

    private static NMSUtils utils = null;

    public NMSPlatform(NMSUtils utils) {

        NMSPlatform.utils = utils;
    }

    @Nullable
    public static NMSUtils getUtils() {

        return utils;

    }

    @Override
    public String getMinecraftVersion() {

        return DetectedVersion.tryDetectVersion().getName();
    }

    @Override
    public Level getLevel(@NotNull String identifier) {

        System.out.println(Arrays.toString(identifier.split("\\" + NMSLevel.resourceKeyDivider)));

        String namespace = identifier.split("\\" + NMSLevel.resourceKeyDivider)[0];
        String path = identifier.split("\\" + NMSLevel.resourceKeyDivider)[1];

        for (ServerLevel level : utils.getServer().getAllLevels()) {

            if (level.dimension().location().getNamespace().equals(namespace) && level.dimension().location().getPath().equals(path))
                return new NMSLevel(level);

        }

        return null;

    }

}

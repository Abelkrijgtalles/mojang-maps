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

import net.minecraft.DetectedVersion;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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

        ResourceKey<net.minecraft.world.level.Level> resourceKey = ResourceKey.create(ResourceKey.createRegistryKey(ResourceLocation.parse(identifier.split("/")[0])), ResourceLocation.parse(identifier.split("/")[1]));
        net.minecraft.world.level.Level nmsLevel = utils.getServer().getLevel(resourceKey);

        if (nmsLevel == null) return null;
        return new NMSLevel(nmsLevel);

    }

}

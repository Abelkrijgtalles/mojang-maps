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

package nl.abelkrijgtalles.mojangmaps.nms.platform.world;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import nl.abelkrijgtalles.mojangmaps.nms.platform.NMSPlatform;
import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NMSLevel implements Level {

    private final ResourceKey<net.minecraft.world.level.Level> resourceKey;

    public NMSLevel(ResourceKey<net.minecraft.world.level.Level> resourceKey) {

        this.resourceKey = resourceKey;
    }

    public NMSLevel(net.minecraft.world.level.Level level) {

        this(level.dimension());

    }

    @Override
    public String getIdentifier() {

        return "%s/%s".formatted(resourceKey.registryKey(), resourceKey.registry());
    }

    @Override
    public int getHeightAtLocation(@NotNull HeightMapType type, int x, int z) {

        if (getNMSLevel() == null)
            throw new IllegalStateException("Level with identifier %s does not exist.".formatted(getIdentifier()));

        return getNMSLevel().getHeight(convertHeightMapType(type), x, z);
    }

    @Nullable
    private net.minecraft.world.level.Level getNMSLevel() {

        if (NMSPlatform.getUtils() == null) return null;

        return NMSPlatform.getUtils().getServer().getLevel(resourceKey);
    }

    private Heightmap.Types convertHeightMapType(@NotNull HeightMapType heightMapType) {

        switch (heightMapType) {
            case MOTION_BLOCKING_NO_LEAVES -> {
                return Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
            }
            case null, default -> {
                throw new IllegalArgumentException("I don't know how, but somehow a HeightMapType that doesn't exist has been passed.");
            }
        }

    }

}

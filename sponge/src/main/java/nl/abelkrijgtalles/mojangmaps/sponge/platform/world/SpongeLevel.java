/*
 * mojang_maps.sponge.main
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

package nl.abelkrijgtalles.mojangmaps.sponge.platform.world;

import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.HeightType;
import org.spongepowered.api.world.HeightTypes;
import org.spongepowered.api.world.server.ServerWorld;

public class SpongeLevel implements Level {

    private final ResourceKey resourceKey;

    public SpongeLevel(ResourceKey resourceKey) {

        this.resourceKey = resourceKey;
    }

    public SpongeLevel(ServerWorld world) {

        this(world.key());

    }

    @Override
    public String getIdentifier() {

        return resourceKey.asString();
    }

    @Override
    public int getHeightAtLocation(@NotNull HeightMapType type, int x, int z) {

        return getWorld().height(convertHeightMapType(type), x, z);
    }

    private ServerWorld getWorld() {

        if (Sponge.game().server().worldManager().world(resourceKey).isEmpty())
            throw new IllegalStateException("Level with identifier %s does not exist.".formatted(getIdentifier()));
        return Sponge.game().server().worldManager().world(resourceKey).get();

    }

    private HeightType convertHeightMapType(@NotNull HeightMapType heightMapType) {

        switch (heightMapType) {
            case WORLD_SURFACE_WG -> {
                return (HeightType) HeightTypes.WORLD_SURFACE_WG;
            }
            case WORLD_SURFACE -> {
                return (HeightType) HeightTypes.WORLD_SURFACE;
            }
            case OCEAN_FLOOR_WG -> {
                return (HeightType) HeightTypes.OCEAN_FLOOR_WG;
            }
            case OCEAN_FLOOR -> {
                return (HeightType) HeightTypes.OCEAN_FLOOR;
            }
            case MOTION_BLOCKING -> {
                return (HeightType) HeightTypes.MOTION_BLOCKING;
            }
            case MOTION_BLOCKING_NO_LEAVES -> {
                return (HeightType) HeightTypes.MOTION_BLOCKING_NO_LEAVES;
            }
            case null, default -> {
                throw new IllegalArgumentException("I don't know how, but somehow a HeightMapType that doesn't exist has been passed.");
            }
        }

    }

}

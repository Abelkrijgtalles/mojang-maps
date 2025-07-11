/*
 * mojang-maps.platform.main
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

package nl.abelkrijgtalles.mojangmaps.platform.world;

import org.jetbrains.annotations.NotNull;

public interface Level {

    /**
     * @return A string which can be used to identify a {@link Level}.
     */
    String getIdentifier();

    /**
     * Gets the height at a specific location.
     *
     * @param type The {@link HeightMapType} used to get the highest location.
     * @param x    X-coordinate.
     * @param z    Z-coordinate.
     * @return The height at the location.
     */
    int getHeightAtLocation(@NotNull HeightMapType type, int x, int z);

    /**
     * Spawns specific particles at a given location.
     *
     * @param particle The type of particle to use.
     * @param location The location to spawn the particles at.
     * @param count    The count of particles to spawn.
     */
    void spawnParticle(@NotNull Particle particle, @NotNull Vec3 location, int count);

    /**
     * @return The {@link WorldBorder} of this {@link Level}.
     */
    WorldBorder getWorldBorder();

}

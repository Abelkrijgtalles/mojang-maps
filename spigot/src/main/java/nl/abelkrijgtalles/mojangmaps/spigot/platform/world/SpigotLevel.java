/*
 * mojang_maps.spigot.main
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

package nl.abelkrijgtalles.mojangmaps.spigot.platform.world;

import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Level;
import org.bukkit.Bukkit;
import org.bukkit.HeightMap;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class SpigotLevel implements Level {

    private final String worldName;

    public SpigotLevel(String worldName) {

        this.worldName = worldName;
    }

    public SpigotLevel(World world) {

        this(world.getName());

    }

    @Override
    public String getIdentifier() {

        return worldName;
    }

    @Override
    public int getHeightAtLocation(@NotNull HeightMapType type, int x, int z) {

        return getWorld().getHighestBlockYAt(x, z, convertHeightMapType(type));
    }

    private World getWorld() {

        if (Bukkit.getWorld(worldName) == null)
            throw new IllegalStateException("Level with identifier %s does not exist.".formatted(getIdentifier()));

        return Bukkit.getWorld(worldName);

    }

    private HeightMap convertHeightMapType(@NotNull HeightMapType heightMapType) {

        switch (heightMapType) {
            case MOTION_BLOCKING_NO_LEAVES -> {
                return HeightMap.MOTION_BLOCKING_NO_LEAVES;
            }
            case null, default -> {
                throw new IllegalArgumentException("I don't know how, but somehow a HeightMapType that doesn't exist has been passed.");
            }
        }

    }

}

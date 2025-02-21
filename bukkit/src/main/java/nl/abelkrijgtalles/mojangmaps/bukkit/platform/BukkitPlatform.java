/*
 * mojang-maps.bukkit.main
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

package nl.abelkrijgtalles.mojangmaps.bukkit.platform;

import nl.abelkrijgtalles.mojangmaps.bukkit.platform.world.BukkitLevel;
import nl.abelkrijgtalles.mojangmaps.platform.Platform;
import nl.abelkrijgtalles.mojangmaps.platform.world.Level;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitPlatform implements Platform {

    @Override
    public String getMinecraftVersion() {

        return Bukkit.getMinecraftVersion();
    }

    @Override
    public @Nullable Level getLevel(@NotNull String identifier) {

        World bukkitWorld = Bukkit.getWorld(identifier);

        if (bukkitWorld == null) return null;
        return new BukkitLevel(bukkitWorld);
    }

}

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

package nl.abelkrijgtalles.mojangmaps.bukkit.platform.world;

import nl.abelkrijgtalles.mojangmaps.platform.world.Vec3;
import nl.abelkrijgtalles.mojangmaps.platform.world.WorldBorder;
import org.bukkit.Location;

public class BukkitWorldBorder implements WorldBorder {

    private final org.bukkit.WorldBorder worldBorder;

    public BukkitWorldBorder(org.bukkit.WorldBorder worldBorder) {

        this.worldBorder = worldBorder;
    }

    @Override
    public Vec3 getCenter() {

        Location center = worldBorder.getCenter();
        return new Vec3(center.x(), 0, center.z());
    }

}

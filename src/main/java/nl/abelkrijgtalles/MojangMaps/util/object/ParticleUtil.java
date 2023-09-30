/*
 * Copyright (C) 2023 Abel van Hulst/Abelkrijgtalles/Abelpro678
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.util.object;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleUtil {

    public static void spawnParticleLine(Player p, Location location1, Location location2) {

        int distance = LocationUtil.getDistance(location1, location2);

        for (int i = 0; i < distance; i++) {
            double ratio = (double) i / distance;

            int x = Math.round((float) (location1.getBlockX() + (location2.getBlockX() - location1.getBlockX()) * ratio));
            int y = Math.round((float) (location1.getBlockY() + (location2.getBlockY() - location1.getBlockY()) * ratio));
            int z = Math.round((float) (location1.getBlockZ() + (location2.getBlockZ() - location1.getBlockZ()) * ratio));

            Location location = new Location(p.getWorld(), x, y, z);

            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLUE, 1.0f);
            p.spawnParticle(Particle.REDSTONE, location, 50, dustOptions);
        }

    }

}

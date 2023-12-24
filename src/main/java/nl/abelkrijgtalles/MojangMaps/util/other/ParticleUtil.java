/*
 * MojangMaps
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.util.other;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleUtil {

    // credit: https://github.com/PenguinHi5/CustomParticleEffects/blob/master/src/main/java/io/github/penguinhi5/effect/LineParticleEffect.java

    public static Integer spawnLine(Player p, Location location1, Location location2) {

        int speed = 1;
        double distBetweenParticle = 0.25;
        int particleCount = (int) Math.max(Math.round(location2.distance(location1) / distBetweenParticle), 1);

        // spawn particles

        return new BukkitRunnable() {
            // compute dist between particles
            final double xDist = (location2.getX() - location1.getX()) / particleCount;
            final double yDist = (location2.getY() - location1.getY()) / particleCount;
            final double zDist = (location2.getZ() - location1.getZ()) / particleCount;
            int idx = 0;

            @Override
            public void run() {
                // update idx
                if (++idx >= particleCount)
                    idx = 0;

                // compute particle coordinates
                double x = location1.getX() + xDist * idx;
                double y = location1.getY() + yDist * idx;
                double z = location1.getZ() + zDist * idx;

                // spawn particles
                location1.getWorld().spawnParticle(Particle.COMPOSTER, x, y, z, 1, 0, 0, 0);
                location1.getWorld().spawnParticle(Particle.CRIMSON_SPORE, x, y, z, 1, 0.5, 0.5, 0.5);
                location1.getWorld().spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, x, y, z, 1, 0.5, 0.5, 0.5);
            }
        }.runTaskTimer(MojangMaps.getPlugin(MojangMaps.class), 0, speed).getTaskId();

    }

}

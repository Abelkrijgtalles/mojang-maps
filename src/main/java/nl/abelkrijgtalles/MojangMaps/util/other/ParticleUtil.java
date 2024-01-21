/*
 * MojangMaps
 * Copyright (C) 2024 Abel van Hulst/Abelkrijgtalles/Abelpro678
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ParticleUtil {

    // spawnLine() and getParticleLocations() are derived from this YouTube video: https://youtu.be/dJaVKIzs1x4 by PenguinHi5.

    public static int spawnLine(ArrayList<Location> locations, int speed, double distanceBetweenParticles) {

        int taskId = -1;
        ArrayList<Location> particleLocations = getParticleLocations(locations, distanceBetweenParticles);

        return new BukkitRunnable() {

            int i = 0;

            @Override
            public void run() {

                double x = particleLocations.get(i).getX();
                double y = particleLocations.get(i).getY();
                double z = particleLocations.get(i).getZ();

                particleLocations.get(0).getWorld().spawnParticle(Particle.COMPOSTER, x, y, z, 1, 0, 0, 0);
                particleLocations.get(0).getWorld().spawnParticle(Particle.CRIMSON_SPORE, x, y, z, 1, 0.5, 0.5, 0.5);
                particleLocations.get(0).getWorld().spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, x, y, z, 1, 0.5, 0.5, 0.5);

                if (++i >= particleLocations.size()) {
                    i = 0;
                }

            }

        }.runTaskTimer(MojangMaps.getPlugin(MojangMaps.class), 0, speed).getTaskId();

    }

    private static ArrayList<Location> getParticleLocations(ArrayList<Location> locations, double distanceBetweenParticles) {
        ArrayList<Location> particleLocations = new ArrayList<>();
        double[] distances = new double[locations.size() - 1];

        for (int i = 0; i < distances.length; i++) {

            distances[i] = locations.get(i).distance(locations.get(i + 1));
        }

        for (int segment = 0; segment < distances.length; segment++) {

            int particleInSegment = (int) Math.max(Math.round(distances[segment] / distanceBetweenParticles), 1);
            double xDistance = (locations.get(segment + 1).getX() - locations.get(segment).getX()) / particleInSegment;
            double yDistance = (locations.get(segment + 1).getY() - locations.get(segment).getY()) / particleInSegment;
            double zDistance = (locations.get(segment + 1).getZ() - locations.get(segment).getZ()) / particleInSegment;

            for (int point = 0; point < particleInSegment; point++) {

                double x = locations.get(segment).getX() + xDistance * point;
                double y = locations.get(segment).getY() + yDistance * point;
                double z = locations.get(segment).getZ() + zDistance * point;

                particleLocations.add(new Location(locations.get(0).getWorld(), x, y, z));

            }
        }

        return particleLocations;
    }

}

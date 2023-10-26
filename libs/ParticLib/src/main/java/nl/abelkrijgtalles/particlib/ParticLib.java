package nl.abelkrijgtalles.particlib;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import nl.abelkrijgtalles.particlib.object.ParticleLine;
import nl.abelkrijgtalles.particlib.util.LocationUtil;

public final class ParticLib extends JavaPlugin {

    public static void spawnParticleLine(ParticleLine particleLine, ArrayList<Player> players) {

        ArrayList<Location> locations = particleLine.getLocations();

        for (int i = 0; i < particleLine.getLocations().size() - 1; i++) {

            Location location1 = particleLine.getLocations().get(i);
            Location location2 = particleLine.getLocations().get(i + 1);

            int distance = LocationUtil.getDistance(location1, location2);

            double numParticles = distance * particleLine.getDensity();

            for (int j = 0; j < distance; j++) {
                double ratio = (double) j / numParticles;

                int x = Math.round((float) (location1.getBlockX() + (location2.getBlockX() - location1.getBlockX()) * ratio));
                int y = Math.round((float) (location1.getBlockY() + (location2.getBlockY() - location1.getBlockY()) * ratio));
                int z = Math.round((float) (location1.getBlockZ() + (location2.getBlockZ() - location1.getBlockZ()) * ratio));

                Location location = new Location(particleLine.getWorld(), x, y, z);

                Particle.DustOptions dustOptions = new Particle.DustOptions(particleLine.getColor(), 1.0f);
                for (Player p : players) {
                    p.spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
                }
            }

        }

    }

    public static void spawnParticleLine(ParticleLine particleLine, Player p) {

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p);

        spawnParticleLine(particleLine, players);

    }

    public static void broadcastParticleLine(ParticleLine particleLine) {

        spawnParticleLine(particleLine, (ArrayList<Player>) Bukkit.getOnlinePlayers());

    }

}

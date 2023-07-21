package nl.abelkrijgtalles.mojangmaps.util;

import org.bukkit.Location;

public class LocationUtil {

    public static boolean isTheSameLocation(Location location, Location otherLocation, int gap) {

        boolean x = false;
        boolean y = false;
        boolean z = false;

        if (Math.abs(location.getBlockX() - otherLocation.getBlockX()) <= gap + 1) {
            x = true;
        }

        if (Math.abs(location.getBlockY() - otherLocation.getBlockY()) <= gap + 1) {
            y = true;
        }

        if (Math.abs(location.getBlockY() - otherLocation.getBlockY()) <= gap + 1) {
            z = true;
        }

        return x && y && z;

    }

    public static int getDistance(Location location1, Location location2) {

        int x1 = location1.getBlockX();
        int x2 = location2.getBlockX();
        int z1 = location1.getBlockZ();
        int z2 = location2.getBlockZ();

        return Math.abs(x1 - x2) + Math.abs(z1 - z2);

    }

}

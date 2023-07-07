package nl.abelkrijgtalles.mojangmaps.util;

import org.bukkit.Location;

public class LocationUtil {

    public static boolean isTheSameLocation(Location location, Location otherLocation) {

        boolean x = false;
        boolean y = false;
        boolean z = false;

        if (location.getBlockX() == otherLocation.getBlockX()) {
            x = true;
        }

        if (location.getBlockY() == otherLocation.getBlockY()) {
            y = true;
        }

        if (location.getBlockZ() == otherLocation.getBlockZ()) {
            z = true;
        }

        if (x && y && z) {
            return true;
        } else {
            return false;
        }

    }

    public static int getDistance(Location location1, Location location2) {

        int x1 = location1.getBlockX();
        int x2 = location2.getBlockX();
        int z1 = location1.getBlockZ();
        int z2 = location2.getBlockZ();

        return Math.abs(x1 - x2) + Math.abs(z1 - z2);

    }

}

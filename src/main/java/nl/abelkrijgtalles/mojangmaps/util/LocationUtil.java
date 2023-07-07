package nl.abelkrijgtalles.mojangmaps.util;

import org.bukkit.Location;

public class LocationUtil {

    public static boolean IsTheSameLocation(Location location, Location otherLocation) {

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

}

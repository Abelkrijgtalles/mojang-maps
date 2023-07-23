package nl.abelkrijgtalles.mojangmaps.util;

import org.bukkit.Axis;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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

    public static int getOneAxisDistance(Location location1, Location location2) {

        if (Math.abs(location1.getBlockX() - location2.getBlockX()) > Math.abs(location1.getBlockZ() - location2.getBlockZ()) || Math.abs(location1.getBlockX() - location2.getBlockX()) == Math.abs(location1.getBlockZ() - location2.getBlockZ())) {

            return Math.abs(location1.getBlockX() - location2.getBlockX());

        } else {

            return Math.abs(location1.getBlockZ() - location2.getBlockZ());

        }

    }

    public static int getOneAxisDistance(Axis axis, Location location1, Location location2) {

        if (axis == Axis.X) {

            return Math.abs(location1.getBlockX() - location2.getBlockX());

        } else if (axis == Axis.Z) {

            return Math.abs(location1.getBlockZ() - location2.getBlockZ());

        } else {

            return Math.abs(location1.getBlockY() - location2.getBlockY());

        }

    }

    public static void addMoreLocations(Player p, List<Location> locations) {

        int i = 0;
        List<Location> locationsCopy = new ArrayList<>(locations);

        for (Location location : locationsCopy) {

            if (i != 0) {

                Location location1 = locations.get(i - 1);
                int oneAxisDistance = LocationUtil.getOneAxisDistance(location1, location);

                if (oneAxisDistance > 4) {

                    int neededLocations = (int) Math.floor((double) oneAxisDistance / 5);

                    for (int j = 0; j < neededLocations - 1; j += 1) {

                        locations.add(new Location(p.getWorld(), Math.round((float) ((getOneAxisDistance(Axis.X, location, location1) / neededLocations) * (j + 1)) + location1.getBlockX()), location1.getBlockY(), Math.round((float) ((getOneAxisDistance(Axis.Z, location, location1) / neededLocations) * (j + 1)) + location1.getBlockZ())));

                    }

                }

            }

            i += 1;

        }

    }

}

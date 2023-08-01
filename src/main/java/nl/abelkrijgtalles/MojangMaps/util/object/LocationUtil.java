package nl.abelkrijgtalles.MojangMaps.util.object;

import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import org.bukkit.Location;

public class LocationUtil {

    public static boolean isTheSameLocation(Location location, Location otherLocation, int gap) {

        return getDistance(location, otherLocation) < gap + 1;

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

    public static Location getClosestLocation(Location location) {

        double minDistanceSquared = Double.MAX_VALUE;
        Location closestLocation = null;

        for (Location location1 : NodesConfigUtil.getLocations()) {
            double distanceSquared = location.distanceSquared(location1);

            if (distanceSquared < minDistanceSquared) {
                minDistanceSquared = distanceSquared;
                closestLocation = location1;
            }
        }

        return closestLocation;

    }

}

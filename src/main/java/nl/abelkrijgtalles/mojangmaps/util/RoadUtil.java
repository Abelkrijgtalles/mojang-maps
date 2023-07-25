package nl.abelkrijgtalles.mojangmaps.util;

import nl.abelkrijgtalles.mojangmaps.objects.Road;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RoadUtil {
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

                        locations.add(new Location(p.getWorld(), Math.round((float) ((location.getBlockX() - location1.getBlockX()) / neededLocations) * (j + 1)) + location1.getBlockX(), location1.getBlockY(), Math.round((float) ((location.getBlockZ() - location1.getBlockZ()) / neededLocations) * (j + 1)) + location1.getBlockZ()));

                    }

                }

            }

            i += 1;

        }

    }

    public static String getRoadNameFromLocation(Location location) {

        for (Road road : NodesConfigUtil.getRoads()) {

            for (Integer location1 : road.getLocations()) {

                if (NodesConfigUtil.getLocations().get(location1) == location) {

                    return road.getName();

                }

            }

        }

        return null;

    }

    public static String getLocationMessage(Player p) {

        if (LocationUtil.isTheSameLocation(p.getLocation(), LocationUtil.getClosestLocation(p.getLocation()), 5)) {

            return "Currently on: " + RoadUtil.getRoadNameFromLocation(LocationUtil.getClosestLocation(p.getLocation())) + ".";

        } else {

            return "";

        }

    }
}

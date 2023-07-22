package nl.abelkrijgtalles.mojangmaps.objects;

import org.bukkit.Location;

import java.util.List;

public class Road {

    private String name;

    private final List<Location> locations;

    public Road(List<Location> locations) {
        this.locations = locations;
    }

    public Road(String name, List<Location> locations) {
        this.name = name;
        this.locations = locations;
    }

    public String getName() {
        if (name != null) {
            return name;
        } else {
            return "Unnamed Road";
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    public int addLocation(Location location) {

        locations.add(location);
        return locations.size() - 1;

    }
}

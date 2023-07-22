package nl.abelkrijgtalles.mojangmaps.objects;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Road implements ConfigurationSerializable {

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

    public static Road deserialize(Map<String, Object> args) {

        if (args.containsKey("name")) {

            return new Road(args.get("name").toString(), (List<Location>) args.get("locations"));

        }

        return new Road((List<Location>) args.get("locations"));

    }

    @Override
    public Map<String, Object> serialize() {

        Map<String, Object> data = new HashMap<>();

        if (name != null) {

            data.put("name", this.name);

        }

        data.put("locations", this.locations);

        return data;
    }

}

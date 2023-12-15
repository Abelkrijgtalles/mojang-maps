package nl.abelkrijgtalles.MojangMaps.util.other;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BlockSelectUtil {

    public static void getSelectedBlock(Location selectedLocation, Player p) {

        p.sendMessage(String.valueOf(selectedLocation.toVector().angle(p.getLocation().toVector())));

    }

}

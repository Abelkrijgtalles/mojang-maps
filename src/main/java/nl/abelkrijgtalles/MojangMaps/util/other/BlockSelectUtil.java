package nl.abelkrijgtalles.MojangMaps.util.other;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BlockSelectUtil {

    public static void getSelectedBlock(Location selectedLocation, Player p) {

        // btw, I don't even know what a rad is yet, because I haven't had/discussed it in school yet 👍
        p.sendMessage("Test");
        p.sendMessage(String.valueOf(selectedLocation.toVector().angle(p.getLocation().toVector())));

    }

}

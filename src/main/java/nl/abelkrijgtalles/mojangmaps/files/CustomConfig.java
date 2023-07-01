package nl.abelkrijgtalles.mojangmaps.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {

    private static File file;
    private static FileConfiguration customFile;

    // Idk weird setup I guess
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("MojangMaps").getDataFolder(), "customconfig.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // why no working brother. just work for me for god's sake
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}

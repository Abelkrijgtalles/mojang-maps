package nl.abelkrijgtalles.mojangmaps.handlers.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class NodesConfig {

    private static File file;
    private static FileConfiguration customFile;

    // Idk weird setup I guess
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("MojangMaps").getDataFolder(), "nodes.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().warning("Could not create the configuration file nodes.yml");
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().warning("Could not save the configuration file nodes.yml");
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}
package nl.abelkrijgtalles.mojangmaps;

import nl.abelkrijgtalles.mojangmaps.dijkstras_algorithm.Node;
import nl.abelkrijgtalles.mojangmaps.files.PathFindingConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class MojangMaps extends JavaPlugin {

    @Override
    public void onEnable() {

        PathFindingConfig.setup();
        PathFindingConfig.get().addDefault("node", new Node("A"));
        PathFindingConfig.get().options().copyDefaults(true);
        PathFindingConfig.save();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

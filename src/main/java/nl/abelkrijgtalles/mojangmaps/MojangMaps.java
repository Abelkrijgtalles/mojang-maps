package nl.abelkrijgtalles.mojangmaps;

import nl.abelkrijgtalles.mojangmaps.managers.config.NodesConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class MojangMaps extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        NodesConfig.setup();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

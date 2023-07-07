package nl.abelkrijgtalles.mojangmaps;

import nl.abelkrijgtalles.mojangmaps.commands.CalculateDistanceCommand;
import nl.abelkrijgtalles.mojangmaps.commands.RegisterLocationCommand;
import nl.abelkrijgtalles.mojangmaps.managers.config.NodesConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class MojangMaps extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        NodesConfig.setup();

        getCommand("registerlocation").setExecutor(new RegisterLocationCommand());
        getCommand("calculatedistance").setExecutor(new CalculateDistanceCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

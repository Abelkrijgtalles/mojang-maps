package nl.abelkrijgtalles.mojangmaps;

import nl.abelkrijgtalles.mojangmaps.commands.MessageCommand;
import nl.abelkrijgtalles.mojangmaps.files.PathFindingConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class MojangMaps extends JavaPlugin {

    @Override
    public void onEnable() {

        PathFindingConfig.setup();
        PathFindingConfig.get().addDefault("algorithm", 1);
        PathFindingConfig.get().options().copyDefaults(true);
        PathFindingConfig.save();

        getCommand("message").setExecutor(new MessageCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

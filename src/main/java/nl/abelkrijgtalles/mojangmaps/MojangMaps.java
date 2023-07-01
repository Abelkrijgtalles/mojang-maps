package nl.abelkrijgtalles.mojangmaps;

import nl.abelkrijgtalles.mojangmaps.commands.MessageCommand;
import nl.abelkrijgtalles.mojangmaps.files.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class MojangMaps extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("message").setExecutor(new MessageCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

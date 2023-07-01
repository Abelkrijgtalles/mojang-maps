package nl.abelkrijgtalles.mojangmaps;

import nl.abelkrijgtalles.mojangmaps.files.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class MojangMaps extends JavaPlugin {

    @Override
    public void onEnable() {

        CustomConfig.setup();
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

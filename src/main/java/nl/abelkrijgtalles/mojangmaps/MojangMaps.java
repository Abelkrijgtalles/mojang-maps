package nl.abelkrijgtalles.mojangmaps;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import nl.abelkrijgtalles.mojangmaps.commands.GoToCommand;
import nl.abelkrijgtalles.mojangmaps.commands.RegisterLocationCommand;
import nl.abelkrijgtalles.mojangmaps.commands.RegisterRoadCommand;
import nl.abelkrijgtalles.mojangmaps.objects.Road;
import nl.abelkrijgtalles.mojangmaps.util.NodesConfigUtil;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class MojangMaps extends JavaPlugin {

    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {

        this.adventure = BukkitAudiences.create(this);

        ConfigurationSerialization.registerClass(Road.class);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        NodesConfigUtil.setup();

        getCommand("registerlocation").setExecutor(new RegisterLocationCommand());
        getCommand("registerroad").setExecutor(new RegisterRoadCommand());
        getCommand("goto").setExecutor(new GoToCommand());

    }

    @Override
    public void onDisable() {
        if (this.adventure != null) {

            this.adventure.close();
            this.adventure = null;

        }
    }
}

package nl.abelkrijgtalles.mojangmaps;

import nl.abelkrijgtalles.mojangmaps.commands.GoToCommand;
import nl.abelkrijgtalles.mojangmaps.commands.RegisterLocationCommand;
import nl.abelkrijgtalles.mojangmaps.commands.RegisterRoadCommand;
import nl.abelkrijgtalles.mojangmaps.commands.WhereAmIStandingCommand;
import nl.abelkrijgtalles.mojangmaps.events.PlayerWalkEvent;
import nl.abelkrijgtalles.mojangmaps.objects.Road;
import nl.abelkrijgtalles.mojangmaps.util.NodesConfigUtil;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class MojangMaps extends JavaPlugin {

    @Override
    public void onEnable() {

        // Config init
        ConfigurationSerialization.registerClass(Road.class);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        NodesConfigUtil.setup();

        // Commands Init
        getCommand("registerlocation").setExecutor(new RegisterLocationCommand());
        getCommand("registerroad").setExecutor(new RegisterRoadCommand());
        getCommand("goto").setExecutor(new GoToCommand());
        getCommand("whereamistanding").setExecutor(new WhereAmIStandingCommand());

        // Listeners/Events init
        getServer().getPluginManager().registerEvents(new PlayerWalkEvent(), this);

    }

    @Override
    public void onDisable() {

    }
}

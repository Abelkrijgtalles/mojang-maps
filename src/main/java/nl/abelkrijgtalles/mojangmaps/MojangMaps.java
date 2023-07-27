package nl.abelkrijgtalles.mojangmaps;

import nl.abelkrijgtalles.mojangmaps.commands.*;
import nl.abelkrijgtalles.mojangmaps.events.PlayerWalkEvent;
import nl.abelkrijgtalles.mojangmaps.objects.Road;
import nl.abelkrijgtalles.mojangmaps.util.NodesConfigUtil;
import nl.abelkrijgtalles.mojangmaps.util.TranslationUtil;
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
        TranslationUtil translationUtil = new TranslationUtil(this);
        translationUtil.updateTranslations();
        // Commands Init
        getCommand("registerlocation").setExecutor(new RegisterLocationCommand());
        getCommand("registerroad").setExecutor(new RegisterRoadCommand());
        getCommand("goto").setExecutor(new GoToCommand());
        getCommand("whereamistanding").setExecutor(new WhereAmIStandingCommand());
        getCommand("reloadconfigsfromdisk").setExecutor(new ReloadConfigsFromDiskCommand(this));

        // Listeners/Events init
        getServer().getPluginManager().registerEvents(new PlayerWalkEvent(this), this);

    }

    @Override
    public void onDisable() {

    }
}

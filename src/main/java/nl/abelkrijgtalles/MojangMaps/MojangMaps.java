package nl.abelkrijgtalles.MojangMaps;

import com.samjakob.spigui.SpiGUI;
import nl.abelkrijgtalles.MojangMaps.command.NewGuiCommand;
import nl.abelkrijgtalles.MojangMaps.command.register.RegisterLocationCommand;
import nl.abelkrijgtalles.MojangMaps.command.register.RegisterRoadCommand;
import nl.abelkrijgtalles.MojangMaps.command.using.GoToCommand;
import nl.abelkrijgtalles.MojangMaps.command.using.WhereAmIStandingCommand;
import nl.abelkrijgtalles.MojangMaps.command.util.ReloadConfigsFromDiskCommand;
import nl.abelkrijgtalles.MojangMaps.event.PlayerWalkEvent;
import nl.abelkrijgtalles.MojangMaps.object.Road;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import nl.abelkrijgtalles.MojangMaps.util.file.TranslationUtil;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.DrilldownPie;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class MojangMaps extends JavaPlugin {

    public static SpiGUI spiGUI;

    private void addLanguageChart(Metrics metrics) {

        metrics.addCustomChart(new DrilldownPie("language", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String language = getConfig().getString("language");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(language, 1);

            switch (language) {
                case "af" -> map.put("Afrikaans", entry);
                case "ar" -> map.put("Arabic", entry);
                case "ca" -> map.put("Catalan", entry);
                case "cs" -> map.put("Czech", entry);
                case "da" -> map.put("Danish", entry);
                case "de" -> map.put("German", entry);
                case "el" -> map.put("Greek", entry);
                case "en" -> map.put("English", entry);
                case "es-ES" -> map.put("Spanish", entry);
                case "fi" -> map.put("Finnish", entry);
                case "fr" -> map.put("French", entry);
                case "he" -> map.put("Hebrew", entry);
                case "hu" -> map.put("Hungarian", entry);
                case "it" -> map.put("Italian", entry);
                case "ja" -> map.put("Japanese", entry);
                case "ko" -> map.put("Korean", entry);
                case "nl" -> map.put("Dutch", entry);
                case "no" -> map.put("Norwegian", entry);
                case "pl" -> map.put("Polish", entry);
                case "pt-BR" -> map.put("Portuguese, Brazilian", entry);
                case "pt-PT" -> map.put("Portuguese", entry);
                case "ro" -> map.put("Romanian", entry);
                case "ru" -> map.put("Russian", entry);
                case "sr" -> map.put("Serbian (Cyrillic)", entry);
                case "sv-SE" -> map.put("Swedish", entry);
                case "tr" -> map.put("Turkish", entry);
                case "uk" -> map.put("Ukrainian", entry);
                case "vi" -> map.put("Vietnamese", entry);
                case "zh-CN" -> map.put("Chinese Simplified", entry);
                case "zh-TW" -> map.put("Chinese Traditional", entry);
                default -> map.put("Other", entry);
            }

            return map;
        }));

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {

        configSetup();
        registerCommands();
        registerListeners();

        // SpiGUI init
        spiGUI = new SpiGUI(this);

        bStatsInit();
    }

    private void bStatsInit() {

        int pluginId = 19295;
        Metrics metrics = new Metrics(this, pluginId);
        addLanguageChart(metrics);

    }

    private void configSetup() {

        ConfigurationSerialization.registerClass(Road.class);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        NodesConfigUtil.setup();
        TranslationUtil translationUtil = new TranslationUtil(this);
        translationUtil.updateTranslations();

    }

    private void registerCommands() {

        getCommand("registerlocation").setExecutor(new RegisterLocationCommand());
        getCommand("registerroad").setExecutor(new RegisterRoadCommand());
        getCommand("goto").setExecutor(new GoToCommand());
        getCommand("whereamistanding").setExecutor(new WhereAmIStandingCommand());
        getCommand("reloadconfigsfromdisk").setExecutor(new ReloadConfigsFromDiskCommand(this));
        getCommand("newgui").setExecutor(new NewGuiCommand());

    }

    private void registerListeners() {

        getServer().getPluginManager().registerEvents(new PlayerWalkEvent(this), this);

    }

}

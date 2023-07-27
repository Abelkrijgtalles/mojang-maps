package nl.abelkrijgtalles.mojangmaps.util;

import nl.abelkrijgtalles.mojangmaps.MojangMaps;
import org.bukkit.Bukkit;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TranslationUtil {

    private final MojangMaps plugin;

    public TranslationUtil(MojangMaps plugin) {
        this.plugin = plugin;
    }

    public void updateTranslations() {

        if (plugin.getConfig().getString("language") != "custom") {

            Bukkit.getLogger().info("Getting latest translations.");

            JsonArray dataContents = HTTPUtil.HTTPRequestJSONArray("https://api.github.com/repos/Abelkrijgtalles/mojang-maps-data/contents");

            List<String> languageCodes = new ArrayList<>();
            String languageCode;

            dataContents.forEach(jsonValue -> {

                JsonObject content = (JsonObject) jsonValue;

                if (Objects.equals(content.getString("type"), "dir")) {

                    languageCodes.add(content.getString("name"));

                }

            });

            if (languageCodes.contains(plugin.getConfig().getString("language"))) {

                languageCode = plugin.getConfig().getString("language");

            } else {

                Bukkit.getLogger().warning("Language code in messages.yml isn't in the translations, reverting to English.");
                languageCode = "en";

            }

            HTTPUtil.DownloadFile("https://raw.githubusercontent.com/Abelkrijgtalles/mojang-maps-data/main/%s/%s.json".formatted(languageCode, languageCode), String.valueOf(Paths.get(String.valueOf(Bukkit.getServer().getPluginManager().getPlugin("MojangMaps").getDataFolder()), "messages.json")));


        }

    }

}

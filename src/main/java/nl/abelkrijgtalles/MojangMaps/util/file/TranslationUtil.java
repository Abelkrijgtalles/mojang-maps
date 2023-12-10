/*
 * Copyright (C) 2023 Abel van Hulst/Abelkrijgtalles/Abelpro678
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.util.file;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.other.HTTPUtil;

import org.bukkit.Bukkit;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class TranslationUtil {

    private final MojangMaps plugin;

    public TranslationUtil(MojangMaps plugin) {
        this.plugin = plugin;
    }

    public void updateTranslations() {

        if (!Objects.equals(plugin.getConfig().getString("language"), "custom")) {

            MojangMaps.getMMLogger().info("Getting latest translations.");

            JsonArray dataContents = HTTPUtil.HTTPRequestJSONArray("https://api.github.com/repos/Abelkrijgtalles/mojang-maps-data/contents");

            if (MojangMaps.isOnline) {
                List<String> languageCodes = new ArrayList<>();
                String languageCode;

                assert dataContents != null;
                dataContents.forEach(jsonValue -> {

                    JsonObject content = (JsonObject) jsonValue;

                    if (Objects.equals(content.getString("type"), "dir")) {

                        languageCodes.add(content.getString("name"));

                    }

                });

                if (languageCodes.contains(plugin.getConfig().getString("language"))) {

                    languageCode = plugin.getConfig().getString("language");

                } else {

                    MojangMaps.getMMLogger().warning("Language code in messages.yml isn't in the translations, reverting to English.");
                    languageCode = "en";

                }

                assert languageCode != null;
                HTTPUtil.DownloadFile("https://raw.githubusercontent.com/Abelkrijgtalles/mojang-maps-data/main/%s/%s.json".formatted(languageCode, languageCode.split("-")[0]), String.valueOf(Paths.get(String.valueOf(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("MojangMaps")).getDataFolder()), "messages.json")));
            }

        }

    }

}

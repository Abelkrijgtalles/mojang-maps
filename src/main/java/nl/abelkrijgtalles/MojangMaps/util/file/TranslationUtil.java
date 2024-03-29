/*
 * MojangMaps
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.MojangMaps.util.file;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.other.HTTPUtil;
import nl.abelkrijgtalles.MojangMaps.util.other.TestUtil;
import org.bukkit.Bukkit;

public class TranslationUtil {

    public void updateTranslations(MojangMaps plugin) {

        if (!Objects.equals(plugin.getConfig().getString("language"), "custom")) {

            if (!TestUtil.detectTest()) plugin.getLogger().info("Getting latest translations.");

            JsonArray dataContents = HTTPUtil.HTTPRequestJSONArray("https://api.github.com/repos/Abelkrijgtalles/mojang-maps-data/contents");

            if (MojangMaps.isOnline) {
                List<String> languageCodes = new ArrayList<>();
                String languageCode;

                assert dataContents != null;
                dataContents.forEach(jsonValue -> {

                    JsonObject content = jsonValue.getAsJsonObject();

                    if (Objects.equals(content.get("type").getAsString(), "dir") && !Objects.equals(content.get("name").getAsString(), ".github")) {

                        languageCodes.add(content.get("name").getAsString());

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

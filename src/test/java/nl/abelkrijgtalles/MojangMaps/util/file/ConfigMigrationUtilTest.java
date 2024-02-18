/*
 * MojangMaps
 * Copyright (C) 2024 Abel van Hulst/Abelkrijgtalles/Abelpro678
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

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
class ConfigMigrationUtilTest {

    private ServerMock server;
    private MojangMaps plugin;

    private static Stream<Arguments> possibleConfigYMLValuesFor2() {


        Boolean[] possibleStreetActionBarValues = {true, false};
        List<String> languageCodes = new ArrayList<>();
        List<Arguments> arguments = new ArrayList<>();
        languageCodes.add("custom");

        // this is to prevent rate limiting
        JsonArray dataContents = getDefaultLanguages();
        dataContents.forEach(jsonValue -> {

            JsonObject content = jsonValue.getAsJsonObject();

            if (Objects.equals(content.get("type").getAsString(), "dir") && !Objects.equals(content.get("name").getAsString(), ".github")) {

                languageCodes.add(content.get("name").getAsString());

            }

        });

        for (boolean streetActionBar : possibleStreetActionBarValues) {
            for (String languageCode : languageCodes) {
                arguments.add(Arguments.of(streetActionBar, languageCode));
            }
        }

        return arguments.stream();
    }

    private static JsonArray getDefaultLanguages() {

        return (JsonArray) JsonParser.parseString("""
                [
                  {
                    "name": "af",
                    "type": "dir"
                  },
                  {
                    "name": "ar",
                    "type": "dir"
                  },
                  {
                    "name": "ca",
                    "type": "dir"
                  },
                  {
                    "name": "crowdin.yml",
                    "type": "file"
                  },
                  {
                    "name": "cs",
                    "type": "dir"
                  },
                  {
                    "name": "da",
                    "type": "dir"
                  },
                  {
                    "name": "de",
                    "type": "dir"
                  },
                  {
                    "name": "el",
                    "type": "dir"
                  },
                  {
                    "name": "en",
                    "type": "dir"
                  },
                  {
                    "name": "es-ES",
                    "type": "dir"
                  },
                  {
                    "name": "fi",
                    "type": "dir"
                  },
                  {
                    "name": "fr",
                    "type": "dir"
                  },
                  {
                    "name": "he",
                    "type": "dir"
                  },
                  {
                    "name": "hu",
                    "type": "dir"
                  },
                  {
                    "name": "it",
                    "type": "dir"
                  },
                  {
                    "name": "ja",
                    "type": "dir"
                  },
                  {
                    "name": "ko",
                    "type": "dir"
                  },
                  {
                    "name": "la-LA",
                    "type": "dir"
                  },
                  {
                    "name": "nl",
                    "type": "dir"
                  },
                  {
                    "name": "no",
                    "type": "dir"
                  },
                  {
                    "name": "pl",
                    "type": "dir"
                  },
                  {
                    "name": "pt-BR",
                    "type": "dir"
                  },
                  {
                    "name": "pt-PT",
                    "type": "dir"
                  },
                  {
                    "name": "ro",
                    "type": "dir"
                  },
                  {
                    "name": "ru",
                    "type": "dir"
                  },
                  {
                    "name": "sr",
                    "type": "dir"
                  },
                  {
                    "name": "sv-SE",
                    "type": "dir"
                  },
                  {
                    "name": "tr",
                    "type": "dir"
                  },
                  {
                    "name": "uk",
                    "type": "dir"
                  },
                  {
                    "name": "vi",
                    "type": "dir"
                  },
                  {
                    "name": "zh-CN",
                    "type": "dir"
                  },
                  {
                    "name": "zh-TW",
                    "type": "dir"
                  }
                ]""");
    }

    @AfterEach
    public void tearDown() {

        MockBukkit.unmock();
    }

    @BeforeEach
    public void setUp() {

        server = MockBukkit.mock();
        plugin = MockBukkit.load(MojangMaps.class);

    }

    @ParameterizedTest
    @MethodSource("possibleConfigYMLValuesFor2")
    public void configMigrationFrom2To3(boolean streetActionBar, String languageCode) {

        String config = "street-actionbar: " + streetActionBar + "\nlanguage: " + languageCode;

        String configPath = new File(plugin.getDataFolder(), "config.yml").getPath();
        try {
            FileWriter configWriter = new FileWriter(configPath, false);
            configWriter.write(config);
            configWriter.close();
        } catch (IOException e) {

            Assertions.assertNotNull(e);

        }

        plugin.getLogger().info(plugin.getConfig().saveToString());
        plugin.getLogger().info(String.valueOf(ConfigMigrationUtil.getConfigVersion(plugin.getConfig())));

        try {
            ConfigMigrationUtil.migrateConfig(plugin);
        } catch (IOException e) {

            Assertions.assertNotNull(e);

        }

        plugin.reloadConfig();
        // idk if this is needed, but just i'll leave it here just in case
        String configString = plugin.getConfig().saveToString();
        String expectedConfigString = "# Config-version is auto-generated by Mojang Maps, so don't change this. When changed, undefined behavior can and will probably happen\n" +
                "config-version: 3\n\n" +
                "# If true, then the plugin shows the current street in the ActionBar (a box of text right above the name of items).\n" +
                "street-actionbar: " +
                streetActionBar +
                "\n\n" +
                "# If you change this to custom, you can change the messages in messages.yml. Codes that can be used as language: https://github.com/Abelkrijgtalles/mojang-maps-data/blob/main/README.md#the-following-codes-can-be-used-as-language\n" +
                "language: " +
                languageCode;

        Assertions.assertEquals(expectedConfigString, configString);

    }

}
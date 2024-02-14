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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.other.HTTPUtilTest;
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

        JsonArray dataContents = HTTPUtilTest.HTTPRequestJSONArray("https://api.github.com/repos/Abelkrijgtalles/mojang-maps-data/contents");
        assert dataContents != null;
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

    @BeforeEach
    public void setUp() {

        ServerMock server = MockBukkit.mock();
        MojangMaps plugin = MockBukkit.load(MojangMaps.class);

    }

    @AfterEach
    public void tearDown() {

        MockBukkit.unmock();
    }

    @ParameterizedTest
    @MethodSource("possibleConfigYMLValuesFor2")
    public void configMigrationFrom2To3(boolean streetActionBar, String languageCode) {

        String config = "street-actionbar: " + streetActionBar + "\nlanguage: " + languageCode;

        String configPath = new File(plugin.getDataFolder(), "config.yml").getPath();
        try {
            FileWriter configWriter = new FileWriter(configPath);
            configWriter.write(config);
            configWriter.close();
        } catch (IOException e) {

            Assertions.assertNotNull(e);

        }

    }

}
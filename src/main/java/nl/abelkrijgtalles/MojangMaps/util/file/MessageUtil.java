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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import org.bukkit.Bukkit;

public class MessageUtil {

    public static String getMessage(String key) {

        JsonObject messages = getMessages();
        assert messages != null;
        return messages.get(key).getAsString();

    }

    public static JsonObject getMessages() {

        try {

            Path file = Paths.get(String.valueOf(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("MojangMaps")).getDataFolder()), "messages.json");
            String tokener = new String(Files.readAllBytes(file));

            return JsonParser.parseString(tokener).getAsJsonObject();

        } catch (IOException e) {

            MojangMaps.getMMLogger().warning("Could not load messages.json.");
            return null;

        }

    }

}

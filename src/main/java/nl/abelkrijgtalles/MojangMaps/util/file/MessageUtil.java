package nl.abelkrijgtalles.MojangMaps.util.file;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class MessageUtil {

    public static String getMessage(String key) {

        JsonObject messages = getMessages();
        assert messages != null;
        return messages.getString(key);

    }

    public static JsonObject getMessages() {

        try {

            Path file = Paths.get(String.valueOf(Bukkit.getServer().getPluginManager().getPlugin("MojangMaps").getDataFolder()), "messages.json");
            JsonReader reader = Json.createReader(Files.newBufferedReader(file));

            return reader.readObject();

        } catch (IOException e) {

            Bukkit.getLogger().warning("Could not load messages.json.");
            return null;

        }

    }

}

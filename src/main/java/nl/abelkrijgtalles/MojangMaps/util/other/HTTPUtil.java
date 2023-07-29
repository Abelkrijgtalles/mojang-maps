package nl.abelkrijgtalles.MojangMaps.util.other;

import org.bukkit.Bukkit;

import javax.json.Json;
import javax.json.JsonArray;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPUtil {

    public static JsonArray HTTPRequestJSONArray(String URL) {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try {

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();

            Bukkit.getLogger().info("API call to " + URL + " finished with code " + statusCode);

            JsonArray dataContents;
            dataContents = Json.createReader(new StringReader(responseBody)).readArray();

            return dataContents;

        } catch (IOException | InterruptedException e) {

            Bukkit.getLogger().warning("Couldn't make an API call.");
            return null;

        }
    }

    public static void DownloadFile(String fileUrl, String path) {

        try {

            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(path);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {

                outputStream.write(buffer, 0, bytesRead);

            }

            outputStream.close();
            inputStream.close();

            Bukkit.getLogger().info("Successfully downloaded file from %s to %s.".formatted(fileUrl, path));

        } catch (IOException e) {

            Bukkit.getLogger().warning("Could not download file from %s to %s.".formatted(fileUrl, path));

        }

    }

}

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

package nl.abelkrijgtalles.MojangMaps.util.other;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;

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

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

public class HTTPUtil {

    public static JsonArray HTTPRequestJSONArray(String URL) {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try {

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();

            MojangMaps.getMMLogger().info("API call to " + URL + " finished with code " + statusCode);

            JsonArray dataContents;
            dataContents = Json.createReader(new StringReader(responseBody)).readArray();

            return dataContents;

        } catch (IOException | InterruptedException e) {

            MojangMaps.getMMLogger().warning("Couldn't make an API call.");
            MojangMaps.isOnline = false;
            return null;

        }
    }

    public static JsonObject HTTPRequestJSONObject(String URL) {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try {

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();

            MojangMaps.getMMLogger().info("API call to " + URL + " finished with code " + statusCode);

            JsonObject dataContents;
            dataContents = Json.createReader(new StringReader(responseBody)).readObject();

            return dataContents;

        } catch (IOException | InterruptedException e) {

            MojangMaps.getMMLogger().warning("Couldn't make an API call.");
            MojangMaps.isOnline = false;
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

            MojangMaps.getMMLogger().info("Successfully downloaded file from %s to %s.".formatted(fileUrl, path));

        } catch (IOException e) {

            MojangMaps.getMMLogger().warning("Could not download file from %s to %s.".formatted(fileUrl, path));
            MojangMaps.isOnline = false;

        }

    }

}

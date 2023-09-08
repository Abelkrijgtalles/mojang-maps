package nl.abelkrijgtalles.MojangMaps.util;

import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.other.HTTPUtil;

import java.util.Objects;

import javax.json.JsonObject;

import nl.abelkrijgtalles.autoupdater.manager.PluginRegister;
import nl.abelkrijgtalles.autoupdater.object.PluginRegisterResponse;

public class MojangMapsRegister implements PluginRegister {

    private final MojangMaps plugin;

    public MojangMapsRegister(MojangMaps plugin) {
        this.plugin = plugin;
    }

    @Override
    public PluginRegisterResponse checkForUpdate() {
        JsonObject latestRelease = HTTPUtil.HTTPRequestJSONObject("https://api.github.com/repos/Abelkrijgtalles/mojang-maps/releases/latest");
        if (!Objects.equals(latestRelease.getString("name"), plugin.getDescription().getVersion())) {

            String downloadLink = "";

            JsonObject object = (JsonObject) latestRelease.getJsonArray("assets").get(0);

            downloadLink = object.getString("browser_download_url");

            return new PluginRegisterResponse(plugin, true, downloadLink);

        }

        return new PluginRegisterResponse(plugin, false);
    }
}

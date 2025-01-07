package me.hektortm.woSSystems.systems.guis;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import me.hektortm.woSSystems.WoSSystems;
import me.hektortm.woSSystems.utils.dataclasses.gui.GUIConfig;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GUIManager {
    private final Map<String, GUIConfig> guiConfigs = new HashMap<>();
    private final File guiFolder = new File(WoSSystems.getPlugin(WoSSystems.class).getDataFolder(), "guis");
    private final Gson gson;

    public GUIManager() {
        this.gson = new Gson();
        loadAllConfigs();
    }

    public GUIConfig getGuiConfig(String id) {
        return guiConfigs.get(id);
    }

    public boolean reloadGuiConfig(String id) {
        File guiFile = new File(guiFolder, id + ".json");

        if (!guiFile.exists()) {
            return false; // GUI file doesn't exist
        }

        try (FileReader reader = new FileReader(guiFile)) {
            GUIConfig newConfig = gson.fromJson(reader, GUIConfig.class);
            guiConfigs.put(id, newConfig);
            return true; // Successfully reloaded
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error during reload
        }
    }

    private void loadAllConfigs() {
        if (!guiFolder.exists()) {
            guiFolder.mkdirs();
            return;
        }

        File[] files = guiFolder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null) {
            return;
        }

        for (File file : files) {
            try (FileReader reader = new FileReader(file)) {
                GUIConfig config = gson.fromJson(reader, GUIConfig.class);
                guiConfigs.put(file.getName().replace(".json", ""), config);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
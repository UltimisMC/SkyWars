package xyz.directplan.directlib.config;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class BukkitConfigHandler extends ConfigHandler {

    private final JavaPlugin plugin;

    @Override
    protected ConfigurationAdapter loadConfiguration(ConfigIdentifier configIdentifier) {
        return new BukkitConfigurationAdapter(plugin, configIdentifier);
    }
}

class BukkitConfigurationAdapter extends ConfigurationAdapter {

    private final ConfigIdentifier identifier;
    private FileConfiguration configuration;
    private final JavaPlugin plugin;

    public BukkitConfigurationAdapter(JavaPlugin plugin, ConfigIdentifier identifier) {
        super(identifier.getEntryClass());
        this.plugin = plugin;
        this.identifier = identifier;

        loadKeys();
    }

    @Override
    public void loadConfiguration() {
        String fileName = identifier.getFileName();
        File pluginFile = new File(plugin.getDataFolder(), fileName);

        if(!pluginFile.exists()) {
            plugin.saveResource(fileName, true);
        }
        configuration = YamlConfiguration.loadConfiguration(pluginFile);
    }

    @Override
    public void saveConfiguration() {
        String fileName = identifier.getFileName();
        File file = new File(plugin.getDataFolder(), fileName);

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(String key, Object value) {
        configuration.set(key, value);
    }

    @Override
    public Object get(String key) {
        return configuration.get(key);
    }

    @Override
    public String getString(String key) {
        return configuration.getString(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return configuration.getBoolean(key);
    }

    @Override
    public int getInteger(String key) {
        return configuration.getInt(key);
    }

    @Override
    public List<String> getStringList(String key) {
        return configuration.getStringList(key);
    }

    @Override
    public boolean containsKey(String key) {
        return configuration.contains(key);
    }

    @Override
    public Set<String> getSectionKeys(String key, boolean b) {
        ConfigurationSection section = configuration.getConfigurationSection(key);
        return section.getKeys(b);
    }
}

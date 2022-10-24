package xyz.directplan.directlib.config;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
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

    private FileConfiguration configuration;
    private final JavaPlugin plugin;

    public BukkitConfigurationAdapter(JavaPlugin plugin, ConfigIdentifier identifier) {
        super(new File(plugin.getDataFolder(), identifier.getFileName()), identifier.getEntryClass());
        this.plugin = plugin;
        loadKeys();
    }

    @Override
    public void loadConfiguration() {
        File file = getFile();
        if(!file.exists()){
            plugin.saveResource(file.getName(), false);
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void saveConfiguration() {
        File file = getFile();
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

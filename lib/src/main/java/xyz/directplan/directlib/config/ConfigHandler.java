package xyz.directplan.directlib.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public abstract class ConfigHandler {

    private final Map<String, ConfigurationAdapter> configurations = new HashMap<>();

    public void loadConfiguration(String fileName, Class<? extends ConfigEntry> entryClass) {
        loadConfigurations(new ConfigIdentifier(fileName, entryClass));
    }

    public void loadConfigurations(ConfigIdentifier... identifiers) {
        for(ConfigIdentifier identifier : identifiers) {
            ConfigurationAdapter adapter = loadConfiguration(identifier);
            configurations.put(identifier.getFileName(), adapter);
        }
    }

    public ConfigurationAdapter getConfig(String file) {
        return configurations.get(file);
    }

    protected abstract ConfigurationAdapter loadConfiguration(ConfigIdentifier configIdentifier);

    public void saveConfigurations() {
        configurations.forEach((a, ca) -> {
            ca.saveKeys();
            ca.saveConfiguration();
        });
    }

    public void reloadConfigurations() {
        configurations.forEach((a, ca) -> ca.loadKeys());
    }
}

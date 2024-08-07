package xyz.directplan.directlib.config;

/**
 * @author DirectPlan
 */
public abstract class ConfigurationAdapter implements ConfigurationMemory<String> {

    private final Class<? extends ConfigEntry> configEnumClass;
    public ConfigurationAdapter(Class<? extends ConfigEntry> configEnumClass) {
        this.configEnumClass = configEnumClass;
    }

    public abstract void loadConfiguration();

    public abstract void saveConfiguration();

    public void saveKeys() {

        for(ConfigEntry entry : configEnumClass.getEnumConstants()) {
            Object value = entry.getValue();
            if(value != null) {
                set(entry.getKey(), value);
            }
        }
    }

    public void loadKeys() {
        loadConfiguration();

        for(ConfigEntry entry : configEnumClass.getEnumConstants()) {

            String key = entry.getKey();
            if(!containsKey(key)) {
                set(key, entry.getValue());
                continue;
            }
            Object value = get(key);
            entry.setValue(value);
        }
        // For any new key that has been added to the config file.
        // This save method will make sure it is added to config, and prevent them from restarting the server again.
        saveConfiguration();
    }

}

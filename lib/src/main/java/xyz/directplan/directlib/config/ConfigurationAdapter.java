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
        saveConfiguration();
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
        // Save default entries if not already.
        saveConfiguration();
    }

}

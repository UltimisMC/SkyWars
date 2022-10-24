package xyz.directplan.directlib.config;

import lombok.Getter;
import xyz.directplan.directlib.config.replacement.ReplacementBoundary;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author DirectPlan
 */
public abstract class ConfigurationAdapter implements ConfigurationMemory<String> {

    @Getter private final File file;
    private final Class<? extends ConfigEntry> configEnumClass;
    public ConfigurationAdapter(File file, Class<? extends ConfigEntry> configEnumClass) {
        this.file = file;
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

            prepareEntryBoundaries(entry);
        }
        // For any new key that has been added to the config file.
        // This save method will make sure it is added to config, and prevent them from restarting the server again.
        saveConfiguration();
    }


    // ******** DEPRECATED ************

    private void prepareEntryBoundaries(ConfigEntry entry) {
        Map<String, ReplacementBoundary> boundaries = entry.getReplacementBoundaries();

        boundaries.clear();
        String key = entry.getKey();
        Object value = entry.getValue();

        if(value instanceof String) {
            String message = (String) value;
            prepareBoundaryMessage(boundaries, key, message);
        }else if(value instanceof List) {
            List<String> messages = (List<String>) value;
            messages.forEach(message -> prepareBoundaryMessage(boundaries, key, message));
        }
    }

    private void prepareBoundaryMessage(Map<String, ReplacementBoundary> boundaryMap, String key, String message) {

        char[] chars = message.toCharArray();

        // -1 indicates that the index is not found
        int beginIndex = -1, endIndex = -1;

        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(c != '%') continue;
            if(beginIndex <= -1) {
                beginIndex = i;
                continue;
            }
            endIndex = i;
            // BOUNDARY SEARCH FOUND & ENDED !!!!
            // Filtering boundary entry and adding cache.

            String entryKey = message.substring(beginIndex + 1, endIndex);
//            System.out.println("ENTRY KEY: " + entryKey + " (" + beginIndex+ ", " + endIndex+") for: " + key);
            ReplacementBoundary replacementBoundary = new ReplacementBoundary(key, beginIndex, endIndex);
            boundaryMap.put(entryKey, replacementBoundary);

            // Resetting for other entry keys in the same message.
            beginIndex = -1;
        }

//        System.out.println("Map size: " + boundaryMap.size());
    }
}

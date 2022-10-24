package xyz.directplan.directlib.config;

import lombok.Data;

/**
 * @author DirectPlan
 */
@Data
public class ConfigIdentifier {
    private final String fileName;
    private final Class<? extends ConfigEntry> entryClass;
}

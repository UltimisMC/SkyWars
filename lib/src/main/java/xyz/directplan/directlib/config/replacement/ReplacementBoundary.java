package xyz.directplan.directlib.config.replacement;

import lombok.Data;

/**
 * @author DirectPlan
 */
@Data
public class ReplacementBoundary {

    private final String key;
    private final int beginIndex, endIndex;
}

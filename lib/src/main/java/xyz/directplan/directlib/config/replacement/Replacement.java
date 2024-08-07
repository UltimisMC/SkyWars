package xyz.directplan.directlib.config.replacement;

import xyz.directplan.directlib.config.replacement.characters.PercentReplacementChar;

import java.util.Collection;
/**
 * @author DirectPlan
 */

public class Replacement {

    private final String key, replacement;
    private final ReplacementChar replacementChar;

    public Replacement(String key, String replacement, ReplacementChar replacementChar) {
        if(key == null || replacement == null) throw new IllegalStateException("Key or the replacement message cannot be null");
        this.key = key;
        this.replacement = replacement;
        this.replacementChar = replacementChar;
    }

    public Replacement(String key, String replacement) {
        this(key, replacement, new PercentReplacementChar());
    }

    public Replacement(String key, int replacement) {
        this(key, Integer.toString(replacement), new PercentReplacementChar());
    }

    public Replacement(String key, Collection<String> collection) {
        this(key, String.join("\n", collection));
    }

    public String replace(String message) {
        return message.replace(replacementChar.start() + key + replacementChar.end(), replacement);
    }
}

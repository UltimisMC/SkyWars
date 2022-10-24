package xyz.directplan.directlib.config.replacement;

import xyz.directplan.directlib.config.ConfigEntry;
import xyz.directplan.directlib.config.replacement.characters.PercentReplacementChar;

import java.util.Collection;
import java.util.Map;

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

     @Deprecated
    public String replace(ConfigEntry entry, String message) {

        Map<String, ReplacementBoundary> boundaries = entry.getReplacementBoundaries();

        ReplacementBoundary replacementBoundary = boundaries.get(key);
        if(replacementBoundary == null) {
            return message;
        }
        int beginIndex = replacementBoundary.getBeginIndex();
        int endIndex = replacementBoundary.getEndIndex();

        int lastMessageIndex = (message.length() - 1);
        System.out.println("key: " + key);
        System.out.println("message length: " + message.length() + " (" + message + ")");
        System.out.println("First text begin index: " + "0 => "+ beginIndex);
        System.out.println("Last text index: " + endIndex + 1 + " => " + lastMessageIndex);
        String firstText = message.substring(0, beginIndex);
        String lastText = message.substring(endIndex + 1, lastMessageIndex);
        return (firstText + replacement + lastText);
    }

    public String replace(String message) {
        return message.replace(replacementChar.start() + key + replacementChar.end(), replacement);
    }
}

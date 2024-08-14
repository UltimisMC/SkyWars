package xyz.directplan.directlib.config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.config.replacement.Replacement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public interface ConfigEntry {
    
    Object getValue();

    String getKey();

    void setValue(Object value);

    default List<String> getStringList(boolean colored, Replacement... replacements){
        Object value = getValue();
        List<String> lines = new ArrayList<>();
        if(!(value instanceof List)) {
            return lines;
        }
        List<?> list = (List<?>) value;
        for(Object obj : list) {
            if(!(obj instanceof String)) continue;
            String line = runReplacements(obj.toString(), replacements);
            if(colored) line = ChatColor.translateAlternateColorCodes('&', line);

            String lineSeparator = System.lineSeparator();
            if(line.contains(lineSeparator)) {
                String[] separatedLines = line.split(lineSeparator);
                lines.addAll(Arrays.asList(separatedLines));
                continue;
            }
            lines.add(line);
        }
        return lines;
    }

    default List<String> getStringList(Replacement... replacements) {
        return getStringList(true, replacements);
    }

    default void broadcastMessage(Replacement... replacements) {
        if(getValue() instanceof List) {
            getStringList(replacements).stream()
                    .map(PluginUtility::translateMessage)
                    .forEach(Bukkit::broadcastMessage);
            return;
        }
        Bukkit.broadcastMessage(PluginUtility.translateMessage(getStringValue(replacements)));
    }

    default void sendMessage(CommandSender sender, Replacement... replacements) {
        Object value = getValue();
        if(value instanceof List) {
            getStringList(replacements).forEach(sender::sendMessage);
            return;
        }
        String message = getStringValue(replacements);
        sender.sendMessage(PluginUtility.translateMessage(message));
    }

    default String runReplacements(String text, Replacement... replacements) {
        for(Replacement replacement : replacements) {
            text = replacement.replace(text);
        }
        return text;
    }

    default String getStringValue(Replacement... replacements) {
        Object value = getValue();
        if (!(value instanceof String)) return null;

        String text = value.toString();
        return runReplacements(text, replacements);
    }

    default int getInteger() {
        return (int) getValue();
    }

    default boolean getBoolean() {
        return (boolean) getValue();
    }
}

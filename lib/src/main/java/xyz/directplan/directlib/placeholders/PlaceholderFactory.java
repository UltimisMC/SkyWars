package xyz.directplan.directlib.placeholders;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public abstract class PlaceholderFactory<T> extends AbstractPlaceholderExpansion {

    private final Map<String, PlaceholderProcessor<T>> placeholders = new HashMap<>();

    public PlaceholderFactory(JavaPlugin plugin, String identifier) {
        super(plugin, identifier);
    }

    public void registerProcessor(String name, PlaceholderProcessor<T> placeholderProcessor) {
        placeholders.put(name, placeholderProcessor);
    }

    public PlaceholderProcessor<T> getPlaceholderProcessor(String name) {
        return placeholders.get(name);
    }

    public String process(String name, T player, String value) {
        PlaceholderProcessor<T> placeholderProcessor = getPlaceholderProcessor(name);
        if(placeholderProcessor == null) {
            return "Invalid processor '" + name+"'";
        }
        Object placeholderValue = placeholderProcessor.process(player, value);
        if(placeholderValue == null) return null;
        return placeholderValue.toString();
    }

    @Override
    public String onPlaceholderPairRequest(Player player, String key, String value) {
        T genericPlayer = getPlayer(player);

        return process(key, genericPlayer, value);
    }

    public abstract T getPlayer(Player player);
}

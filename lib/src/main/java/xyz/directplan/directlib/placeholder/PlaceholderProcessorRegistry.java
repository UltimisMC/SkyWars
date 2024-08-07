package xyz.directplan.directlib.placeholder;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class PlaceholderProcessorRegistry {

    private final Map<String, PlaceholderProcessor> statProcessors = new HashMap<>();

    public void register(String name, PlaceholderProcessor processor) {
        statProcessors.put(name, processor);
    }

    public PlaceholderProcessor getProcessor(String name) {
        return statProcessors.get(name);
    }

    public Object process(Player player, String key, String value) {
        PlaceholderProcessor processor = getProcessor(key);
        if(processor == null) {
            return "Invalid processor '" + key + "'";
        }
        return processor.process(player, value);
    }
}

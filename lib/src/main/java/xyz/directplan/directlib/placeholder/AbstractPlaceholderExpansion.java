package xyz.directplan.directlib.placeholder;

import lombok.Getter;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractPlaceholderExpansion extends PlaceholderExpansion {

    private final String identifier;
    private final String author;
    private final String version;

    private final PlaceholderProcessorRegistry processorRegistry;

    public AbstractPlaceholderExpansion(JavaPlugin plugin, String identifier) {
        this.identifier = identifier;

        PluginDescriptionFile descriptionFile = plugin.getDescription();
        author = String.join(", ", descriptionFile.getAuthors());
        version = descriptionFile.getVersion();

        processorRegistry = new PlaceholderProcessorRegistry();
    }

    public abstract void initialize(PlaceholderProcessorRegistry processorRegistry);

    protected Object onPlaceholderEntryRequest(Player player, String key, String value) {
        if(!player.isOnline()) {
            return "N/A";
        }
        return processorRegistry.process(player, key, value);
    }

    protected Object onPlaceholderRequestArray(Player player, String[] params) {
        if(params.length == 0) return null;
        String key = null, value = null;

        if(params.length == 1) {
            key = params[0];
        }
        if(params.length >= 2) {
            value = params[1];
        }

        return onPlaceholderEntryRequest(player, key, value);
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        String[] arrayParams = params.split("_");
        return onPlaceholderRequestArray(player, arrayParams).toString();
    }

    @Override
    public boolean register() {
        initialize(processorRegistry);
        return super.register();
    }

    @Override
    public boolean persist() {
        return true;
    }
}

package xyz.directplan.directlib.placeholders;

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

    public AbstractPlaceholderExpansion(JavaPlugin plugin, String identifier) {
        this.identifier = identifier;

        PluginDescriptionFile descriptionFile = plugin.getDescription();
        author = String.join(", ", descriptionFile.getAuthors());
        version = descriptionFile.getVersion();
    }

    public String onPlaceholderPairRequest(Player player, String key, String value) {
        return null;
    }

    public String onPlaceholderRequestArray(Player player, String[] params) {
        if(params.length == 0) return null;
        String key = null, value = null;

        if(params.length == 1) {
            key = params[0];
        }
        if(params.length >= 2) {
            value = params[1];
        }

        return onPlaceholderPairRequest(player, key, value);
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        String[] arrayParams = params.split("_");
        return onPlaceholderRequestArray(player, arrayParams);
    }
}

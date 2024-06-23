package xyz.directplan.directlib.placeholders;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author DirectPlan
 */
public class PlayerPlaceholderFactory extends PlaceholderFactory<Player> {

    public PlayerPlaceholderFactory(JavaPlugin plugin, String identifier) {
        super(plugin, identifier);
    }

    @Override
    public Player getPlayer(Player player) {
        return player;
    }
}

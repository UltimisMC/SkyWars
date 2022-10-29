package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public abstract class UserPlaceholderExpansion extends AbstractPlaceholderExpansion {

    private final UserManager userManager;
    protected final PlaceholderProcessorHandler processorHandler;

    public UserPlaceholderExpansion(SkyWarsPlugin plugin, String identifier) {
        super(plugin, identifier);

        userManager = plugin.getUserManager();
        processorHandler = new PlaceholderProcessorHandler();
    }

    @Override
    public String onPlaceholderRequestPair(Player player, String key, String value) {
        User user = userManager.getCachedUser(player);
        if(user == null) {
            return "Loading...";
        }
        return processorHandler.process(key, user, value);
    }
}

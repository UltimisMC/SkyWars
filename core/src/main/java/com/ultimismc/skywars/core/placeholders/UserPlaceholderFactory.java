package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.user.User;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.directplan.directlib.placeholders.PlaceholderFactory;

/**
 * @author DirectPlan
 */
public abstract class UserPlaceholderFactory extends PlaceholderFactory<User> {

    public UserPlaceholderFactory(JavaPlugin plugin, String identifier) {
        super(plugin, identifier);
    }
}

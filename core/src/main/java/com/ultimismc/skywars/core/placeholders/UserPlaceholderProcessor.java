package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.placeholder.PlaceholderProcessor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public abstract class UserPlaceholderProcessor implements PlaceholderProcessor {

    private final UserManager userManager;

    public abstract Object process(User user, String value);

    @Override
    public Object process(Player player, String value) {
        return userManager.getCachedUser(player);
    }
}

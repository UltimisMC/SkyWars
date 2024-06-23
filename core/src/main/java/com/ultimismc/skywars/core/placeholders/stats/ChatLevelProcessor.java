package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public class ChatLevelProcessor implements UserPlaceholderProcessor {

    @Override
    public String process(User user, String value) {
        return user.getLevelDisplayName(true);
    }
}

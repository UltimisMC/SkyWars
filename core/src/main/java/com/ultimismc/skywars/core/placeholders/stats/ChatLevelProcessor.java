package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;

/**
 * @author DirectPlan
 */
public class ChatLevelProcessor extends UserPlaceholderProcessor {

    public ChatLevelProcessor(UserManager userManager) {
        super(userManager);
    }

    @Override
    public String process(User user, String value) {
        return user.getLevelDisplayName(true);
    }
}

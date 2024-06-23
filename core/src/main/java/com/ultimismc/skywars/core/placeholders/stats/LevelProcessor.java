package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LevelProcessor implements UserPlaceholderProcessor {
    
    private final boolean showPrestige;

    @Override
    public String process(User user, String value) {
        if(showPrestige) return user.getLevelDisplayName();

        Level level = user.getLevel();
        return String.valueOf(level.getOrder());
    }
}

package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.placeholders.PlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class LevelProcessor implements PlaceholderProcessor {
    
    private final boolean noPrestige;

    public LevelProcessor() {
        this(false);
    }
    
    @Override
    public String process(User user, String value) {
        if(noPrestige) {
            Level level = user.getLevel();
            return String.valueOf(level.getOrder());
        }
        return user.getLevelDisplayName();
    }
}

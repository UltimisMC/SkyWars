package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
public class LevelProcessor extends UserPlaceholderProcessor {
    
    private final boolean showPrestige;

    public LevelProcessor(UserManager userManager, boolean showPrestige) {
        super(userManager);

        this.showPrestige = showPrestige;
    }

    @Override
    public String process(User user, String value) {
        if(showPrestige) return user.getLevelDisplayName();

        Level level = user.getLevel();
        return String.valueOf(level.getOrder());
    }
}

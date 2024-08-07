package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import com.ultimismc.skywars.core.user.UserStatistics;

/**
 * @author DirectPlan
 */
public class BestWinstreakProcessor extends UserPlaceholderProcessor {

    public BestWinstreakProcessor(UserManager userManager) {
        super(userManager);
    }

    @Override
    public String process(User user, String value) {
        UserStatistics userStatistics = user.getStatistics();
        return String.valueOf(userStatistics.getBestWinstreak());
    }
}

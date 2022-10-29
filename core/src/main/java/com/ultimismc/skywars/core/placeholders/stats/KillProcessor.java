package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.placeholders.PlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;

/**
 * @author DirectPlan
 */
public class KillProcessor implements PlaceholderProcessor {

    @Override
    public String process(User user, String value) {
        UserStatistics userStatistics = user.getStatistics();
        return String.valueOf(userStatistics.getTotalKills());
    }
}

package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;

/**
 * @author DirectPlan
 */
public interface MessageResolver {

    String resolveMessage(String user, String killer, UserStatistics statistics);

    default String resolveMessage(User user, User killer) {
        UserStatistics killerStatistics = null;
        String killerDisplayName = "";
        if(killer != null) {
            killerDisplayName = killer.getDisplayName();
            killerStatistics = killer.getStatistics();
        }
        return resolveMessage(user.getDisplayName(), killerDisplayName, killerStatistics);
    }
}
